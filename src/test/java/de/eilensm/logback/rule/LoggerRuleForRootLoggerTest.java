package de.eilensm.logback.rule;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerRuleForRootLoggerTest {

  @Rule
  public LoggerRule loggerRule = new LoggerRule();

  @Test
  public void shouldReportTheMessage() {
    new LoggerRuleExample().logMessage("Hello, World!");
    assertThat(loggerRule.getMessages()).containsExactly("Hello, World!");
  }
}
