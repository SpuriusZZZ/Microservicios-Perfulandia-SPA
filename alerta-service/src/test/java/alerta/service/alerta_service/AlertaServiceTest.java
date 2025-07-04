package alerta.service.alerta_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alerta.service.alerta_service.entidades.Alerta;
import alerta.service.alerta_service.repositorio.AlertaRepository;
import alerta.service.alerta_service.servicio.AlertaService;

public class AlertaServiceTest {
    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private AlertaService alertaService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Alerta a1 = new Alerta();
        a1.setId(1);
        a1.setFecha(null);
        a1.setMensaje("Alerta de bajo stock de perfumes en inventario 1");
        a1.setInventarioId(1);

        Alerta a2 = new Alerta();
        a2.setId(2);
        a2.setFecha(null);
        a2.setMensaje("Alerta de bajo stock de ... en inventario 1");
        a2.setInventarioId(1);

        //decimos que cuando se llame a findAll(),
        //retorne una lista simulada con los usuarios
        when(alertaRepository.findAll()).thenReturn(Arrays.asList(a1,a2));

        //ejecutamos el metodo a testear
        List<Alerta> resultado = alertaService.getAll();

        //Verificamos
        assertEquals(2, resultado.size());
        assertEquals("Alerta de bajo stock de ... en inventario 1", resultado.get(1).getMensaje());
    }

    @Test
    public void testGuardarAlerta() {
        Alerta alerta = new Alerta();
        alerta.setMensaje("Stock bajo en inventario 2");
        alerta.setInventarioId(2);

        when(alertaRepository.save(alerta)).thenReturn(alerta);

        Alerta resultado = alertaService.save(alerta);

        assertEquals("Stock bajo en inventario 2", resultado.getMensaje());
        assertEquals(2, resultado.getInventarioId());
    }

    @Test
    public void testGetAlertaById() {
        Alerta alerta = new Alerta();
        alerta.setId(3);
        alerta.setMensaje("Verificar stock urgente");

        when(alertaRepository.findById(3)).thenReturn(Optional.of(alerta));

        Alerta resultado = alertaService.getAlertaById(3);

        assertNotNull(resultado);
        assertEquals("Verificar stock urgente", resultado.getMensaje());
    }

    @Test
    @DisplayName("Debe retornar null si alerta no existe")
    public void testGetAlertaByIdNoExiste() {
        when(alertaRepository.findById(99)).thenReturn(Optional.empty());

        Alerta resultado = alertaService.getAlertaById(99);

        assertNull(resultado);
    }

    @Test
    public void testEliminarAlerta() {
        Alerta alerta = new Alerta();
        alerta.setId(4);

        when(alertaRepository.findById(4)).thenReturn(Optional.of(alerta));

        alertaService.deleteAlertaById(4);

        verify(alertaRepository).deleteById(4);
    }

}
