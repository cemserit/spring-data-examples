package com.cemserit.cassandra.model;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    @CassandraType(type = DataType.Name.UUID)
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;
    private String email;
    private String name;
    private int age;
}
