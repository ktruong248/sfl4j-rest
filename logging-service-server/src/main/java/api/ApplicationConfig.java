package api;

import api.common.UniqueIdGenerator;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

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
    public UniqueIdGenerator uniqueIdGenerator() {
        return new UniqueIdGenerator();
    }

    @Bean
    public MongoTypeMapper mongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

    @Bean
    public MongoMappingContext mongoMappingContext() {
        return new MongoMappingContext();
    }

    @Bean
    public UserCredentials userCredentials() {
        return new UserCredentials(dbUserName, dbPassword);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(mongo(), dbName, userCredentials());
    }

    @Bean
    public MongoConverter mongoConverter() throws UnknownHostException {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), mongoMappingContext());
        converter.setTypeMapper(mongoTypeMapper());

        return converter;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate template = new MongoTemplate(mongoDbFactory(), mongoConverter());
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);

        return template;
    }
}
