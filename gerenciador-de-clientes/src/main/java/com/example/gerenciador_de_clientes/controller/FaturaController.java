package com.example.gerenciador_de_clientes.controller;

import com.example.gerenciador_de_clientes.model.Fatura;
import com.example.gerenciador_de_clientes.service.FaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import com.example.gerenciador_de_clientes.DTO.PagamentoDTO;

import java.util.List;

@RestController
@RequestMapping("/faturas")
public class FaturaController {

    @Autowired
    private FaturaService faturaService;

    @GetMapping("/{id}")
    public List<Fatura> listarFaturas(@PathVariable Long id) {
        return faturaService.listarFaturas(id);
    }

    @PutMapping("/{id}/pagamento")
    public Fatura registrarPagamento(@PathVariable Long id, @RequestBody PagamentoDTO dto) {
        Fatura fatura = new Fatura();
        fatura.setDataPagamento(LocalDate.parse(dto.getDataPagamento()));
        return faturaService.registrarPagamento(id, fatura);
    }

    @GetMapping("/atrasadas")
    public List<Fatura> listarFaturasAtrasadas() {
        return faturaService.listarFaturasAtrasadas();
    }
}
