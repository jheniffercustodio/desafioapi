package com.agencia.apiviagens.controller;

import org.springframework.web.bind.annotation.*;

import com.agencia.apiviagens.model.Destino;
import com.agencia.apiviagens.service.DestinoService;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService service;

    public DestinoController(DestinoService service) {
        this.service = service;
    }

    @PostMapping
    public Destino cadastrar(@RequestBody Destino destino) {
        return service.cadastrarDestino(destino);
    }

    @GetMapping
    public List<Destino> listar() {
        return service.listarTodos();
    }

    @GetMapping("/pesquisa")
    public List<Destino> pesquisar(@RequestParam String termo) {
        return service.pesquisar(termo);
    }

    @GetMapping("/{id}")
    public Destino detalhar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Destino atualizar(@PathVariable Long id, @RequestBody Destino destinoAtualizado) {
        return service.atualizarDestino(id, destinoAtualizado);
    }

    @PatchMapping("/{id}/avaliar")
    public Destino avaliar(@PathVariable Long id, @RequestParam int nota) {
        return service.avaliarDestino(id, nota);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluirDestino(id);
    }
}

