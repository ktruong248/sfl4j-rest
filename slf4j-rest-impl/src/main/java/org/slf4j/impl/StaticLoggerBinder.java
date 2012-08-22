/**
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.impl;

import api.LoggingServiceClient;
import api.LoggingServiceClientImpl;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.rest.impl.LoggingRESTServiceLoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * The binding of {@link LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {

    /**
     * The unique instance of this class.
     */
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    private static final String DEFAULT_CONFIGURATION_KEY = "sfl4j-rest-config";

    private static final String DEFAULT_CONFIGURATION_FILE = "sfl4j-rest-config.properties";

    private static Properties configProperties = null;

    private static final String LOGGING_SERVICE_URL_KEY = "logging.service.url";

    private static final String LOGGING_SERVICE_APP_NAME_KEY = "logging.service.appName";

    /**
     * Return the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */
    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    /**
     * Declare the version of the SLF4J API this implementation is compiled
     * against. The value of this field is usually modified with each release.
     */

    private static final String loggerFactoryClassStr = LoggingRESTServiceLoggerFactory.class.getName();

    /**
     * The ILoggerFactory instance returned by the {@link #getLoggerFactory}
     * method should always be the same object
     */
    private final ILoggerFactory loggerFactory;


    private StaticLoggerBinder() {
        Properties config = getConfig();
        LoggingServiceClient loggingServiceClient = new LoggingServiceClientImpl(config.getProperty(LOGGING_SERVICE_URL_KEY));
        loggerFactory = new LoggingRESTServiceLoggerFactory(loggingServiceClient, config.getProperty(LOGGING_SERVICE_APP_NAME_KEY));
    }

    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }

    public Properties getConfig() {
        if (configProperties == null) {

            String configPath = OptionConverter.getSystemProperty(DEFAULT_CONFIGURATION_KEY, null);
            URL url;

            // if the user has not specified the sfl4j-rest-config
            // property, we search first for the app-config.properties"
            if (configPath == null) {
                url = Loader.getResource(DEFAULT_CONFIGURATION_FILE);
            } else {
                try {
                    url = new URL(configPath);
                } catch (MalformedURLException ex) {
                    // so, resource is not a URL:
                    // attempt to get the resource from the class path
                    url = StaticLoggerBinder.class.getClassLoader().getResource(configPath);
                }
            }
            configProperties = new Properties();

            try {
                configProperties.load(url.openStream());
            } catch (Exception e) {
                configProperties.setProperty(LOGGING_SERVICE_URL_KEY, "http://localhost:8080");
                configProperties.setProperty(LOGGING_SERVICE_APP_NAME_KEY, "dev");
                LogLog.error("failed to load " + url, e);
            }
        }

        return configProperties;
    }
}
