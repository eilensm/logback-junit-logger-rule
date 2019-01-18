package de.eilensm.logback.rule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerRuleExampleTest {

  @Rule
  public LoggerRule loggerRule = new LoggerRule(LoggerRuleExample.class);

  private LoggerRuleExample loggerRuleExample;

  @Before
  public void setUp() {
    loggerRuleExample = new LoggerRuleExample();
  }

  @Test
  public void shouldReportTheMessage() {
    loggerRuleExample.logMessage("Hello, World!");
    assertThat(loggerRule.getMessages())
        .containsExactly("Hello, World!");
  }

  @Test
  public void shouldReportTheFormattedMessage() {
    loggerRuleExample.logMessage("Hello, {}!", "Ingo");
    assertThat(loggerRule.getFormattedMessages())
        .containsExactly("Hello, Ingo!");
  }

  @Test
  public void shouldReportException() {
    final Exception exception = new Exception();
    loggerRuleExample.logError("an exception occured", exception);
    assertThat(loggerRule.getThrowables())
        .containsExactly(exception);
  }
}

