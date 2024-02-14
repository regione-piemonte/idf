/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author 71740 (Simone Cornacchia)
 */
public final class EasyDateTimeFormat {

    public static final EasyDateTimeFormat FULL_FORMAT = new EasyDateTimeFormat(Pattern.values());

    public static final String DEFAULT_DATE_SEPARATOR = "/";

    public static final String DEFAULT_TIME_SEPARATOR = ":";

    public static final String DEFAULT_DATE_TIME_SEPARATOR = org.apache.commons.lang3.StringUtils.EMPTY;

    public static final String DEFAULT_TIME_MILLIS_SEPARATOR = ".";

    public static final String DEFAULT_MILLIS_TIMEZONE_SEPARATOR = org.apache.commons.lang3.StringUtils.EMPTY;

    private String dateSeparator = DEFAULT_DATE_SEPARATOR;

    private String timeSeparator = DEFAULT_TIME_SEPARATOR;

    private String dateTimeSeparator = DEFAULT_DATE_TIME_SEPARATOR;

    private String timeMillisSeparator = DEFAULT_TIME_MILLIS_SEPARATOR;

    private String millisTimezoneSeparator = DEFAULT_MILLIS_TIMEZONE_SEPARATOR;

    private final Set<EasyDateTimeFormat.Pattern> patterns;

    private String stringRepresentation;

    /**
     * Constructor.
     */
    public EasyDateTimeFormat() {
        this.patterns = EnumSet.of(Pattern.DATE);
    }

    /**
     * Constructor.
     *
     * @param patterns
     */
    public EasyDateTimeFormat(EasyDateTimeFormat.Pattern... patterns) {
        this();

        this.addPatterns(patterns);
    }

    /**
     * @param dateSeparator the dateSeparator to set
     */
    public void setDateSeparator(String dateSeparator) {
        this.dateSeparator = dateSeparator;
    }

    /**
     * @param timeSeparator the timeSeparator to set
     */
    public void setTimeSeparator(String timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    /**
     * @param dateTimeSeparator the dateTimeSeparator to set
     */
    public void setDateTimeSeparator(String dateTimeSeparator) {
        this.dateTimeSeparator = dateTimeSeparator;
    }

    /**
     * @param timeMillisSeparator the timeMillisSeparator to set
     */
    public void setTimeMillisSeparator(String timeMillisSeparator) {
        this.timeMillisSeparator = timeMillisSeparator;
    }

    /**
     * @param millisTimezoneSeparator the millisTimezoneSeparator to set
     */
    public void setMillisTimezoneSeparator(String millisTimezoneSeparator) {
        this.millisTimezoneSeparator = millisTimezoneSeparator;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (this.stringRepresentation == null) {
            this.stringRepresentation = this.buildToStringRepresentation();
        }

        return this.stringRepresentation;
    }

    /**
     *
     * @return
     */
    private String buildToStringRepresentation() {
        final StringBuilder builder = new StringBuilder();

        for (final EasyDateTimeFormat.Pattern pattern : this.patterns) {
            switch (pattern) {
            case DATE:
                builder.append(MessageFormat.format(pattern.mask(), this.dateSeparator));
                break;
            case TIME:
                builder.append(this.dateTimeSeparator)
                       .append(MessageFormat.format(pattern.mask(), this.timeSeparator));
                break;
            case MILLIS:
                builder.append(this.timeMillisSeparator)
                       .append(pattern.mask());
                break;
            case TIMEZONE:
                builder.append(this.millisTimezoneSeparator)
                       .append(pattern.mask());
                break;
            default:
                break;
            }
        }

        return builder.toString();
    }

    /**
     *
     * @param patterns
     */
    private void addPatterns(EasyDateTimeFormat.Pattern... patterns) {
        if (ArrayUtils.isNotEmpty(patterns)) {
            Set<EasyDateTimeFormat.Pattern> addenda;

            if (patterns.length == 1) {
                addenda = EnumSet.of(patterns[0]);
            } else {
                addenda = EnumSet.of(patterns[0], ArrayUtils.subarray(patterns, 1, patterns.length));
            }

            this.patterns.addAll(addenda);
        }
    }

    /**
     *
     * @author 71740 (Simone Cornacchia)
     */
    public enum Pattern {

        DATE("dd{0}MM{0}yyyy"),
        TIME("HH{0}mm{0}ss"),
        MILLIS("SSS"),
        TIMEZONE("Z z"),
        ;

        private String mask;

        public String mask() {
            return this.mask;
        }

        /**
         * Constructor.
         *
         * @param mask
         */
        private Pattern(String mask) {
            this.mask = mask;
        }

    }

}
