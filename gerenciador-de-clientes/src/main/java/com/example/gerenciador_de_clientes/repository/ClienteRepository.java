package com.example.gerenciador_de_clientes.repository;

import com.example.gerenciador_de_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByStatusBloqueio(String statusBloqueio);
}
