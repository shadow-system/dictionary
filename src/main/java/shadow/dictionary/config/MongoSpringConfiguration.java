package shadow.dictionary.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories("shadow.dictionary")
public class MongoSpringConfiguration extends MongoConfigurationSupport {

    /**
     * Injecting cause MongoConfigurationSupport getDatabaseName() doesn't accept any parameters on override,
     * and I have properties which I need to inject and get database name from there
     */
    private final MongoConfig config;

    @Autowired
    public MongoSpringConfiguration(MongoConfig config) {
        this.config = config;
    }

    @Override
    protected String getDatabaseName() {
        return config.getDatabaseName();
    }

    @Bean
    public MongoClient mongoClient(MongoClientSettings clientSettings) {
        return MongoClients.create(clientSettings);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(MongoClient mongoClient, MongoConfig config) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, config.getDatabaseName());
    }

    @Bean
    public MongoClientSettings mongoClientSettings(MongoConfig config) {
        ConnectionString ConnectionString = createConnectionString(config);
        return MongoClientSettings.builder()
            .applyConnectionString(ConnectionString)
            .build();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, getDatabaseName());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(ReactiveMongoDatabaseFactory databaseFactory,
                                                       MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.setCodecRegistryProvider(databaseFactory);
        return converter;
    }

    private ConnectionString createConnectionString(MongoConfig config) {
        return new ConnectionString("mongodb+srv://" + config.getUsername() + ":" + config.getPassword() +
            "@" + config.getHost()
        );
    }
}
