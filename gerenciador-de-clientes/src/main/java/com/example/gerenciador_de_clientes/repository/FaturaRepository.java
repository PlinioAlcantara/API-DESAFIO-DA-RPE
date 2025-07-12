package com.example.gerenciador_de_clientes.repository;

import com.example.gerenciador_de_clientes.model.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    List<Fatura> findByStatus(String status);
    List<Fatura> findByClienteId(Long clienteId);
}
