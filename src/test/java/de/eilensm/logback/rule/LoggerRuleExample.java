package de.eilensm.logback.rule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerRuleExample {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerRuleExample.class);

    public void logMessage(String message, String... args) {
        LOG.info(message, (Object[]) args);
    }

    public void logError(final String message, Exception e) {
        LOG.error(message, e);
    }
}
