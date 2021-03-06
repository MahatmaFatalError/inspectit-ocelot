package rocks.inspectit.ocelot.core.config.model.instrumentation.scope;

import net.bytebuddy.matcher.StringMatcher;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ElementDescriptionMatcherSettingsTest {

    @Nested
    public class IsAnyMatcher {

        @Test
        public void emptySettings() {
            ElementDescriptionMatcherSettings settings = new ElementDescriptionMatcherSettings();
            settings.setMatcherMode(StringMatcher.Mode.STARTS_WITH);

            boolean result = settings.isAnyMatcher();

            assertThat(result).isTrue();
        }

        @Test
        public void specificAnnotationMatcher() {
            ElementDescriptionMatcherSettings settings = new ElementDescriptionMatcherSettings();
            settings.setMatcherMode(StringMatcher.Mode.STARTS_WITH);
            NameMatcherSettings matcher = new NameMatcherSettings();
            matcher.setName("annotation");
            settings.setAnnotations(Collections.singletonList(matcher));

            boolean result = settings.isAnyMatcher();

            assertThat(result).isFalse();
        }

        @Test
        public void allAnnotationMatcher() {
            ElementDescriptionMatcherSettings settings = new ElementDescriptionMatcherSettings();
            settings.setMatcherMode(StringMatcher.Mode.STARTS_WITH);
            NameMatcherSettings matcher = new NameMatcherSettings();
            matcher.setMatcherMode(StringMatcher.Mode.STARTS_WITH);
            settings.setAnnotations(Collections.singletonList(matcher));

            boolean result = settings.isAnyMatcher();

            assertThat(result).isFalse();
        }

        @Test
        public void allAnnotationMatcherButSpecificClassMatcher() {
            ElementDescriptionMatcherSettings settings = new ElementDescriptionMatcherSettings();
            settings.setName("class");
            NameMatcherSettings matcher = new NameMatcherSettings();
            matcher.setMatcherMode(StringMatcher.Mode.STARTS_WITH);
            settings.setAnnotations(Collections.singletonList(matcher));

            boolean result = settings.isAnyMatcher();

            assertThat(result).isFalse();
        }

    }

}