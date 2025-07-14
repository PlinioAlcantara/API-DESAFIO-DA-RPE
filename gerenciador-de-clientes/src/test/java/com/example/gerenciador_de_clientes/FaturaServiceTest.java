package com.example.gerenciador_de_clientes;

import com.example.gerenciador_de_clientes.model.Fatura;
import com.example.gerenciador_de_clientes.repository.FaturaRepository;
import com.example.gerenciador_de_clientes.service.FaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FaturaServiceTest {

    @Mock
    private FaturaRepository faturaRepository;

    @InjectMocks
    private FaturaService faturaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarPagamento() {
        Fatura fatura = new Fatura();
        fatura.setId(1L);
        fatura.setStatus("B");

        Fatura atualizacao = new Fatura();
        atualizacao.setDataPagamento(LocalDate.now());

        when(faturaRepository.findById(1L)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Fatura resultado = faturaService.registrarPagamento(1L, atualizacao);

        assertNotNull(resultado);
        assertEquals("P", resultado.getStatus());
        assertEquals(LocalDate.now(), resultado.getDataPagamento());
    }

    @Test
    public void testListarFaturasAtrasadas() {
        Fatura f1 = new Fatura();
        f1.setStatus("A");

        when(faturaRepository.findByStatus("A")).thenReturn(Arrays.asList(f1));

        var resultado = faturaService.listarFaturasAtrasadas();

        assertEquals(1, resultado.size());
        assertEquals("A", resultado.get(0).getStatus());
    }
}
