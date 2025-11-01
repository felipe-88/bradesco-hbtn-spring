package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    @Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        Produto produtoTeste = new Produto(1L, "ProdutoTeste", 2D);
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produtoTeste));

        Produto produto = produtoService.buscarPorId(1L);

        Assertions.assertEquals("ProdutoTeste", produto.getNome());
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(1L));
        Assertions.assertEquals("Produto não encontrado", runtimeException.getMessage());
    }
}