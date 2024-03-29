/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import it.csi.idf.idfapi.util.DBUtil;

/**
 * Generic abstract class for basic JDBC DAO
 * @param <T>
 */
public abstract class GenericJdbcDAO<T> {
	
	private final static int INITIAL_POSITION = 1 ;
	
	/**
	 * The DataSource providing the connections
	 */
	private final DataSource dataSource;
	
	/**
	 * Constructor
	 */
	protected GenericJdbcDAO() {
		super();
		this.dataSource = DBUtil.jdbcTemplate.getDataSource();
	}
	
	protected abstract T newInstance() ;

	/**
	 * Returns the SQL SELECT REQUEST to be used to retrieve the bean data from the database
	 * @return
	 */
	protected abstract String  getSqlSelect();
	
	/**
	 * Returns the SQL SELECT REQUEST to be used to retrieve all the occurrences
	 * @return
	 */
	protected abstract String  getSqlSelectAll();
	
	/**
	 * Returns the SQL INSERT REQUEST to be used to insert the bean in the database
	 * @return
	 */
	protected abstract String  getSqlInsert();
	
	/**
	 * Returns the SQL UPDATE REQUEST to be used to update the bean in the database
	 * @return
	 */
	protected abstract String  getSqlUpdate();
	
	/**
	 * Returns the SQL DELETE REQUEST to be used to delete the bean from the database
	 * @return
	 */
	protected abstract String  getSqlDelete();
	
	/**
	 * Returns the SQL COUNT REQUEST to be used to check if the bean exists in the database
	 * @return
	 */
	protected abstract String  getSqlCount();
	
	/**
	 * Returns the SQL COUNT REQUEST to be used to count all the beans present in the database
	 * @return
	 */
	protected abstract String  getSqlCountAll();
	
	/**
	 * Set the primary key value(s) in the given PreparedStatement
	 * @param ps
	 * @param i 
	 * @param bean
	 * @throws SQLException
	 */
	protected abstract void    setValuesForPrimaryKey(PreparedStatement ps, int i, T bean) throws SQLException ;
	
	/**
	 * Set the bean values in the given PreparedStatement before SQL INSERT
	 * @param ps
	 * @param i
	 * @param bean
	 * @throws SQLException
	 */
	protected abstract void    setValuesForInsert(PreparedStatement ps, int i, T bean) throws SQLException ; 
	
	/**
	 * Set the bean values in the given PreparedStatement before SQL UPDATE
	 * @param ps
	 * @param i
	 * @param bean
	 * @throws SQLException
	 */
	protected abstract void    setValuesForUpdate(PreparedStatement ps, int i, T bean) throws SQLException ; 
	
	/**
	 * Populates the bean attributes from the given ResultSet
	 * @param rs
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	protected abstract T       populateBean(ResultSet rs, T bean) throws SQLException ;
	
	/**
	 * Abstract method used to set the computed value for an auto-incremented key <br>
	 * This method is supposed to set the key if the bean has an auto-incremented key <br>
	 * or to throw an exception if the bean doesn't have an auto-incremented key 
	 * @param bean
	 * @param id
	 */
	protected abstract void setAutoIncrementedKey(T bean, long id);	
	
    //-----------------------------------------------------------------------------------------
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
  
