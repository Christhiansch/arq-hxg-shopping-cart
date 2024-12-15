package com.mitocode.shop.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCartSpringDataRepository extends JpaRepository<CartJpaEntity, Long> {

    @Query("SELECT c FROM CartJpaEntity c WHERE c.customerId = ?1")
    Optional<CartJpaEntity> findByCustomerId(int customerId);

    void deleteByCustomerId(int customerId);

}
