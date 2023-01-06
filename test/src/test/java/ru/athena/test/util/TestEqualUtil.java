package ru.athena.test.util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test equal util
 */
public final class TestEqualUtil {

    private TestEqualUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Verifies the equals/hashcode contract on the domain object.
     */
    public static <T> void equalsVerifier(Class<T> clazz) {
        try {
            T domainObject1 = clazz.getConstructor().newInstance();
            assertThat(domainObject1.toString()).isNotNull();
            assertThat(domainObject1).isEqualTo(domainObject1);
            assertThat(domainObject1).hasSameHashCodeAs(domainObject1);
            // Test with an instance of another class
            Object testOtherObject = new Object();
            assertThat(domainObject1).isNotEqualTo(testOtherObject);
            assertThat(domainObject1).isNotEqualTo(null);
            // Test with an instance of the same class
            T domainObject2 = clazz.getConstructor().newInstance();
            assertThat(domainObject1).isNotEqualTo(domainObject2);
            // HashCodes are equals because the objects are not persisted yet
            assertThat(domainObject1).hasSameHashCodeAs(domainObject2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a matcher that matches when the examined instant object epoch seconds is equal to the specified operand.
     */
    public static Matcher<Instant> equalToInstant(Instant instant) {
        return is(new InstantEqual(instant));
    }

    static class InstantEqual extends BaseMatcher<Instant> {

        private final Instant expectedInstant;

        InstantEqual(Instant equalArg) {
            expectedInstant = equalArg;
        }

        @Override
        public boolean matches(Object item) {
            Instant arg = Instant.parse(String.valueOf(item));
            return arg.getEpochSecond() == expectedInstant.getEpochSecond();
        }

        @Override
        public void describeTo(Description description) {
            description.appendValue(" equals to date: " + expectedInstant);
        }
    }

    /**
     * Creates a matcher that matches when the examined local date object is equal to the specified operand.
     */
    public static Matcher<LocalDate> equalToLocalDate(LocalDate localDate) {
        return is(new LocalDateEqual(localDate));
    }

    static class LocalDateEqual extends BaseMatcher<LocalDate> {

        private final LocalDate expectedLocalDate;

        LocalDateEqual(LocalDate equalArg) {
            expectedLocalDate = equalArg;
        }

        @Override
        public boolean matches(Object item) {
            return Objects.equals(expectedLocalDate, LocalDate.parse(String.valueOf(item)));
        }

        @Override
        public void describeTo(Description description) {
            description.appendValue("equals to date: " + expectedLocalDate);
        }
    }
}
