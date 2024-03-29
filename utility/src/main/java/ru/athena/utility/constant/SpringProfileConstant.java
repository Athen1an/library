package ru.athena.utility.constant;

/**
 * Spring profiles constants.
 */
public final class SpringProfileConstant {

    private SpringProfileConstant() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public static final String LOCAL = "local";
    public static final String DEV = "dev";
    public static final String PROD = "prod";
    public static final String TEST = "test";
}
