package com.example.demo.service;


import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;


    public List<Produto> listarProdutos() {
        return produtos;
    }


    public Produto adicionarProduto(Produto produto) {
        produto.setId(contadorId);
        produtos.add(produto);
        contadorId++;
        return produto;
    }


    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        Optional<Produto> first = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
        first.ifPresent(pdt -> produtos.set(produtos.indexOf(pdt), produtoAtualizado));
        return produtoAtualizado;
    }


    public boolean deletarProduto(Long id) {
        return produtos.removeIf(produto -> produto.getId().equals(id));
    }
}