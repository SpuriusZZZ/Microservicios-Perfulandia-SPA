package com.usuario.service.usuario_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.usuario.service.usuario_service.entidades.Usuario;
import com.usuario.service.usuario_service.repositorio.UsuarioRepository;
import com.usuario.service.usuario_service.servicio.UsuarioService;

public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNombre("Megan");
        u1.setEmail("megancita@duoc.cl");

        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNombre("Emmita");
        u2.setEmail("Emmita@duoc.cl");

        //decimos que cuando se llame a findAll(),
        //retorne una lista simulada con los usuarios
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1,u2));

        //ejecutamos el metodo a testear
        List<Usuario> resultado = usuarioService.getAll();

        //Verificamos
        assertEquals(2, resultado.size());
        assertEquals("Emmita", resultado.get(1).getNombre());
    }

    @Test
    public void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setEmail("carlos@duoc.cl");
        usuario.setRut("11111111-1");
        usuario.setTelefono("912000000");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        assertEquals("Carlos", resultado.getNombre());
        assertEquals("carlos@duoc.cl", resultado.getEmail());
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Laura");
        usuario.setRut("22222222-2");
        usuario.setEmail("laura@duoc.cl");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.getUsuarioById(1);

        assertNotNull(resultado);
        assertEquals("Laura", resultado.getNombre());
    }

    @Test
    public void testGetUsuarioByIdNoExiste() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.getUsuarioById(99);

        assertNull(resultado);
    }

    @Test
    public void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(3);
        usuario.setNombre("Diego");

        when(usuarioRepository.findById(3)).thenReturn(Optional.of(usuario));

        usuarioService.deleteUserById(3);

        verify(usuarioRepository).deleteById(3);
    }
}
