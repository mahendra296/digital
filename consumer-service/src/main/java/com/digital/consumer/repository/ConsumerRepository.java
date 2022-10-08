package com.digital.consumer.repository;

import com.digital.consumer.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Consumer findByEmail(String email);
    Boolean existsByEmail(String email);
}
