package com.example.gerenciador_de_clientes.service;

import com.example.gerenciador_de_clientes.model.Cliente;
import com.example.gerenciador_de_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente consultarCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> listarClientesBloqueados() {
        return clienteRepository.findByStatusBloqueio("B");
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