    //-----------------------------------------------------------------------------------------
	/**
	 * Loads the given bean from the database using its primary key (SQL SELECT)<br>
	 * The given bean is populated from the ResultSet if found
	 * @param bean
	 * @return true if found and loaded, false if not found
	 */
	protected boolean doSelect(T bean) {
 
		boolean result = false ;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlSelect() );
			//--- Set the PRIMARY KEY ( SQL "WHERE ..." )
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean); 
			//--- Execute SQL SELECT 
			rs = ps.executeQuery();
			if (rs.next()) {
				populateBean(rs, bean);
				result = true ;
			}
			else {
				result = false ;
			}
			
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,rs);
		}
		return result ;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * Returns all the occurrences existing in the database
	 * @return 
	 */
	protected List<T> doSelectAll() {
		 
		List<T> list = new LinkedList<T>() ;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlSelectAll() );
			//--- Execute SQL SELECT 
			rs = ps.executeQuery();
			while ( rs.next() ) {
				T bean = newInstance(); 
				populateBean(rs, bean);
				list.add(bean);
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,rs);
		}
		return list ;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database (SQL INSERT)
	 * @param bean
	 */
	protected void doInsert(T bean) {
		 
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlInsert() );
			//--- Call specific method to set the values to be inserted
			setValuesForInsert(ps, INITIAL_POSITION, bean); 
			//--- Execute SQL INSERT
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,null);
		}
	}	
    
	//-----------------------------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database (SQL INSERT) with an auto-incremented columns
	 * @param bean
	 */
	protected Long doInsertAutoIncr(T bean) {
		Long generatedKey = 0L ;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlInsert(), PreparedStatement.RETURN_GENERATED_KEYS );
			//--- Call specific method to set the values to be inserted
			setValuesForInsert(ps, INITIAL_POSITION, bean); 
			//--- Execute SQL INSERT
			ps.executeUpdate();
			//--- Retrieve the generated key 
			rs = ps.getGeneratedKeys();
			if ( rs.next() ) {
				generatedKey = rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,rs);
		}
		// update the bean with the generated key
		setAutoIncrementedKey(bean, generatedKey);	
		return generatedKey ;
	}	
    
	//-----------------------------------------------------------------------------------------
	/**
	 * Updates the given bean in the database (SQL UPDATE)
	 * @param bean
	 * @return the JDBC return code (i.e. the row count affected by the UPDATE operation : 0 or 1 )
	 */
	protected int doUpdate(T bean) {
		int result = 0 ;
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlUpdate() );
			//--- Call specific method to set the values to be updated and the primary key
			setValuesForUpdate(ps, INITIAL_POSITION, bean);
			//--- Execute SQL UPDATE
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,null);
		}
		return result ;
	}	
	//-----------------------------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database (SQL DELETE)
	 * @param bean
	 * @return the JDBC return code (i.e. the row count affected by the DELETE operation : 0 or 1 )
	 */
	protected int doDelete(T bean) {
		int result = 0 ;
 
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlDelete() );
			//--- Set the PRIMARY KEY ( SQL "WHERE ..." )
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean); 
			//--- Execute SQL DELETE
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,null);
		}
		return result ;
	}
	
	//-----------------------------------------------------------------------------------------
	/**
	 * Checks if the given bean exists in the database (SQL SELECT COUNT(*) )
	 * @param bean
	 * @return
	 */
	protected boolean doExists(T bean) {

		long result = 0 ;
 
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlCount() );
			//--- Set the PRIMARY KEY ( SQL "WHERE ..." )
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean); 
			//--- Execute SQL COUNT 
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,rs);
		}
		return result > 0 ;
	}
	//-----------------------------------------------------------------------------------------
	/**
	 * Counts all the occurrences in the table ( SQL SELECT COUNT(*) )
	 * @return
	 */
	protected long doCountAll() {

		long result = 0 ;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement( getSqlCountAll() );
			//--- Execute SQL COUNT (without where clause)
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con,ps,rs);
		}
		return result ;
	}
	
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, String value) throws SQLException {
		ps.setString(i, value);
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Boolean value) throws SQLException {
		ps.setBoolean(i, value);
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, java.util.Date value) throws SQLException {
		if ( value != null ) {
			ps.setDate(i, new java.sql.Date(value.getTime())); // Convert util.Date to sql.Date
		}
		else {
			ps.setNull(i, java.sql.Types.DATE); 
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, java.sql.Date value) throws SQLException {
		ps.setDate(i, value);
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, java.sql.Time value) throws SQLException {
		ps.setTime(i, value);
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, java.sql.Timestamp value) throws SQLException {
		ps.setTimestamp(i, value);
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Byte value) throws SQLException {
		if ( value != null ) {
			ps.setByte(i, value.byteValue());
		}
		else {
			ps.setNull(i, java.sql.Types.TINYINT); // JDBC : "TINYINT" => getByte/setByte
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Short value) throws SQLException {
		if ( value != null ) {
			ps.setShort(i, value.shortValue());
		}
		else {
			ps.setNull(i, java.sql.Types.SMALLINT);
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Integer value) throws SQLException {
		if ( value != null ) {
			ps.setInt(i, value.intValue());
		}
		else {
			ps.setNull(i, java.sql.Types.INTEGER);
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Long value) throws SQLException {
		if ( value != null ) {
			ps.setLong(i, value.longValue());
		}
		else {
			ps.setNull(i, java.sql.Types.BIGINT); // JDBC : "BIGINT" => getLong/setLong
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Float value) throws SQLException {
		if ( value != null ) {
			ps.setFloat(i, value.floatValue());
		}
		else {
			ps.setNull(i, java.sql.Types.FLOAT); 
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, Double value) throws SQLException {
		if ( value != null ) {
			ps.setDouble(i, value.doubleValue());
		}
		else {
			ps.setNull(i, java.sql.Types.DOUBLE); 
		}
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, BigDecimal value) throws SQLException {
		ps.setBigDecimal(i, value );
	}
	//-----------------------------------------------------------------------------------------
	protected void setValue(PreparedStatement ps, int i, byte[] value) throws SQLException {
		ps.setBytes(i, value );
	}
	
	private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(con != null) {
			try {con.close();} catch (SQLException e) {}
		}
		if(ps != null) {
			try {ps.close();} catch (SQLException e) {}
		}
		if(rs != null) {
			try {rs.close();} catch (SQLException e) {}
		}
	}

}
