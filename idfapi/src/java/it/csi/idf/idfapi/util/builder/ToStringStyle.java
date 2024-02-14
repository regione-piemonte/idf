/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.builder;

import static java.util.Locale.ITALY;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Offers a {@link ToStringStyle} instance,
 * useful for <code>DTO</code> textual representation, configured as follows:
 * <ul>
 *     <li><code>arrayStart</code>: <code>"["</code></li>
 *     <li><code>arrayEnd</code>: <code>"]"</code></li>
 *     <li><code>contentStart</code>: <code>"("</code></li>
 *     <li><code>contentEnd</code>: <code>")"</code></li>
 *     <li><code>nullText</code>: <code>"&lt;NULL&gt;"</code></li>
 *     <li><code>useShortClassName</code>: <code>true</code></li>
 *     <li><code>useIdentityHashCode</code>: <code>true</code></li>
 * </ul>
 *
 * @deprecated
 * @author scornacchia
 */
@Deprecated
public final class ToStringStyle extends org.apache.commons.lang3.builder.ToStringStyle {

    private static final long serialVersionUID = 480233369693751666L;

    /**
     * A {@link ToStringStyle} instance, configured as follows:
     * <ul>
     *     <!--<li><code>useFieldNames</code>: <code>true</code></li>-->
     *     <li><code>useShortClassName</code>: <code>true</code></li>
     *     <li><code>useIdentityHashCode</code>: <code>true</code></li>
     *     <li><code>contentStart</code>: <code>"("</code></li>
     *     <li><code>fieldSeparator</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + "  "</code></li>
     *     <li><code>fieldSeparatorAtStart</code>: <code>true</code></li>
     *     <li><code>contentEnd</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + ")"</code></li>
     *     <li><code>nullText</code>: <code>"&lt;NULL&gt;"</code></li>
     * </ul>
     */
    public static final ToStringStyle STRING_STYLE_INSTANCE = new ToStringStyle();

    /**
     * The {@link DateFormat} instance used for date formatting using italian {@link Locale}
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("E, d MMMM yyyy z", ITALY);

    /**
     * The {@link DateFormat} instance used for date and time formatting using italian {@link Locale}.
     */
    @SuppressWarnings("unused")
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("E, d MMMM yyyy HH.mm.ss z", ITALY);

    /**
     * Default indentation string
     */
    private static final String DEFAULT_IDENTATION = "  ";

    /**
     * The indentation string.
     */
    private String indentation = DEFAULT_IDENTATION;

    /**
     * The indentation level
     */
    private int indentationLevel = 0;

    /**
     * Modified internally to indicate whether array content should be multiline
     */
    private boolean multiLineArrayContent = true;

    /**
     * The original contentEnd.
     */
    private final String originalContentEnd;

    /**
     * The original arrayEnd
     */
    private final String originalArrayEnd;

    /**
     * The original arraySeparator
     */
    private final String originalArraySeparator;

    /**
     * The original arrayStart
     */
    private final String originalArrayStart;

    /**
     * The original fieldSeparator
     */
    private final String originalFieldSeparator;

    /**
     * Constructor.
     */
    protected ToStringStyle() {
        super();

        this.setArrayStart("[");
        this.setArrayEnd("]");
        this.setContentStart("(");
        this.setContentEnd(")");
        this.setNullText("<NULL>");

        this.originalArrayEnd = this.getArrayEnd();
        this.originalArraySeparator = this.getArraySeparator();
        this.originalArrayStart = this.getArrayStart();
        this.originalContentEnd = this.getContentEnd();
        this.originalFieldSeparator = this.getFieldSeparator();

        this.setFieldSeparatorAtStart(false);
        this.setUseShortClassName(true);
        this.setUseIdentityHashCode(true);

        this.updateToStyleStrings();
    }

    /**
     * Ensure <code>Singleton</code> after serialization.
     *
     * @return the singleton
     */
    private Object readResolve() {
        return STRING_STYLE_INSTANCE;
    }

    /**
     * Set the field separator based on the indentation level (always has at
     * least one level of indentation).
     */
    private void updateFieldSeparator() {
        final StringBuilder fieldSeparator = new StringBuilder();
        fieldSeparator.append(this.originalFieldSeparator);
        fieldSeparator.append(LINE_SEPARATOR);

        this.appendIndentation(fieldSeparator);
        this.setFieldSeparator(fieldSeparator.toString());
    }

