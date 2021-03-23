package com.company.util;

public class IdGenerator {
    private static IdGenerator instance;

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public Long generateId(Long maxEntityId) {
        return maxEntityId + 1L;
    }
}
