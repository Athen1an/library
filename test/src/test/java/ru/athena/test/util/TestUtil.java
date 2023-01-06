package ru.athena.test.util;

import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

/**
 * Test utility class
 */
public final class TestUtil {

    private TestUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Create JSON UTF-8 MediaType
     */
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    /**
     * Create a FormattingConversionService which use ISO date format, instead of the localized one.
     *
     * @return the FormattingConversionService
     */
    public static FormattingConversionService createFormattingConversionService() {
        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(defaultFormattingConversionService);
        return defaultFormattingConversionService;
    }
}
