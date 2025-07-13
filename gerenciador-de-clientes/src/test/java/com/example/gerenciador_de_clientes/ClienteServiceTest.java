package com.example.gerenciador_de_clientes;

import com.example.gerenciador_de_clientes.model.Cliente;
import com.example.gerenciador_de_clientes.repository.ClienteRepository;
import com.example.gerenciador_de_clientes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente salvo = clienteService.cadastrarCliente(cliente);

        assertEquals("Teste", salvo.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testListarClientesBloqueados() {
        Cliente bloqueado = new Cliente();
        bloqueado.setStatusBloqueio("B");
        when(clienteRepository.findByStatusBloqueio("B")).thenReturn(Arrays.asList(bloqueado));

        var resultado = clienteService.listarClientesBloqueados();

        assertEquals(1, resultado.size());
        assertEquals("B", resultado.get(0).getStatusBloqueio());
    }

    @Test
    public void testConsultarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.consultarCliente(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }
}
