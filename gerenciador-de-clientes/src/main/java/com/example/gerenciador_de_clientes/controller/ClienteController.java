package com.example.gerenciador_de_clientes.controller;

import com.example.gerenciador_de_clientes.model.Cliente;
import com.example.gerenciador_de_clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodosClientes();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente consultarCliente(@PathVariable Long id) {
        return clienteService.consultarCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.atualizarCliente(cliente);
    }

    @GetMapping("/bloqueados")
    public List<Cliente> listarClientesBloqueados() {
        return clienteService.listarClientesBloqueados();
    }
}
