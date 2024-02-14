/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;



import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe di funzioni di utilit&agrave; sulle stringhe.
 * 
 */
public final class StringUtils {

    /**
     * Binary unit size.
     */
    private static final int BINARY_UNIT = 1024;

    /**
     * <code>SI</code> unit size.
     */
    private static final int SI_UNIT = 1000;

    /**
     * Convert byte size into human-readable representation, using binary units.
     *
     * @param bytes the byte size to convert into human-readable representation
     * @return byte size human-readable representation
     */
    public static String toHumanReadableSize(byte[] bytes) {
        return toHumanReadableSize(org.apache.commons.lang3.ArrayUtils.isNotEmpty(bytes) ? bytes.length : -1);
    }

    /**
     * Convert byte size into human-readable representation, using binary units.
     *
     * @param byteCount the byte size to convert into human-readable representation
     * @return byte size human-readable representation
     */
    public static String toHumanReadableSize(long byteCount) {
        return toHumanReadableSize(byteCount, false);
    }

    /**
     * Convert byte size into human-readable representation, handling both SI units and binary units.
     *
     * @param byteCount the byte size to convert into human-readable representation
     * @param si convert in SI units if <code>true</code>, otherwise in binary units
     * @return byte size human-readable representation
     * @see http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
     */
    public static String toHumanReadableSize(long byteCount, boolean si) {
        final int unit = si ? SI_UNIT : BINARY_UNIT;

        if (byteCount < unit) {
            return byteCount + " B";
        }

        final int exp = (int) (Math.log(byteCount) / Math.log(unit));

        final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "i" : "");

        final String byteCountReadableRepresentation = String.format("%.1f %sB", byteCount / Math.pow(unit, exp), pre);

        return byteCountReadableRepresentation;
    }


    /**
     * 
     * @see org.apache.commons.lang3.StringUtils#isEmpty(CharSequence)
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    /**
     * 
     * @see org.apache.commons.lang3.StringUtils#isNotEmpty(CharSequence)
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    /**
     * 
     * @see org.apache.commons.lang3.StringUtils#isBlank(CharSequence)
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    /**
     * 
     * @see org.apache.commons.lang3.StringUtils#isNotBlank(CharSequence)
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

    /**
     * Check that the given string does not exceed the given maximum length.
     * 
     * @param s
     * @param maxLenght
     * @param field
     * @return
     */
    public static boolean checkStringMaxLenght(String s, int maxLenght) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(s) && s.length() <= maxLenght;
    }

    /**
     * Check that the given string is at least of the given maximum length.
     * 
     * @param s
     * @param maxLenght
     * @param field
     * @return
     */
    public static boolean checkStringMinLenght(String s, int minLenght) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(s) && s.length() >= minLenght;
    }

    /**
     * Check that the given alphanumeric string does not exceed the given maximum length.
     * 
     * @param s
     * @param maxLenght
     * @param field
     * @return
     */
    public static boolean checkAlphanumericStringMaxLenght(String s, int maxLenght) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(s) && s.length() <= maxLenght;
    }

    /**
     * Check that the given alphanumeric string is at least of the given maximum length.
     * 
     * @param s
     * @param maxLenght
     * @param field
     * @return
     */
    public static boolean checkAlphanumericStringMinLenght(String s, int minLenght) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(s) && s.length() >= minLenght;
    }

    /**
     * Verifica se una stringa &egrave; valida rispetto ad una data espressione regolare.
     *
     * @param string La stringa da verificare
     * @param regex L'espressione regolare
     * @return {@code true} se la stringa &egrave; valida, {@code false} altrimenti
     */
    public static boolean isStringValid(String string, String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    /**
     * Verifica se una stringa non &egrave; valida rispetto ad una data espressione regolare.
     *
     * @param string La stringa da verificare
     * @param regex L'espressione regolare
     * @return {@code true} se la stringa non &egrave; valida, {@code false} altrimenti
     */
    public static boolean isNotStringValid(String string, String regex) {
        return ! isStringValid(string, regex);
    }

    /**
     * Constructor.
     */
    private StringUtils() {
        /* NOP */
    }

    public static double stringToDouble(String fatturatoStr) {
		double valueFatturato;
		if(fatturatoStr.contains(",")) {
			valueFatturato = Double.parseDouble(fatturatoStr.replace(",", "."));
		}else {
			valueFatturato = Double.parseDouble(fatturatoStr);
		}
		return valueFatturato;
	}
    
    public static String defaultValue(String s, String def) {
    	if(StringUtils.isEmpty(s)) {
    		return def;
    	}
    	return s;
    }
    
    public static String[] sort(String[] array) {
		Arrays.sort(array, new Comparator<String>() {

		    @Override
		    public int compare(String s1, String s2) {
		    	if(s1.length() == s2.length()) {
		    		return s1.compareTo(s2);		    		
		    	}
		    	return s1.length() - s2.length();
		    }
		});
		return array;
	}
    
}
