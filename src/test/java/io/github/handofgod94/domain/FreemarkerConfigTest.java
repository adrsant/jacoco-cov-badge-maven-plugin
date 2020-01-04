package io.github.handofgod94.domain;

import freemarker.template.Configuration;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class FreemarkerConfigTest {

  @Test
  void getConfiguration_loads_freemarker_config() {
    // TODO: see if we can test other configs as well

    Configuration actual = new FreemarkerConfig().getConfiguration();
    assertAll("it loadsd all the freemarker config",
      () -> assertEquals("UTF-8", actual.getDefaultEncoding()),
      () -> assertEquals(Locale.US, actual.getLocale())
    );
  }

  @Test
  void render_WhenBadgeIsValid_ItReturnsSuccess() {
    FreemarkerConfig freemarkerConfig = new FreemarkerConfig();
    Badge badge = Badge.create("Foo", 78);

    Try<String> result = freemarkerConfig.render(badge);

    assertTrue(result.isSuccess());
    assertTrue(result.get().contains("Foo"));
  }

  @Test
  void render_WhenBadgeThrowsError_ItReturnsFailure() {
    FreemarkerConfig freemarkerConfig = new FreemarkerConfig();
    Badge badge = Mockito.mock(Badge.class);
    Mockito.when(badge.templateData()).thenThrow(IOException.class);

    Try<String> result = freemarkerConfig.render(badge);

    assertTrue(result.isFailure());
  }

}
