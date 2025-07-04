package factura.service.factura_service;

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

import factura.service.factura_service.entidades.Factura;
import factura.service.factura_service.repositorio.FacturaRepository;
import factura.service.factura_service.servicio.FacturaServicio;

public class FacturaServiceTest {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServicio facturaServicio;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Factura f1 = new Factura();
        f1.setId(1);
        f1.setRazonSocial("Maribel");
        f1.setRutEmpresa("70.707.070-7");
        f1.setFecha(null);
        f1.setCantidadProducto(6);
        f1.setTotalFactura(650000);
        f1.setFormaPago("Credito");
        f1.setUsuarioId(1);

        when(facturaRepository.findAll()).thenReturn(Arrays.asList(f1));

        List<Factura> resultado = facturaServicio.getAll();

        assertEquals(1, resultado.size());
        
    }

    @Test
    public void testGuardarFactura() {
        Factura factura = new Factura();
        factura.setRazonSocial("Comercial S.A.");
        factura.setRutEmpresa("99.999.999-9");
        factura.setCantidadProducto(10);
        factura.setTotalFactura(900000);
        factura.setFormaPago("Transferencia");
        factura.setUsuarioId(2);

        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura resultado = facturaServicio.save(factura);

        assertEquals("Comercial S.A.", resultado.getRazonSocial());
        assertEquals(900000, resultado.getTotalFactura());
    }

    @Test
    public void testGetFacturaById() {
        Factura factura = new Factura();
        factura.setId(5);
        factura.setRazonSocial("Empresa X");

        when(facturaRepository.findById(5)).thenReturn(Optional.of(factura));

        Factura resultado = facturaServicio.getFacturaById(5);

        assertNotNull(resultado);
        assertEquals("Empresa X", resultado.getRazonSocial());
    }

    @Test
    public void testGetFacturaByIdNoExiste() {
        when(facturaRepository.findById(99)).thenReturn(Optional.empty());

        Factura resultado = facturaServicio.getFacturaById(99);

        assertNull(resultado);
    }

    @Test
    
    public void testEliminarFactura() {
        Factura factura = new Factura();
        factura.setId(6);

        when(facturaRepository.findById(6)).thenReturn(Optional.of(factura));

        facturaServicio.deleteFacturaById(6);

        verify(facturaRepository).deleteById(6);
    }


}
