package com.agencia.apiviagens.service;

import org.springframework.stereotype.Service;

import com.agencia.apiviagens.model.Destino;

import java.util.*;

@Service
public class DestinoService {

    private List<Destino> destinos = new ArrayList<>();
    private Long idCounter = 1L;

    public Destino cadastrarDestino(Destino destino) {
        destino.setId(idCounter++);
        destinos.add(destino);
        return destino;
    }

    public List<Destino> listarTodos() {
        return destinos;
    }

    public List<Destino> pesquisar(String termo) {
        return destinos.stream()
                .filter(d -> d.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                             d.getLocalizacao().toLowerCase().contains(termo.toLowerCase()))
                .toList();
    }

    public Destino buscarPorId(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public Destino avaliarDestino(Long id, int nota) {
        Destino destino = buscarPorId(id);
        if (destino != null && nota >= 1 && nota <= 10) {
            double totalNotas = destino.getNota() * destino.getTotalAvaliacoes();
            destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);
            destino.setNota((totalNotas + nota) / destino.getTotalAvaliacoes());
        }
        return destino;
    }

    public boolean excluirDestino(Long id) {
        return destinos.removeIf(d -> d.getId().equals(id));
    }
}

