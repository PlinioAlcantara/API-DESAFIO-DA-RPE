package com.example.gerenciador_de_clientes.repository;

import com.example.gerenciador_de_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByStatusBloqueio(String statusBloqueio);
}
