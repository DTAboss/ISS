package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E> {
    /**
     *
     * @param id
     * @return
     */
    Optional<E> findById(long id);


    /**
     *
     * @return
     */
    List<E> findAll();

    /**
     *
     * @param e
     */
    void save(E e);


    /**
     *
     * @param e
     */
    void delete(E e);


    /**
     *
     * @param e
     */
    void update(E e);
}
