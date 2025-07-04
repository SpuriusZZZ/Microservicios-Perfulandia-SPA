package proveedor.service.proveedor_service;

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

import proveedor.service.proveedor_service.entidades.Proveedor;
import proveedor.service.proveedor_service.modelos.Inventario;
import proveedor.service.proveedor_service.repositorio.ProveedorRepository;
import proveedor.service.proveedor_service.servicio.ProveedorService;

public class ProveedorServiceTest {
    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Proveedor p = new Proveedor();
        p.setId(1);
        p.setNombreProveedor("Proveedor A");
        p.setTipoDeVenta("Mayorista");
        p.setProductoVenta("Zapatos");
        p.setInventarioId(10);

        when(proveedorRepository.findAll()).thenReturn(Arrays.asList(p));

        List<Proveedor> resultado = proveedorService.getAll();

        assertEquals(1, resultado.size());
    }

    @Test
    public void testGuardarProveedor() {
        Proveedor p = new Proveedor();
        p.setNombreProveedor("Proveedor B");
        p.setTipoDeVenta("Minorista");
        p.setProductoVenta("Ropa");
        p.setInventarioId(5);

        when(proveedorRepository.save(p)).thenReturn(p);

        Proveedor resultado = proveedorService.save(p);

        assertEquals("Proveedor B", resultado.getNombreProveedor());
        assertEquals("Minorista", resultado.getTipoDeVenta());
        assertEquals("Ropa", resultado.getProductoVenta());
        assertEquals(5, resultado.getInventarioId());
    }

    @Test
    public void testGetProveedorById() {
        Proveedor p = new Proveedor();
        p.setId(3);
        p.setNombreProveedor("Proveedor C");
        p.setTipoDeVenta("Mayorista");
        p.setProductoVenta("Electrónica");
        p.setInventarioId(7);

        when(proveedorRepository.findById(3)).thenReturn(Optional.of(p));

        Proveedor resultado = proveedorService.getProveedorById(3);

        assertNotNull(resultado);
        assertEquals("Electrónica", resultado.getProductoVenta());
    }

    @Test
    public void testGetProveedorByIdNoExiste() {
        when(proveedorRepository.findById(100)).thenReturn(Optional.empty());

        Proveedor resultado = proveedorService.getProveedorById(100);

        assertNull(resultado);
    }

    @Test
    public void testEliminarProveedor() {
        Proveedor p = new Proveedor();
        p.setId(4);

        when(proveedorRepository.findById(4)).thenReturn(Optional.of(p));

        proveedorService.deleteProveedorById(4);

        verify(proveedorRepository).deleteById(4);
    }
}
