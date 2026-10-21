package com.valoinfo.repository;

import com.valoinfo.model.ContaRiot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRiotRepository extends JpaRepository<ContaRiot, Long> {
    // Método automático para procurar uma conta no momento do login
    ContaRiot findByNickAndPassword(String nick, String password);
}