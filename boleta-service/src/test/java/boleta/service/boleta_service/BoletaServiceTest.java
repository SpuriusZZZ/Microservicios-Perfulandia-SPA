package boleta.service.boleta_service;

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

import boleta.service.boleta_service.entidades.Boleta;
import boleta.service.boleta_service.repositorio.BoletaRepository;
import boleta.service.boleta_service.servicio.BoletaServicio;

public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServicio boletaServicio;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Boleta b1 = new Boleta();
        b1.setId(1);
        b1.setNombreEmpresa("PerfulandiaSPA");
        b1.setRutEmpresa("76.087.419-1");
        b1.setCantidadProducto(7);
        b1.setTotalBoleta(550000);
        b1.setFormaPago("Efectivo");
        b1.setUsuarioId(1);


    Boleta b2 = new Boleta();
        b2.setId(2);
        b2.setNombreEmpresa("PerfulandiaSPA");
        b2.setRutEmpresa("76.087.419-1");
        b2.setCantidadProducto(7);
        b2.setTotalBoleta(35000);
        b2.setFormaPago("Debito");
        b2.setUsuarioId(2);

        when(boletaRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Boleta> resultado = boletaServicio.getAll();

        assertEquals(2, resultado.size());
        
    }

    @Test
    public void testGuardarBoleta() {
        Boleta boleta = new Boleta();
        boleta.setNombreEmpresa("PerfulandiaSPA");
        boleta.setRutEmpresa("76.087.419-1");
        boleta.setCantidadProducto(5);
        boleta.setTotalBoleta(99990);
        boleta.setFormaPago("Transferencia");
        boleta.setUsuarioId(3);

        when(boletaRepository.save(boleta)).thenReturn(boleta);

        Boleta resultado = boletaServicio.save(boleta);

        assertEquals("Transferencia", resultado.getFormaPago());
        assertEquals(99990, resultado.getTotalBoleta());
    }

    @Test
    public void testGetBoletaById() {
        Boleta boleta = new Boleta();
        boleta.setId(10);
        boleta.setNombreEmpresa("Perfulandia");
        boleta.setTotalBoleta(120000);

        when(boletaRepository.findById(10)).thenReturn(Optional.of(boleta));

        Boleta resultado = boletaServicio.getBoletaById(10);

        assertNotNull(resultado);
        assertEquals("Perfulandia", resultado.getNombreEmpresa());
    }

    @Test
    public void testGetBoletaByIdNoExiste() {
        when(boletaRepository.findById(99)).thenReturn(Optional.empty());

        Boleta resultado = boletaServicio.getBoletaById(99);

        assertNull(resultado);
    }

    @Test
    public void testEliminarBoleta() {
        Boleta boleta = new Boleta();
        boleta.setId(8);

        when(boletaRepository.findById(8)).thenReturn(Optional.of(boleta));

        boletaServicio.deleteBoletaById(8);

        verify(boletaRepository).deleteById(8);
    }


}




