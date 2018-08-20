package com.cemserit.redis.repository;

import java.util.Map;

// Alternative sample
public interface PersonRepositoryComplex {
    void setHash(String key, String hashKey, String value);

    void setHash(String key, Map keyValues);

    Object getHash(String key, Object hashKey);

    void deleteHash(String key, Object... hashKeys);
}
