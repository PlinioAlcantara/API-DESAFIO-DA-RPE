package com.example.gerenciador_de_clientes.service;

import com.example.gerenciador_de_clientes.model.Cliente;
import com.example.gerenciador_de_clientes.model.Fatura;
import com.example.gerenciador_de_clientes.repository.ClienteRepository;
import com.example.gerenciador_de_clientes.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (faturaExistente != null && !faturaExistente.getStatus().equals("P")) {
            faturaExistente.setStatus("P");
            faturaExistente.setDataPagamento(fatura.getDataPagamento());

            Cliente cliente = faturaExistente.getCliente();
            if (cliente != null) {

                double novoLimite = cliente.getLimiteCredito() + faturaExistente.getValor();
                cliente.setLimiteCredito(novoLimite);


                if ("B".equals(cliente.getStatusBloqueio()) && novoLimite > 0) {
                    cliente.setStatusBloqueio("A"); // A = Ativo
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
