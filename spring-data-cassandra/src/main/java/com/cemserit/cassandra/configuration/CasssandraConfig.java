package com.cemserit.cassandra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

import java.util.Collections;
import java.util.List;

@Configuration
public class CasssandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace.name}")
    private String keyspaceName;
    @Value("${cassandra.durableWrites}")
    private String durableWrites;
    @Value("${cassandra.replication.replicationFactor}")
    private String replicationFactor;
    @Value("${cassandra.replication.class}")
    private String replicationClass;

    @Override
    protected List<String> getStartupScripts() {

        String script = String.format("CREATE KEYSPACE IF NOT EXISTS %s "
                        + "WITH durable_writes = %s "
                        + "AND replication = { 'replication_factor' : %s, 'class' : '%s' };",
                keyspaceName, durableWrites, replicationFactor, replicationClass);

        return Collections.singletonList(script);
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.cemserit.cassandra.model"};
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }
}
