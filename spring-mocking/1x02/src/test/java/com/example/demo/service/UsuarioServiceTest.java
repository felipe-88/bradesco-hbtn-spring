package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Usuario usuarioTeste = new Usuario(1L, "UsuarioTeste", "usuario@teste.com");

        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuarioTeste));

        Usuario usuario = usuarioService.buscarUsuarioPorId(1L);

        Assertions.assertEquals("UsuarioTeste", usuario.getNome());
        Assertions.assertEquals("usuario@teste.com", usuario.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(1L));
        Assertions.assertEquals("Usuário não encontrado", runtimeException.getMessage());
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario teste = new Usuario();
        teste.setNome("UsuarioTeste");
        teste.setEmail("usuario@teste.com");

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(new Usuario(1L, "UsuarioTeste", "usuario@teste.com"));

        Usuario usuario = usuarioService.salvarUsuario(teste);

        Assertions.assertEquals(teste.getNome(), usuario.getNome());
        Assertions.assertEquals(teste.getEmail(), usuario.getEmail());
        Assertions.assertNotNull(usuario.getId());
    }
}