    /**
     * @see #appendInternal(StringBuffer, String, Object, boolean)
     */
    protected void appendInternal(StringBuffer buffer, String fieldName, Object value) {
        this.appendInternal(buffer, fieldName, value, true);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendInternal(java.lang.StringBuffer, java.lang.String, java.lang.Object, boolean)
     */
    @Override
    protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail) {
        if (value instanceof Date) {
            if (detail) {
                this.appendDetail(buffer, fieldName, (Date) value);
            } else {
                this.appendSummary(buffer, fieldName, value);
            }
        } else {
            super.appendInternal(buffer, fieldName, value, detail);
        }
    }

    /**
     *
     * @param buffer
     * @param fieldName
     * @param value
     */
    private void appendDetail(StringBuffer buffer, String fieldName, Date value) {
        synchronized (DATE_FORMAT) {
            buffer.append(DATE_FORMAT.format(value));
        }
    }

    /**
     * Append the <code>indendation</code> String to the buffer
     * <code>indentationLevel</code> times.
     * <p>
     * Equivalent to <code>appendIndentation(buffer, 0)</code>
     *
     * @param buffer
     *            the <code>StringBuffer</code> to populate
     */
    protected void appendIndentation(StringBuilder buffer) {
        this.appendIndentation(buffer, 0);
    }

    /**
     * Append the <code>indendation</code> String to the buffer
     * <code>indentationLevel</code> times.
     *
     * @param buffer
     *            the <code>StringBuffer</code> to populate
     * @param adjustment
     *            an adjustment to the indentationLevel
     */
    protected void appendIndentation(StringBuilder buffer, int adjustment) {
        for (int i = 0; i < this.indentationLevel + adjustment; i++) {
            buffer.append(this.indentation);
        }
    }

