/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;



/**
 *
 * @author  1346 (Enrico Fusaro)
 */
public final class ConvertUtil {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".util");

    /**
     * Pattern per formattazione data (<code>dd/MM/yyyy</code>).
     */
    public static final String FORMAT_DATE_STANDARD   = "dd/MM/yyyy";

    /**
     * Pattern per formattazione data (<code>dd_MM_yyyy</code>).
     */
    public static final String FORMAT_DATE_UNDERSCORE = "dd_MM_yyyy";

    /**
     * Lista dei formati decimali.
     */
    private static final  Map<Integer, String> DECIMAL_FORMATS = new HashMap<Integer, String>();

    /**
     *
     * @param dt
     * @return
     */
    public static String convertToString(java.util.Date dt) {
        if (dt == null) {
            return null;
        } else {
            final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STANDARD, Locale.getDefault());

            return sdf.format(dt);
        }
    }

    /**
     *
     * @param dt
     * @param format
     * @return
     */
    public static String convertToString(java.util.Date dt, String format) {
        if (dt == null) {
            return null;
        } else {
            final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

            return sdf.format(dt);
        }
    }

    /**
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static java.util.Date convertToDate(String s) throws Exception {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STANDARD, Locale.getDefault());
            try {
                final java.util.Date result = sdf.parse(s);

                return result;
            } catch (ParseException e) {
                throw new Exception("Errore nel parsing della data " + s, e);
            }
        } else {
            return null;
        }
    }
    
    /**
    *
    * @param s
    * @return
    * @throws Exception
    */
   public static java.util.Date convertToDate(String s, String format) throws Exception {
       if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
           final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
           try {
               final java.util.Date result = sdf.parse(s);

               return result;
           } catch (ParseException e) {
               throw new Exception("Errore nel parsing della data " + s, e);
           }
       } else {
           return null;
       }
   }

    /**
     *
     * @param s
     * @return
     */
    public static Calendar convertToCalendar(String s) {
        Calendar result = null;
        try {
            final java.util.Date date = convertToDate(s);
            if (date != null) {
                result = Calendar.getInstance();
                result.setTime(date);
            }
        } catch (Exception e) {
            LOGGER.error("[ConvertUtil::convertToCalendar] ERROR: " + e.getMessage(), e);

            return null;
        }
        return result;
    }

    /**
     *
     * @param s
     * @return
     */
    public static java.sql.Date convertToSqlDate(String s) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(s)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STANDARD, Locale.getDefault());
            try {
                return new Date(sdf.parse(s).getTime());
            } catch (ParseException e) {
                LOGGER.error("[ConvertUtil::convertToSqlDate] ERROR: " + e.getMessage(), e);

                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToString(Integer i) {
        if (i == null) {
            return null;
        } else {
            return Integer.toString(i);
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToString(Long i) {
        if (i == null) {
            return null;
        } else {
            return Long.toString(i);
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToString(Double i) {
        if (i == null) {
            return null;
        } else {
            return Double.toString(i).replace(".", ",");
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToString(Float i) {
        if (i == null) {
            return null;
        } else {
            return Float.toString(i).replace(".", ",");
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToString(BigDecimal i) {
        if (i == null) {
            return null;
        } else {
            return i.toString().replace(".", ",");
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static String convertToStringNoReplace(BigDecimal i) {
        if (i == null) {
            return null;
        } else {
            return i.toString();
        }
    }

    /**
     * Converte un {@link BigDecimal} in stringa
     *
     * @param number Numero da convertire
     * @param decimali Decimali da visualizzare
     * @return Numero convertito
     */
    public static String convertToString(BigDecimal number, int decimali) {
        DecimalFormat formatter = null;
        String format = null;
        String stringValue = null;

        if (number != null) {
            format = getNumberFormat(decimali);
            formatter = new DecimalFormat(format);
            stringValue = formatter.format(number.doubleValue());
        }
        return stringValue;
    }

    /**
     * Restituisce il formato numerico richiesto
     *
     * @param decimali Decimali da visualizzare
     * @return Formato numerico
     */
    private static String getNumberFormat(int decimali) {
        String format = DECIMAL_FORMATS.get(decimali);
        if (format == null) {
            final StringBuilder formatBuilder  = new StringBuilder("#,##0");
            final StringBuilder decimalBuilder = new StringBuilder();

            for (int i = 0; i < decimali; i++) {
                decimalBuilder.append("0");
            }
            if (decimalBuilder.length() > 0) {
                formatBuilder.append(".").append(decimalBuilder);
            }

            format = formatBuilder.toString();

            DECIMAL_FORMATS.put(decimali, format);
        }

        return format;
    }

    /**
     *
     * @param s
     * @return
     */
    public static Integer convertToInteger(String s) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            return null;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToInteger] ERROR: " + e.getMessage(), e);

                return null;
            }
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static Double convertToDouble(String s) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            return null;
        } else {
            try {
                return new Double(Double.parseDouble(s.replaceAll(",", ".")));
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToDouble] ERROR: " + e.getMessage(), e);

                return null;
            }
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static Float convertToFloat(String s) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            return null;
        } else {
            try {
                return new Float(Double.parseDouble(s.replaceAll(",", ".")));
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToFloat] ERROR: " + e.getMessage(), e);

                return null;
            }
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static Float convertToFloat(BigDecimal s) {
        if (s == null) {
            return null;
        } else {
            try {
                return new Float(s.floatValue());
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToFloat] ERROR: " + e.getMessage(), e);

                return null;
            }
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static Long convertToLong(String s) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            return null;
        } else {
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToLong] ERROR: " + e.getMessage(), e);

                return null;
            }
        }
    }

    /**
     * Converte una stringa in {@link BigDecimal}.<br />
     * Accetta anche numeri con la virgola come separatore dei decimali.
     * Non sono ammessi separatori di migliaia.
     *
     * @param value Valore da convertire
     * @return {@link BigDecimal} che rappresenta il valore
     */
    public static BigDecimal convertToBigDecimal(String value) {
        BigDecimal number = null;
        LOGGER.debug("------- VALORE DA CONVERTIRE ----- *"+value+"*");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            try {
            	 LOGGER.debug("------- 1 ----- "+value.length());
                // Si converte l'eventuale virgola in punto
                number = new BigDecimal(value.replaceAll(",", "."));
                LOGGER.debug("------- 2 ----- ");
            } catch (Exception e) {
                LOGGER.error("[ConvertUtil::convertToBigDecimal] ERROR: " + e.getMessage(), e);
            }
            LOGGER.debug("------- 3 ----- ");
        }
        LOGGER.debug("------- 4 ----- ");
        return number;
    }	

    /**
     *
     * @param b
     * @return
     */
    public static int convertToInteger(BigDecimal b) {
        int i = 0;
        if (b != null) {
            i = b.intValue();
        }
        return i;
    }

    /**
     *
     * @param b
     * @return
     */
    public static Long convertToLong(BigDecimal b) {
        Long l = null;
        if (b != null) {
            l = b.longValue();
        }
        return l;
    }

    /**
     * Restituisce l'anno di una data
     *
     * @param value Data da cui estrarre l'anno
     * @return Anno della data
     * @throws Exception Errore durante la conversione della data
     */
    public static int getYear(String value) throws Exception {
        return getYear(convertToDate(value));
    }

    /**
     * Restituisce l'anno di una data
     *
     * @param date Data da cui estrarre l'anno
     * @return Anno della data
     */
    public static int getYear(java.util.Date date) {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     *
     * @param b
     * @return
     */
    public static int flagPdf(BigDecimal b) {
        int result = 0;
        if (b != null) {
            result = b.intValue();
        }
        return result;
    }

    /**
     *
     * @param s
     * @return
     */
    public static int flagPdf(String s) {
        int result = 0;
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(s)) {
            result = Integer.parseInt(s);
        }
        return result;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String flagPdfString(String s) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
            return EMPTY;
        }
        return s;
    }

    /**
     *
     * @param i
     * @return
     */
    public static BigDecimal convertToBigDecimal(int i) {
        return BigDecimal.valueOf(i);
    }

    /**
     *
     * @param b
     * @return
     */
    public static String flagPdfString(BigDecimal b) {
        if (b == null) {
            return EMPTY;
        }
        return convertToString(b);
    }

    /**
     *
     * @param f
     * @return
     */
    public static BigDecimal convertToBigDecimal(float f) {
        return BigDecimal.valueOf(f);
    }

    /**
     *
     * @param l
     * @return
     */
    public static BigDecimal convertToBigDecimal(long l) {
        return BigDecimal.valueOf(l);
    }

    /**
     *
     * @param b
     * @return
     */
    public static float convertToFloatPrimitive(BigDecimal b) {
        float f = 0;
        if (b != null) {
            f = convertToFloat(b);
        }
        return f;
    }

    /**
     *
     * @param list
     * @return
     */
    public static String convertToSqlIn(List<String> list) {
        final StringBuilder in = new StringBuilder();
        boolean first = true;
        if (list != null) {
            for (final String id : list) {
                if (first) {
                    first = false;
                } else {
                    in.append(",");
                }
                in.append(id);
            }
        }
        return in.toString();
    }

    /**
     *
     * @param list
     * @return
     */
    public static String[] convertToStringArray(List<String> list) {
        String[] result = null;
        if (list != null) {
            result = new String[list.size()];

            list.toArray(result);
        }
        return result;
    }

    /**
     *
     * @param d
     * @return
     */
    public static BigDecimal convertToBigDecimal(double d) {
        return BigDecimal.valueOf(d);
    }

    /**
     *
     * @param b
     * @return
     */
    public static double convertToDouble(BigDecimal b) {
        double d = 0;
        if (b != null) {
            d = b.doubleValue();
        }
        return d;
    }

    /**
     *
     * @param value
     * @return
     */
    public static Integer toInteger(String value) {
        return isEmpty(value)? null : Integer.valueOf(value);
    }

    /**
     *
     * @param value
     * @return
     */
    public static String toStringValue(BigDecimal value) {
        return value == null ? null : value.toString();
    }

    /**
     *
     * @param value
     * @return
     */
    public static String toStringValue(Double value) {
        return value == null ? null : value.toString();
    }

    /**
     *
     * @param value
     * @return
     */
    public static String toStringValue(Object value) {
        return value == null ? null : value.toString();
    }

    /**
     *
     * @param value
     * @return
     */
    public static Long toLong(BigDecimal value) {
        return value == null ? null : value.longValue();
    }

    /**
     *
     * @param value
     * @return
     */
    public static BigDecimal toBigDecimal(Integer value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }

    /**
     *
     * @param value
     * @return
     */
    public static BigDecimal toBigDecimal(Double value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }

    

    /**
     *
     * @param value
     * @return
     */
    public static Integer toInteger(BigDecimal value) {
        return value == null ? null : value.intValue();
    }

    /**
     *
     * @param value
     * @return
     */
    public static Double toDouble(BigDecimal value) {
        return value == null ? null : value.doubleValue();
    }

    

    /**
     *
     * @param input
     * @return
     */
    public static byte[] toBytes(ByteArrayInputStream input) {
        byte[] result = null;

        if (input != null) {
            try {
                result = new byte[input.available()];

                input.read(result);
            } catch (IOException e) {
                LOGGER.error("[ConvertUtil::toBytes] IOException: " + e.getMessage(), e);

                result = null;
            }
        }

        return result;
    }

    /**
     *
     * @param numero
     * @return
     */
    public static String formattazioneProgressivoPratica(String numero) {
        String result = EMPTY;

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(numero) && NumberUtils.isNumber(numero)) {
            result = String.format("%04d", Integer.parseInt(numero));
        }

        return result;
    }

    /**
     * 
     * @param value
     * @param token
     * @return
     */
    public static String[] toArray(String value, String token){
        String[] result = null;
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(token)) {
            final StringTokenizer st = new StringTokenizer(value, token);
            final int count = st.countTokens();
            result = new String[count];
            for (int i = 0; i < count; i++) {
                result[i] = st.nextToken();
            }
        }
        return result == null ? new String[0]:result;
    }

    /**
     * 
     * @param value
     * @param token
     * @return
     */
    public static List<String> toArrayList(String value, String token) {
        final List<String> result = new ArrayList<String>();    
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(token)) {
            final StringTokenizer st = new StringTokenizer(value, token);
            final int count = st.countTokens();
            for (int i = 0; i < count; i++) {
                result.add(st.nextToken());
            }
        }

        return result;
    }
    
	public static boolean checkIsNullOrEmpty(String stringa) {
		boolean notEmpty = false;
		if(stringa!=null && !stringa.equalsIgnoreCase("")){
			notEmpty = true;
		}
		return notEmpty;
	}
	
	public static boolean checkListIsNullOrEmpty(java.util.List<String> lista) {
		boolean notEmpty = false;
		if(lista!= null && lista.size() != 0 && lista.size()  > 0){
			notEmpty = true;
		}
		return notEmpty;
	}
	
	public static boolean checkIntIsNullOrZero(int intero) {
		boolean notEmpty = false;
		if(intero != 0){
			notEmpty = true;
		}
		return notEmpty;
	}
	
	public static String createStringForQuery(java.util.List<String> lista) {
		String stringa ="(";
		for(int i=0;i<lista.size();i++) {
			stringa+= "'"+lista.get(i)+"'";
			if(i!=lista.size()-1) {
				stringa+=",";
			} else {
				stringa+=")";
			}
		}
		return stringa;
	}
	
	
	
	
	
	public static String defaultValueBigDecimal(BigDecimal value, int defaultValue) {
		if(value== null) {
			return convertToString(defaultValue);
		}
		return convertToString(value);
	}
	
	public static String defaultValueInteger(Integer value, int defaultValue) {
		if(value== null) {
			return convertToString(defaultValue);
		}
		return convertToString(value);
	}
	
	public static boolean getBooleanFromString(String s, String trueValue) {
    	if(trueValue.equals(s)) {
    		return true;
    	}
    	return false;
    }
	
	
	 
}
