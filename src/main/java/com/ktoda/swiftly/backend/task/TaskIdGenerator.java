package com.ktoda.swiftly.backend.task;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@Slf4j
public class TaskIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "T";

    @Override
    public Object generate(SharedSessionContractImplementor ssci, Object o) {
        // PostgreSQL specific syntax
        String query = "select nextval('task_id_sequence')";
        Long sequenceValue = null;
        try {
            sequenceValue = ssci.createNativeQuery(query, Long.class).getSingleResult();
        } catch (Exception e) {
            log.info("Error generating sequence value: " + e.getMessage());
            throw new RuntimeException("Error generating sequence value for Task");
        }
        log.info("Generated sequence value: {}", sequenceValue);
        return PREFIX + sequenceValue;
    }
}
