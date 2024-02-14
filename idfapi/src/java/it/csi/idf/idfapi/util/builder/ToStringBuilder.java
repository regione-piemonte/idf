/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.builder;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author 71740 (Cornacchia Simone)
 */
public final class ToStringBuilder {

    /**
     * Enumerazione degli stili della rappresentazione testuale.
     *
     * @author 71740 (Simone Cornacchia)
     */
    public enum Style {
        /**
         * Stile per rappresentazione testuale <em>profonda</em> e <em>su pi&ugrave; righe</em>.
         */
        MULTILINE_RECURSIVE,

        /**
         * Stile per rappresentazione testuale <em>su pi&ugrave; righe</em>.
         */
        RECURSIVE,
        ;
    }

    /**
     * A singleton <em>multiline</em> <em>recursive</em> {@link ToStringStyle} instance, configured as follows:
     * <ul>
     *   <li><code>arrayStart</code>: <code>"["</code></li>
     *   <li><code>arrayEnd</code>: <code>"]"</code></li>
     *   <li><code>contentStart</code>: <code>"("</code></li>
     *   <li><code>fieldSeparator</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + "  "</code></li>
     *   <li><code>fieldNameValueSeparator</code>: <code> : </code></li>
     *   <li><code>fieldSeparatorAtStart</code>: <code>true</code></li>
     *   <li><code>contentEnd</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + ")"</code></li>
     *   <li><code>nullText</code>: <code>"&lt;NULL&gt;"</code></li>
     * </ul>
     */
    private static final ToStringStyle MULTILINE_RECURSIVE_TOSTRING_STYLE = new MultilineRecursiveToStringStyle() {

        private static final long serialVersionUID = -1L;

    {
        this.setFieldNameValueSeparator(" = ");
        this.setNullText("<NULL>");
    }};

    /**
     * A singleton <em>recursive</em> {@link ToStringStyle} instance, configured as follows:
     * <ul>
     *   <li><code>arrayStart</code>: <code>"["</code></li>
     *   <li><code>arrayEnd</code>: <code>"]"</code></li>
     *   <li><code>contentStart</code>: <code>"("</code></li>
     *   <li><code>fieldSeparator</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + "  "</code></li>
     *   <li><code>fieldNameValueSeparator</code>: <code> : </code></li>
     *   <li><code>fieldSeparatorAtStart</code>: <code>true</code></li>
     *   <li><code>contentEnd</code>: <code>{@link org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR} + ")"</code></li>
     *   <li><code>nullText</code>: <code>"&lt;NULL&gt;"</code></li>
     * </ul>
     */
    private static final ToStringStyle RECURSIVE_TOSTRING_STYLE = new RecursiveToStringStyle() {

        private static final long serialVersionUID = 1L;

    {
        this.setFieldNameValueSeparator(" = ");
        this.setNullText("<NULL>");
    }};

    /**
     * Restituisce una rappresentazione testuale, <em>profonda</em> e <em>su pi&ugrave; righe</em>,
     * ottenuta tramite <code>Reflection API</code>, dell'oggetto fornito.
     *
     * @see ToStringBuilder#reflectionToString(Object, org.apache.commons.lang3.builder.ToStringStyle)
     * @see MultilineRecursiveToStringStyle
     * @param object l'oggetto di cui fornire una rappresentazione testuale,
     *        ottenuta tramite <code>Reflection API</code>
     * @return una rappresentazione testuale, <em>profonda</em> e <em>su pi&ugrave; righe</em>,
     *         ottenuta tramite <code>Reflection API</code>, dell'oggetto fornito.
     */
    public static <T> String objectToString(T object) {
        return reflectionToString((T) object, MULTILINE_RECURSIVE_TOSTRING_STYLE);
    }

    /**
     * Restituisce una rappresentazione testuale, applicando lo stile richiesto,
     * ottenuta tramite <code>Reflection API</code>, dell'oggetto fornito.
     *
     * @see ToStringBuilder#reflectionToString(Object, org.apache.commons.lang3.builder.ToStringStyle)
     * @see MultilineRecursiveToStringStyle
     * @param object l'oggetto di cui fornire una rappresentazione testuale,
     *        ottenuta tramite <code>Reflection API</code>
     * @param style lo stile richiesto
     * @return una rappresentazione testuale, applicando lo stile richiesto,
     *         ottenuta tramite <code>Reflection API</code>, dell'oggetto fornito.
     */
    public static <T> String objectToString(T object, Style style) {
        return reflectionToString(
            (T) object,
            style == Style.MULTILINE_RECURSIVE ?
                MULTILINE_RECURSIVE_TOSTRING_STYLE :
                RECURSIVE_TOSTRING_STYLE
        );
    }

    /**
     * Constructor.
     */
    private ToStringBuilder() {
        /* NO-OP */
    }

}