    /**
     * Set the content end based on the indentation level.
     */
    private void updateContentEnd() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(LINE_SEPARATOR);
        this.appendIndentation(buffer, -1);
        buffer.append(this.originalContentEnd);
        this.setContentEnd(buffer.toString());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.lang.Object[])
     */
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array) {
        final boolean oldMultiLineArrayContent = this.multiLineArrayContent;
        this.multiLineArrayContent = true;
        this.increaseIndentationLevel();
        super.appendDetail(buffer, fieldName, array);
        this.multiLineArrayContent = oldMultiLineArrayContent;
        this.decreaseIndentationLevel();
    }

    /**
     * Use an "array" style for Collections. {@inheritDoc}
     */
    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Collection)
     */
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
        buffer.append(this.getArrayStart());
        this.increaseIndentationLevel();
        int i = 0;
        final Iterator<?> iter = coll.iterator();
        while (iter.hasNext()) {
            if (i > 0) {
                buffer.append(this.getArraySeparator());
            }
            buffer.append(LINE_SEPARATOR);
            this.appendIndentation(new StringBuilder(buffer));
            final Object item = iter.next();
            if (item == null) {
                this.appendNullText(buffer, fieldName);

            } else {
                this.appendInternal(buffer, fieldName, item);
            }
            i++;
        }
        this.decreaseIndentationLevel();
        if (i != 0) {
            buffer.append(LINE_SEPARATOR);
            this.appendIndentation(new StringBuilder(buffer));
        }
        buffer.append(this.getArrayEnd());
    }

    /**
     * Use a "map" style for Maps. {@inheritDoc}
     */
    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Map)
     */
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Map<?, ?> map) {
        buffer.append("{");
        this.increaseIndentationLevel();
        int i = 0;
        Entry<?, ?> entry;
        for (final Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
            if (i > 0) {
                buffer.append(this.getArraySeparator());
            }
            buffer.append(LINE_SEPARATOR);
            this.appendIndentation(new StringBuilder(buffer));
            entry = (Entry<?, ?>) iter.next();
            if (entry == null) {
                this.appendNullText(buffer, fieldName);
            } else {
                this.appendFieldStart(buffer, (String) entry.getKey());
                this.appendInternal(buffer, (String) entry.getKey(), entry.getValue());
            }
            i++;
        }
        this.decreaseIndentationLevel();
        if (i != 0) {
            buffer.append(LINE_SEPARATOR);
            this.appendIndentation(new StringBuilder(buffer));
        }
        buffer.append("}");
    }

    /**
     * Update <code>arrayEnd</code> based on <code>indentationLevel</code>
     */
    private void updateArrayEnd() {
        final StringBuilder arrayEnd = new StringBuilder();
        if (this.multiLineArrayContent) {
            arrayEnd.append(LINE_SEPARATOR);
            this.appendIndentation(arrayEnd, -1);
        }
        arrayEnd.append(this.originalArrayEnd);
        this.setArrayEnd(arrayEnd.toString());
    }

    /**
     * Update <code>arraySeparator</code> based on
     * <code>indentationLevel</code>
     */
    private void updateArraySeparator() {
        final StringBuilder arraySeparator = new StringBuilder();
        arraySeparator.append(this.originalArraySeparator);
        if (this.multiLineArrayContent) {
            arraySeparator.append(LINE_SEPARATOR);
            this.appendIndentation(arraySeparator);
        }
        this.setArraySeparator(arraySeparator.toString());
    }

    /**
     * Update the <code>arrayStart</code> value.<br />
     * If <code>multiLineArrayContent</code> is true
     * then a new line plus indendation is used before the { character.<br />
     * Otherwise it is just the { character.
     */
    private void updateArrayStart() {
        final StringBuilder arrayStart = new StringBuilder();
        arrayStart.append(this.originalArrayStart);
        if (this.multiLineArrayContent) {
            arrayStart.append(LINE_SEPARATOR);
            this.appendIndentation(arrayStart);
        }
        this.setArrayStart(arrayStart.toString());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendStart(java.lang.StringBuffer, java.lang.Object)
     */
    @Override
    public void appendStart(StringBuffer buffer, Object object) {
        this.increaseIndentationLevel();
        super.appendStart(buffer, object);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendEnd(java.lang.StringBuffer, java.lang.Object)
     */
    @Override
    public void appendEnd(StringBuffer buffer, Object object) {
        this.removeEmptyContentStart(buffer);
        super.appendEnd(buffer, object);
        this.decreaseIndentationLevel();
    }

    /**
     * If there is an empty content start, then remove it.
     *
     * @param buffer the <code>StringBuffer</code> to populate
     */
    private void removeEmptyContentStart(StringBuffer buffer) {
        final int len = buffer.length();
        final StringBuilder emptyContentStart = new StringBuilder();
        emptyContentStart.append(getContentStart());
        emptyContentStart.append(LINE_SEPARATOR);
        appendIndentation(emptyContentStart);
        final int emptyContentStartLen = emptyContentStart.length();
        if (len > 0 && emptyContentStartLen > 0 && len >= emptyContentStartLen) {
            boolean match = true;
            for (int i = 0; i < emptyContentStartLen; i++) {
                if (buffer.charAt(len - 1 - i) != emptyContentStart.charAt(emptyContentStartLen - 1 - i)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                // Don't discard the contentStart character.
                buffer.setLength(len - emptyContentStartLen + 1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendContentEnd(java.lang.StringBuffer)
     */
    @Override
    protected void appendContentEnd(StringBuffer buffer) {
        super.appendContentEnd(buffer);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendContentStart(java.lang.StringBuffer)
     */
    @Override
    protected void appendContentStart(StringBuffer buffer) {
        super.appendContentStart(buffer);
        buffer.append(LINE_SEPARATOR);
        this.appendIndentation(new StringBuilder(buffer));
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendFieldSeparator(java.lang.StringBuffer)
     */
    @Override
    protected void appendFieldSeparator(StringBuffer buffer) {
        super.appendFieldSeparator(buffer);
    }

    /**
     * @return the indentation
     */
    protected String getIndentation() {
        return this.indentation;
    }

    /**
     * @param indentation the indentation to set
     */
    protected void setIndentation(String indentation) {
        this.indentation = indentation;
    }

    /**
     * @return the indentationLevel
     */
    protected int getIndentationLevel() {
        return this.indentationLevel;
    }

    /**
     * Increase the indentation level.
     */
    protected void increaseIndentationLevel() {
        this.indentationLevel++;
        this.updateToStyleStrings();
    }

    /**
     * Decrease the indentation level.
     */
    protected void decreaseIndentationLevel() {
        this.indentationLevel--;
        this.updateToStyleStrings();
    }

    /**
     * Update strings used internally by {@link ToStringStyle}.<br />
     *
     * <em>Do not call this method yourself !</em><br />
     * This will be invoked by
     * <code>increaseIndenationLevel</code> and
     * <code>decreaseIndentationLevel</code>.
     */
    protected void updateToStyleStrings() {
        this.updateArrayEnd();
        this.updateArrayStart();
        this.updateArraySeparator();
        this.updateContentEnd();
        this.updateFieldSeparator();
    }

}
