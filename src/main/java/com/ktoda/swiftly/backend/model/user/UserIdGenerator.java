package com.ktoda.swiftly.backend.model.user;

import com.ktoda.swiftly.backend.exception.user.UserIdGeneratorException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

/**
 * Custom identifier generator for User entities.
 * <p>
 * This generator creates unique identifiers for User entities by combining a prefix
 * with a sequence number generated from the database. It is currently tailored to
 * work with PostgreSQL by directly querying the sequence.
 * </p>
 * <p>
 * The generator is equipped with a retry mechanism to handle transient issues during
 * ID generation, attempting up to three retries with increasing delay.
 * </p>
 *
 * @see IdentifierGenerator
 */
@Slf4j
public class UserIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "U";

    /**
     * Generates a unique identifier for a User entity.
     * <p>
     * This method queries the 'user_id_sequence' from the database to obtain
     * a sequence number, which is then prefixed with 'U' to form the ID.
     * In case of failure, it retries up to three times with increasing delay.
     * </p>
     *
     * @param ssci the session contract implementor
     * @param o    the entity for which the ID is being generated
     * @return a unique identifier for the User entity
     * @throws HibernateException if there is an issue in generating the sequence number
     */
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 3))
    @Override
    public Object generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        String query = "select nextval('user_id_sequence')";
        Long sequenceValue = null;
        try {
            sequenceValue = ssci.createNativeQuery(query, Long.class).getSingleResult();
        } catch (Exception e) {
            log.info("Error generating sequence value: " + e.getMessage());
            throw new UserIdGeneratorException("Error generating sequence value for User");
        }
        log.info("Generated sequence value: {}", sequenceValue);
        return PREFIX + sequenceValue;
    }
}
