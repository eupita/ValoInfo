package com.valoinfo.repository;

import com.valoinfo.model.ContaRiot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRiotRepository extends JpaRepository<ContaRiot, Long> {
    ContaRiot findByNickAndPassword(String nick, String password);
}