/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Classe di utilit&agrave; generica per la gestione delle date.<br />
 *
 * @author Enrico Fusaro
 * @version 1.0
 */
public final class DateUtil {

    private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".util");

    public static final String DEFAULT_SEPARATOR = "/";

    public static final long ONE_YEAR_MILLIS = 1000 * 60 * 60 * 24 * 365;

    public static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    public static final String DATE_FORMAT_PATTERN_TRADITIONAL = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_NO_SPACE = "yyyyMMddHHmmss";

    private static final String VALID_DATE_REGEXP = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";

    public static String addOneYear(String base) throws Exception {
        final java.util.Date dt_base = ConvertUtil.convertToDate(base);
        final Calendar cal_base = Calendar.getInstance();
        cal_base.setTime(dt_base);
        cal_base.add(Calendar.YEAR, 1);
        final java.util.Date dt_after = cal_base.getTime();
        return ConvertUtil.convertToString(dt_after);
    }

    public static String addTenYear(String base) throws Exception {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(base)) {
            final java.util.Date dt_base = ConvertUtil.convertToDate(base);
            final Calendar cal_base = Calendar.getInstance();
            cal_base.setTime(dt_base);
            cal_base.add(Calendar.YEAR, 10);
            final java.util.Date dt_after = cal_base.getTime();
            return ConvertUtil.convertToString(dt_after);
        } else {
            return null;
        }
    }

    public static String getAnnoMeseCorrente() {
        final Calendar cal = new GregorianCalendar();
        final int month = cal.get(Calendar.MONTH);
        return Integer.toString(cal.get(Calendar.YEAR)) + (month < 10 ? "0" : EMPTY) + Integer.toString(month);
    }

    public static boolean checkValideDate(String date) {
        if (StringUtils.isNotEmpty(date)) {
            return date.matches(VALID_DATE_REGEXP);
        }

        return false;
    }

    public static int getAnnoCorrente() {
        final Calendar cal = new GregorianCalendar();
        return cal.get(Calendar.YEAR);
    }

    public static String getMeseCorrente() {
        final Calendar cal = new GregorianCalendar();
        final int month = cal.get(Calendar.MONTH) + 1;
        return (month < 10 ? "0" : EMPTY) + Integer.toString(month);
    }

    public static String getGiornoCorrente() {
        final Calendar cal = new GregorianCalendar();
        final int day = cal.get(Calendar.DATE);
        return (day < 10 ? "0" : EMPTY) + Integer.toString(day);
    }

    public static String getOraCorrente() {
        final Calendar cal = new GregorianCalendar();
        final int hour = cal.get(Calendar.HOUR_OF_DAY);
        return (hour < 10 ? "0" : EMPTY) + Integer.toString(hour);
    }

    public static String getMinutiCorrenti() {
        final Calendar cal = new GregorianCalendar();
        final int minute = cal.get(Calendar.MINUTE);
        return (minute < 10 ? "0" : EMPTY) + Integer.toString(minute);
    }
    
    public static String getDataAndOraCorrente() {
    	final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN_TRADITIONAL);
    	return dateFormatter.format(new Date());
    }

    public static String getDataCorrente() {
        return getDataCorrente(DEFAULT_SEPARATOR);
    }

    public static String getDataCorrente(String token) {
        String tk = StringUtils.isEmpty(token) ? DEFAULT_SEPARATOR : token;

        return getGiornoCorrente() + tk + getMeseCorrente() + tk + getAnnoCorrente();
    }

    public static boolean checkDateOrder(String dataPrec, String dataSucc, boolean canBeEqual) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        try {
            if (StringUtils.isEmpty(dataPrec) || StringUtils.isEmpty(dataSucc)) {
                return false;
            }
            final java.util.Date dataInizio = dateFormatter.parse(dataPrec);
            final java.util.Date dataFine = dateFormatter.parse(dataSucc);
            final int confronto = dataInizio.compareTo(dataFine);
            if (confronto > 0 || (! canBeEqual && confronto == 0)) {
                return false;
            }
        } catch (ParseException parseEx) {
            return false;
        }
        return true;
    }

    public static boolean checkDateOrderSysdate(String dataPrec, String dataSucc, boolean canBeEqual) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        try {
            if (StringUtils.isEmpty(dataPrec) || StringUtils.isEmpty(dataSucc)) {
                return false;
            }
            final java.util.Date dataInizio = dateFormatter.parse(dataPrec);
            final java.util.Date dataFine = dateFormatter.parse(dataSucc);
            final java.util.Date oggi = dateFormatter.parse(getDataCorrente());
            final int confronto = dataInizio.compareTo(dataFine);
            if (confronto > 0 || (! canBeEqual && confronto == 0) ||
                oggi.compareTo(dataFine) < 0 ||oggi.compareTo(dataInizio) < 0) {
                return false;
            }
        } catch (ParseException parseEx) {
            return false;
        }
        return true;
    }

    public static boolean checkDateMajorSydate(String date, boolean canBeEqual) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        try {
            if (StringUtils.isEmpty(date)) {
                return false;
            }
            final java.util.Date dataInizio = dateFormatter.parse(date);
            final java.util.Date oggi = dateFormatter.parse(getDataCorrente());
            int confronto = oggi.compareTo(dataInizio);
            if (confronto > 0 || (!canBeEqual && confronto == 0)) {
                return false;
            }
        } catch (ParseException parseEx) {
            return false;
        }
        return true;
    }

    public static java.sql.Date getSqlDataCorrente() {
        return new java.sql.Date(System.currentTimeMillis()) ;
    }

    public static String getDataElencoAllegati() {
        final StringBuilder s = new StringBuilder();
        s.append(getAnnoCorrente()).append(getMeseCorrente()).append(getGiornoCorrente())
         .append("_")
         .append(getOraCorrente())
         .append(getMinutiCorrenti());
        return s.toString();
    }

    public static String getDataElencoAllegati(String data) {
        final StringBuilder s = new StringBuilder();
        final String giorno = StringUtils.substringBefore(data, "/");
        final String mese = StringUtils.substringBetween(data, "/", "/");
        final String anno = StringUtils.substringAfterLast(data, "/");
        s.append(anno).append("-").append(mese).append("-").append(giorno);
        return s.toString();
    }

    public static String parseDateField(String stringa) {
        String result;
        if (stringa == null || stringa.length() == 0) {
            return stringa;
        }

        result = stringa;

        try {
            int campo = Integer.parseInt(result);
            if (campo < 10) {
                result = "0" + campo;
            }
        } catch (NumberFormatException nfe) {
            return parseQuote(result);
        }

        return result;
    }

    public static String parseQuote(String stringa) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(stringa)) {
            return EMPTY;
        }

        final StringBuilder builder = new StringBuilder();

        final StringTokenizer st = new StringTokenizer(stringa, "\"");
        while (st.hasMoreTokens()) {
        	builder.append(st.nextToken()).append("&#34;");
        }

        String result = builder.toString();
        if (! stringa.endsWith("\"")) {
            result = result.substring(0, result.length() - 5);
        }

        return result;
    }

    public static java.sql.Timestamp getTimestampDataCorrente() {
        final java.util.Date date = new java.util.Date();
        return new java.sql.Timestamp(date.getTime());
    }
    
    public static String getTokenDataCorrente(){
    	return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static int getAnnoFromDate(Timestamp data) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTime());
        return cal.get(Calendar.YEAR);
    }

    public static int getMeseFromDate(Timestamp data) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTime());
        return cal.get(Calendar.MONTH)+1;
    }

    public static int getGiornoFromDate(Timestamp data) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTime());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String getStringTimestamp(Timestamp dataInput) {
        String result = EMPTY;

        if (dataInput != null) {
            final Calendar data = getCalendar(dataInput);
            String anno = EMPTY + data.get(Calendar.YEAR);

            while (anno.length() < 4) {
                anno = "0" + anno;
            }

            result += parseDateField(EMPTY + data.get(Calendar.DATE))
                + "/"
                + parseDateField(EMPTY + (data.get(Calendar.MONTH) + 1)) + "/" + anno;
        }

        return result;
    }

    public static Calendar getCalendar(Timestamp ts) {
        final Calendar data = new GregorianCalendar();
        data.setTime(ts);
        return data;
    }

    public static Timestamp buildTimestamp(String data) {
        return buildTimestamp(data, DATE_FORMAT_PATTERN);
    }

    public static Timestamp buildTimestamp(String data, String formato) {
        Timestamp result = null;
        final SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false);

        try {
            result = new Timestamp(sdf.parse(data).getTime());
        } catch (Exception e) {
            LOGGER.error("[DateUtil::buildTimestamp] Exception: " + e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * @param date
     * @return
     */
    public static long convertDateToTimestamp(java.util.Date date) {
        return convertDateToTimestamp(date, TimeZone.getDefault(), Locale.ITALIAN);
    }

    /**
     *
     * @param date
     * @param timezone
     * @return
     */
    public static long convertDateToTimestamp(java.util.Date date, TimeZone timezone) {
        return convertDateToTimestamp(date, timezone, Locale.ITALIAN);
    }

    /**
     *
     * @param date
     * @param locale
     * @return
     */
    public static long convertDateToTimestamp(java.util.Date date, Locale locale) {
        return convertDateToTimestamp(date, TimeZone.getDefault(), locale);
    }

    /**
     *
     * @param date
     * @param timezone
     * @param locale
     * @return
     */
    public static long convertDateToTimestamp(java.util.Date date, TimeZone timezone, Locale locale) {
        final Calendar calendar = new GregorianCalendar(timezone, locale);
        calendar.setTimeInMillis(date.getTime());
        return calendar.getTimeInMillis();
    }

    /**
     *
     * @param timestamp
     * @return
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, TimeZone.getDefault(), Locale.ITALIAN, EasyDateTimeFormat.FULL_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @return
     */
    public static String formatTimestamp(long timestamp, TimeZone timezone) {
        return formatTimestamp(timestamp, timezone, Locale.ITALIAN, EasyDateTimeFormat.FULL_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @param locale
     * @return
     */
    public static String formatTimestamp(long timestamp, Locale locale) {
        return formatTimestamp(timestamp, TimeZone.getDefault(), locale, EasyDateTimeFormat.FULL_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @param datePattern
     * @return
     */
    public static String formatTimestamp(long timestamp, TimeZone timezone, String datePattern) {
        return formatTimestamp(timestamp, timezone, Locale.ITALIAN, datePattern);
    }

    /**
     *
     * @param timestamp
     * @param locale
     * @param datePattern
     * @return
     */
    public static String formatTimestamp(long timestamp, Locale locale, String datePattern) {
        return formatTimestamp(timestamp, TimeZone.getDefault(), locale, datePattern);
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @param locale
     * @param dateTimeFormat
     * @return
     */
    public static String formatTimestamp(long timestamp, TimeZone timezone, Locale locale, EasyDateTimeFormat dateTimeFormat) {
        return formatTimestamp(timestamp, TimeZone.getDefault(), locale, dateTimeFormat.toString());
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @param locale
     * @param datePattern
     * @return
     */
    public static String formatTimestamp(long timestamp, TimeZone timezone, Locale locale, String datePattern) {
        final java.util.Date date = new java.util.Date(timestamp);
        final Calendar calendar = new GregorianCalendar(timezone, locale);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, locale);

        calendar.setTime(date);
        dateFormat.setCalendar(calendar);

        return dateFormat.format(date);
    }

    /**
     *
     * @param timestamp
     * @return
     */
    public static java.util.Date convertTimestampToDate(long timestamp) {
        return convertTimestampToDate(timestamp, TimeZone.getDefault());
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @return
     */
    public static java.util.Date convertTimestampToDate(long timestamp, TimeZone timezone) {
        return convertTimestampToDate(timestamp, timezone, Locale.ITALIAN, EasyDateTimeFormat.FULL_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @param locale
     * @return
     */
    public static java.util.Date convertTimestampToDate(long timestamp, Locale locale) {
        return convertTimestampToDate(timestamp, TimeZone.getDefault(), locale, EasyDateTimeFormat.FULL_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @param locale
     * @param dateTimeFormat
     * @return
     */
    public static java.util.Date convertTimestampToDate(long timestamp, TimeZone timezone, Locale locale, EasyDateTimeFormat dateTimeFormat) {
        return convertTimestampToDate(timestamp, TimeZone.getDefault(), locale, dateTimeFormat.toString());
    }

    /**
     *
     * @param timestamp
     * @param timezone
     * @param locale
     * @param datePattern
     * @return
     */
    public static java.util.Date convertTimestampToDate(long timestamp, TimeZone timezone, Locale locale, String datePattern) {
        final String formattedDate = formatTimestamp(timestamp, timezone, locale, datePattern);
        final java.util.Date date = parseFormattedDate(formattedDate, locale, datePattern);

        return date;
    }

    /**
     *
     *
     * @param formattedDate
     * @param locale
     * @param datePattern
     * @return
     */
    public static java.util.Date parseFormattedDate(String formattedDate, Locale locale, String datePattern) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, locale);
        java.util.Date date = null;
        try {
            date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            LOGGER.error("[ConvertUtil::convertFormattedDateToDate] ParseException: " + e.getMessage(), e);
        }

        return date;
    }
    
   

    private DateUtil() {
        /* NOP */
    }
    
    /**
    *
    *
    * @param data
    * @param datePattern
    * @return
    */
    public static LocalDate parseLocalDate(String data, String datePattern) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
    	formatter = formatter.withLocale( Locale.ITALY );
    	return LocalDate.parse(data, formatter);
    }

}
