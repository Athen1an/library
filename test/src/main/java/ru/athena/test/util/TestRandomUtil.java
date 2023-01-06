package ru.athena.test.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Test random util
 */
public final class TestRandomUtil {

    private TestRandomUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Generate random string value
     *
     * @return string value in UUID format
     */
    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate random string value of the specified length
     *
     * @param length string length
     * @return string of the specified length
     */
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generate random integer value
     *
     * @return random integer from 0 to 2147483647
     */
    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    }

    /**
     * Generate random integer value with limit
     *
     * @return random integer from 0 to limit value
     */
    public static int randomInt(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    /**
     * Generate random long value
     *
     * @return random long from 0 to 9223372036854775807
     */
    public static long randomLong() {
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }

    /**
     * Generate random BigDecimal value
     *
     * @return random BigDecimal from 0 to 9223372036854775807
     */
    public static BigDecimal randomBigDecimal() {
        return new BigDecimal(randomLong());
    }

    /**
     * Generate random BigDecimal value with limit
     *
     * @param limit limit
     * @return random BigDecimal from 0 to limit
     */
    public static BigDecimal randomBigDecimal(int limit) {
        return new BigDecimal(ThreadLocalRandom.current().nextInt(limit));
    }

    /**
     * Generate random enum from enum class
     *
     * @param enumClass enum class
     * @param <T> class type
     * @return random enum
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        return enumClass.getEnumConstants()[ThreadLocalRandom.current().nextInt(enumClass.getEnumConstants().length)];
    }
}
