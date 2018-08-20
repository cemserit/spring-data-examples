package com.cemserit.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Person")
public class Person {

    @Id
    private String email;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
