package com.example.gerenciador_de_clientes.service;

import com.example.gerenciador_de_clientes.model.Cliente;
import com.example.gerenciador_de_clientes.model.Fatura;
import com.example.gerenciador_de_clientes.repository.ClienteRepository;
import com.example.gerenciador_de_clientes.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Fatura> listarFaturas(Long clienteId) {
        return faturaRepository.findByClienteId(clienteId);
    }

    public Fatura registrarPagamento(Long faturaId, Fatura fatura) {
        Fatura faturaExistente = faturaRepository.findById(faturaId).orElse(null);

        if (faturaExistente != null && !"P".equals(faturaExistente.getStatus())) {
            faturaExistente.setStatus("P");
            faturaExistente.setDataPagamento(fatura.getDataPagamento());

            Cliente cliente = faturaExistente.getCliente();
            if (cliente != null) {
                boolean possuiFaturasAtrasadas = faturaRepository
                        .findByClienteId(cliente.getId())
                        .stream()
                        .anyMatch(f -> !"P".equals(f.getStatus()) &&
                                f.getDataVencimento().isBefore(LocalDate.now().minusDays(3)));

                if (!possuiFaturasAtrasadas) {
                    double limiteTotal = faturaRepository
                            .findByClienteId(cliente.getId())
                            .stream()
                            .filter(f -> "P".equals(f.getStatus()))
                            .mapToDouble(Fatura::getValor)
                            .sum();
                    cliente.setLimiteCredito(limiteTotal);
                    if ("B".equals(cliente.getStatusBloqueio())) {
                        cliente.setStatusBloqueio("A");
                    }
                } else {
                    cliente.setLimiteCredito(0.0);
                    cliente.setStatusBloqueio("B");
                }

                clienteRepository.save(cliente);
            }

            return faturaRepository.save(faturaExistente);
        }

        return null;
    }

    public List<Fatura> listarFaturasAtrasadas() {
        return faturaRepository.findByStatus("A");
    }
}
