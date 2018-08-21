package com.cemserit.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {

    @Id
    private String email;
    private String name;
    @Indexed
    private int age;
}
