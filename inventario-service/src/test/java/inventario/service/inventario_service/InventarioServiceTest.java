package inventario.service.inventario_service;

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

import inventario.service.inventario_service.entidades.Inventario;
import inventario.service.inventario_service.repositorio.InventarioRepository;
import inventario.service.inventario_service.servicio.InventarioService;

public class InventarioServiceTest {
    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Inventario i1 = new Inventario();
        i1.setId(1);
        i1.setProductoId(1);
        i1.setStockActual(50);
        
        when(inventarioRepository.findAll()).thenReturn(Arrays.asList(i1));
        
        List<Inventario> resultado = inventarioService.getAll();

        assertEquals(1, resultado.size());
    }

    @Test
    public void testGuardarInventario() {
        Inventario inv = new Inventario();
        inv.setProductoId(2);
        inv.setStockActual(30);

        when(inventarioRepository.save(inv)).thenReturn(inv);

        Inventario resultado = inventarioService.save(inv);

        assertEquals(2, resultado.getProductoId());
        assertEquals(30, resultado.getStockActual());
    }

    @Test
    public void testGetInventarioById() {
        Inventario inv = new Inventario();
        inv.setId(3);
        inv.setProductoId(5);
        inv.setStockActual(10);

        when(inventarioRepository.findById(3)).thenReturn(Optional.of(inv));

        Inventario resultado = inventarioService.getInventarioById(3);

        assertNotNull(resultado);
        assertEquals(10, resultado.getStockActual());
    }

    @Test
    public void testGetInventarioByIdNoExiste() {
        when(inventarioRepository.findById(100)).thenReturn(Optional.empty());

        Inventario resultado = inventarioService.getInventarioById(100);

        assertNull(resultado);
    }

    @Test
    public void testEliminarInventario() {
        Inventario inv = new Inventario();
        inv.setId(4);

        when(inventarioRepository.findById(4)).thenReturn(Optional.of(inv));

        inventarioService.deleteInventarioById(4);

        verify(inventarioRepository).deleteById(4);
    }
}
