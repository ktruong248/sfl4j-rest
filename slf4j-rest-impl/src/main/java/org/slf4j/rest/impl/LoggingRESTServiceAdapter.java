package org.slf4j.rest.impl;

import api.common.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

/**
 * A wrapper over {@link org.apache.log4j.Logger org.apache.log4j.Logger} in
 * conforming to the {@link org.slf4j.Logger} interface.
 * <p/>
 * <p/>
 * Note that the logging levels mentioned in this class refer to those defined
 * in the <a
 * href="http://logging.apache.org/log4j/docs/api/org/apache/log4j/Level.html">
 * <code>org.apache.log4j.Level</code></a> class.
 * <p/>
 * <p/>
 * The TRACE level was introduced in log4j version 1.2.12. In order to avoid
 * crashing the host application, in the case the log4j version in use predates
 * 1.2.12, the TRACE level will be mapped as DEBUG. See also <a
 * href="http://bugzilla.slf4j.org/show_bug.cgi?id=68">bug 68</a>.
 * <p/>
 *
 * @author Ceki G&uuml;lc&uuml;
 * @author Khiem Truong;
 */
public final class LoggingRESTServiceAdapter extends MarkerIgnoringBase implements
        LocationAwareLogger, Serializable {

    private static final long serialVersionUID = -5623116085876917915L;

    // Does the log4j version in use recognize the TRACE level?
    // The trace level was introduced in log4j 1.2.12.
    final boolean traceCapable;

    //  final transient org.apache.log4j.Logger logger;
    final transient Logger logger;

    /**
     * Following the pattern discussed in pages 162 through 168 of "The complete
     * log4j manual".
     */
    final static String FQCN = LoggingRESTServiceAdapter.class.getName();

    private String appName;

    private String ipAddress;

    private LoggingRESTService4jLogger restService4jLogger;

    // WARN: LoggingRESTServiceAdapter constructor should have only package access so
    // that
    // only LoggingRESTServiceAdapter be able to create one.
    LoggingRESTServiceAdapter(LoggingRESTService4jLogger restService4jLogger, String appName) {
        this.logger = restService4jLogger.getLogger();
        this.name = restService4jLogger.getLogger().getName();
        this.appName = appName;
        this.ipAddress = Utils.localAddress();
        this.traceCapable = isTraceCapable();
        this.restService4jLogger = restService4jLogger;
    }

    /**
     * Is this logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for level TRACE, false otherwise.
     */
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Log a message object at level TRACE.
     *
     * @param msg - the message object to be logged
     */
    public void trace(String msg) {
        if (traceCapable) {
            restService4jLogger.trace(FQCN, msg, appName, ipAddress, null);
        } else {
            restService4jLogger.debug(FQCN, msg, appName, ipAddress, null);
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level TRACE.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void trace(String format, Object arg) {
        if (traceCapable) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.trace(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        } else {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void trace(String format, Object arg1, Object arg2) {
        if (traceCapable) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.trace(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        } else {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void trace(String format, Object[] argArray) {
        if (traceCapable) {
            FormattingTuple ft = MessageFormatter.format(format, argArray);
            restService4jLogger.trace(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        } else {
            FormattingTuple ft = MessageFormatter.format(format, argArray);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at level TRACE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void trace(String msg, Throwable t) {
        if (traceCapable) {
            restService4jLogger.trace(FQCN, msg, appName, ipAddress, t);
        } else {
            restService4jLogger.debug(FQCN, msg, appName, ipAddress, t);
        }
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Log a message object at level DEBUG.
     *
     * @param msg - the message object to be logged
     */
    public void debug(String msg) {
        if (isDebugEnabled()) {
            restService4jLogger.debug(FQCN, msg, appName, ipAddress, null);
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level DEBUG.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void debug(String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void debug(String format, Object[] argArray) {
        if (isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            restService4jLogger.debug(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void debug(String msg, Throwable t) {
        if (isDebugEnabled()) {
            restService4jLogger.debug(FQCN, msg, appName, ipAddress, t);
        }
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Log a message object at the INFO level.
     *
     * @param msg - the message object to be logged
     */
    public void info(String msg) {
        if (isInfoEnabled()) {
            restService4jLogger.info(FQCN, msg, appName, ipAddress, null);
        }
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.info(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void info(String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.info(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level INFO according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void info(String format, Object[] argArray) {
        if (isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            restService4jLogger.info(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            restService4jLogger.info(FQCN, msg, appName, ipAddress, t);
        }
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false otherwise.
     */
    public boolean isWarnEnabled() {
        return logger.isEnabledFor(Level.WARN);
    }

    /**
     * Log a message object at the WARN level.
     *
     * @param msg - the message object to be logged
     */
    public void warn(String msg) {
        if (isWarnEnabled()) {
            restService4jLogger.warn(FQCN, msg, appName, ipAddress, null);
        }
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.warn(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.warn(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level WARN according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void warn(String format, Object[] argArray) {
        if (isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            restService4jLogger.warn(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()) {
            restService4jLogger.warn(FQCN, msg, appName, ipAddress, t);
        }
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     *
     * @param msg - the message object to be logged
     */
    public void error(String msg) {
        restService4jLogger.error(FQCN, msg, appName, ipAddress, null);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            restService4jLogger.error(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void error(String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            restService4jLogger.error(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log a message at level ERROR according to the specified format and
     * arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void error(String format, Object[] argArray) {
        if (isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            restService4jLogger.error(FQCN, ft.getMessage(), appName, ipAddress, ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            restService4jLogger.error(FQCN, msg, appName, ipAddress, t);
        }
    }

    public void log(Marker marker, String callerFQCN, int level, String msg,
                    Object[] argArray, Throwable t) {
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                restService4jLogger.debug(callerFQCN, msg, appName, ipAddress, t);
                break;
            case LocationAwareLogger.DEBUG_INT:
                restService4jLogger.debug(callerFQCN, msg, appName, ipAddress, t);
                break;
            case LocationAwareLogger.INFO_INT:
                restService4jLogger.info(callerFQCN, msg, appName, ipAddress, t);
                break;
            case LocationAwareLogger.WARN_INT:
                restService4jLogger.warn(callerFQCN, msg, appName, ipAddress, t);
                break;
            case LocationAwareLogger.ERROR_INT:
                restService4jLogger.error(callerFQCN, msg, appName, ipAddress, t);
                break;
            default:
                throw new IllegalStateException("Level number " + level + " is not recognized.");
        }
    }

    private boolean isTraceCapable() {
        try {
            logger.isTraceEnabled();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        }
    }
}
