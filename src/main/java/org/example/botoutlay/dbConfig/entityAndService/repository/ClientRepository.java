package org.example.botoutlay.dbConfig.entityAndService.repository;

import org.example.botoutlay.dbConfig.entityAndService.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdAndDeletedFalse(Long id);
    Page<Client> findAllByDeletedFalse(Pageable pageable);
}
