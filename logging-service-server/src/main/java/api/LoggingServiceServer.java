package api;

import com.google.common.base.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class LoggingServiceServer {
    private static final transient Logger logger = LoggerFactory.getLogger(LoggingServiceServer.class);
    private static final String DEFAULT_SERVER_CONFIG = "classpath:api/spring-server.xml";

    public static void main(String[] args) {

        ApplicationContext applicationContext = new GenericXmlApplicationContext(DEFAULT_SERVER_CONFIG);
        final Service service = applicationContext.getBean(Service.class);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("shutdown LoggingServiceServer");
                service.stopAndWait();
            }
        });

        logger.info("Starting LoggingServiceServer");

        try {
            service.startAndWait();
        } catch (Exception e) {
            logger.error("failed to start", e);
            System.exit(-1);
        }
    }
}
