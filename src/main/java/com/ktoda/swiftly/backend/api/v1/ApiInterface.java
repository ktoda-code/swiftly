package com.ktoda.swiftly.backend.api.v1;


import org.springframework.http.ResponseEntity;

/**
 * Generic interface for Restful services with basic methods for saving, deleting, finding and updating.
 *
 * @param <CR> Create request entity - Data Transfer Object
 * @param <UR> Update request entity - Data Transfer Object
 * @param <ID> Data type of the id of the entity
 */
public interface ApiInterface<ID, CR, UR> {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(ID id);

    ResponseEntity<?> save(CR entity);

    ResponseEntity<?> update(UR entity);

    ResponseEntity<?> delete(ID id);
}
