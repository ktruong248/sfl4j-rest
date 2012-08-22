package org.slf4j.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingITTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggingITTest.class);

    @Test
    @Ignore("enabled for IT Test")
    public void shouldLogDebug() {
        String msg = "debug message";
        logger.debug(msg);
        logger.debug(msg, new RuntimeException("test", new NullPointerException("some null")));
        logger.debug("debug message {} -> {}", "1", "2");
    }
}
