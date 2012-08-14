package api;

import api.common.UniqueIdGenerator;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.UnknownHostException;

@Configuration
public class ApplicationConfig {

    @Value("${db.mongo.host}")
    String host;

    @Value("${db.mongo.dbName}")
    String dbName;

    @Value("${db.mongo.userName}")
    String dbUserName;

    @Value("${db.mongo.password}")
    String dbPassword;

    @Value("${db.mongo.port}")
    int port;

    @Value("${db.mongo.connectionsPerHost}")
    int connectionPerHost;

    @Value("${db.mongo.threadsAllowedToBlockForConnectionMultiplier}")
    int threadsAllowedToBlockForConnectionMultiplier;

    @Value("${db.mongo.maxWaitTime}")
    int maxWaitTime;

    @Value("${db.mongo.connectTimeout}")
    int connectTimeout;

    @Value("${db.mongo.socketTimeout}")
    int socketTimeout;

    @Value("${db.mongo.autoConnectRetry}")
    boolean autoConnectRetry;

    @Value("${db.mongo.slaveOk}")
    boolean slaveOk;

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new Mongo(new ServerAddress(host, port), mongoOptions());
    }

    @Bean
    public MongoOptions mongoOptions() {
        MongoOptions options = new MongoOptions();
        options.autoConnectRetry = autoConnectRetry;
        options.connectionsPerHost = connectionPerHost;
        options.connectTimeout = connectTimeout;
        options.maxWaitTime = maxWaitTime;
        options.socketTimeout = socketTimeout;
        options.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;

        return options;
    }

    @Bean
    public Morphia morphia() {
        return new com.google.code.morphia.Morphia();
    }

    @Bean
    public UniqueIdGenerator uniqueIdGenerator() {
        return new UniqueIdGenerator();
    }

    @Bean
    public Datastore datastore() throws UnknownHostException {

        if (StringUtils.hasLength(dbUserName) && StringUtils.hasLength(dbPassword)) {
            return new DatastoreImpl(morphia(), mongo(), dbName, dbUserName, dbPassword.toCharArray());
        }

        return new DatastoreImpl(morphia(), mongo(), dbName);
    }
}
