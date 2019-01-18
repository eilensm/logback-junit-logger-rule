package de.eilensm.logback.rule;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.read.ListAppender;
import org.junit.rules.ExternalResource;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class LoggerRule extends ExternalResource {

  private final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

  private final Logger logger;

  /**
   * Creates the {@link LoggerRule} for the root logger
   */
  public LoggerRule() {
    this(Logger.ROOT_LOGGER_NAME);
  }

  /**
   * @param clazz the class for which the {@link LoggerRule} is created
   */
  public LoggerRule(final Class clazz) {
    logger = (Logger) LoggerFactory.getLogger(clazz);
  }

  /**
   * @param loggerName the logger name for which the {@link LoggerRule} is created
   */
  public LoggerRule(final String loggerName) {
    logger = (Logger) LoggerFactory.getLogger(loggerName);
  }

  @Override
  protected void before() {
    logger.addAppender(listAppender);
    listAppender.start();
  }

  @Override
  protected void after() {
    listAppender.stop();
    listAppender.list.clear();
    logger.detachAppender(listAppender);
  }

  public List<String> getMessages() {
    return listAppender.list.stream().map(ILoggingEvent::getMessage).collect(toList());
  }

  public List<String> getFormattedMessages() {
    return listAppender.list.stream().map(ILoggingEvent::getFormattedMessage).collect(toList());
  }

  public List<Throwable> getThrowables() {
    return listAppender.list.stream()
        .map(event -> ((ThrowableProxy) event.getThrowableProxy()).getThrowable())
        .collect(toList());
  }
}
