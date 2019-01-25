package de.eilensm.logback.rule;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerRuleExampleNegativeTest {

  @Rule
  public LoggerRule loggerRule = new LoggerRule(String.class);

  @Test
  public void shouldNotReportTheMessageFromOtherLogger() {
    new LoggerRuleExample().logMessage("Hello, World!");
    assertThat(loggerRule.getMessages()).isEmpty();
  }
}
