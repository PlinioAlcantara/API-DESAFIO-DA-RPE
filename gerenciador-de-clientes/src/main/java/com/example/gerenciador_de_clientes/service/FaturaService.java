package com.example.gerenciador_de_clientes.service;

import com.example.gerenciador_de_clientes.model.Fatura;
import com.example.gerenciador_de_clientes.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    public List<Fatura> listarFaturas(Long clienteId) {
        return faturaRepository.findByClienteId(clienteId);
    }

    public Fatura registrarPagamento(Long faturaId, Fatura fatura) {
        Fatura faturaExistente = faturaRepository.findById(faturaId).orElse(null);
        if (faturaExistente != null) {
            faturaExistente.setStatus("P");
            faturaExistente.setDataPagamento(fatura.getDataPagamento());
            return faturaRepository.save(faturaExistente);
        }
        return null;
    }

    public List<Fatura> listarFaturasAtrasadas() {
        return faturaRepository.findByStatus("A");
    }
}
