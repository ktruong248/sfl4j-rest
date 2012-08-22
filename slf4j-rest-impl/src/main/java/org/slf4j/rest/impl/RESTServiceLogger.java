package org.slf4j.rest.impl;

import org.apache.log4j.Logger;

public interface RESTServiceLogger {
    void trace(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable);

    void debug(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable);

    void info(String callerFQCN, String msg, String appName, String ipAddress, Throwable throwable);

    void warn(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable);

    void error(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable);

    Logger getLogger();
}
