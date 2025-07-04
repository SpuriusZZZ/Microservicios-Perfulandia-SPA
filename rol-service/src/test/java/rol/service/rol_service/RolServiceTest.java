package rol.service.rol_service;

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

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.repositorio.RolRepository;
import rol.service.rol_service.servicio.RolService;

public class RolServiceTest {
    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Rol r1 = new Rol();
        r1.setId(1);
        r1.setNombreRol("ADMIN");

        Rol r2 = new Rol();
        r2.setId(2);
        r2.setNombreRol("USER");

        when(rolRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Rol> resultado = rolService.getAll();

        assertEquals(2, resultado.size());
        assertEquals("USER", resultado.get(1).getNombreRol());
    }

    @Test
    public void testGuardarRol() {
        Rol rol = new Rol();
        rol.setNombreRol("MODERATOR");

        when(rolRepository.save(rol)).thenReturn(rol);

        Rol resultado = rolService.save(rol);

        assertEquals("MODERATOR", resultado.getNombreRol());
    }

    @Test
    public void testGetRolById() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombreRol("ADMIN");

        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));

        Rol resultado = rolService.getRolById(1);

        assertNotNull(resultado);
        assertEquals("ADMIN", resultado.getNombreRol());
    }

    @Test
    public void testGetRolByIdNoExiste() {
        when(rolRepository.findById(99)).thenReturn(Optional.empty());

        Rol resultado = rolService.getRolById(99);

        assertNull(resultado);
    }

    @Test
    public void testEliminarRol() {
        Rol rol = new Rol();
        rol.setId(3);
        rol.setNombreRol("GUEST");

        when(rolRepository.findById(3)).thenReturn(Optional.of(rol));

        rolService.deleteRolById(3);

        verify(rolRepository).deleteById(3);
    }
}
