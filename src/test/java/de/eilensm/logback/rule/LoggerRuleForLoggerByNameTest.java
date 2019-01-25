package de.eilensm.logback.rule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerRuleForLoggerByNameTest {

  @Rule
  public LoggerRule loggerRule = new LoggerRule("test");

  @Test
  public void shouldReportTheMessage() {
    new ExampleWithNamedLogger().logMessage("Hello, World!");
    assertThat(loggerRule.getMessages()).containsExactly("Hello, World!");
  }
}

class ExampleWithNamedLogger {

  private static final Logger LOG = LoggerFactory.getLogger("test");

  public void logMessage(String message) {
    LOG.info(message);
  }
}

