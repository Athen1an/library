package ru.athena.utility.util;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

import static ru.athena.utility.constant.SpringProfileConstant.DEV;
import static ru.athena.utility.constant.SpringProfileConstant.SPRING_PROFILE_DEFAULT;

/**
 * Spring profile utility class.
 */
public final class SpringProfileUtil {

    private SpringProfileUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Set up dev profile as default.
     *
     * @param application spring-application.
     **/
    public static void addDefaultProfile(SpringApplication application) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put(SPRING_PROFILE_DEFAULT, DEV);
        application.setDefaultProperties(defProperties);
    }

    /**
     * Get active profiles or default if are empty.
     *
     * @param env spring environment.
     * @return profiles array.
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
