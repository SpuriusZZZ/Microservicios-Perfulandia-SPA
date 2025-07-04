package com.producto.service.producto_service;

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

import com.producto.service.producto_service.entidades.Producto;
import com.producto.service.producto_service.repositorio.ProductoRepository;
import com.producto.service.producto_service.servicio.ProductoServicio;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServicio productoServicio;

    @BeforeEach
    public void setup (){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Producto p1 = new Producto();
        p1.setId(1);
        p1.setNombreProducto("perfum de la pam");
        p1.setMarca("ginotos perfumes");
        p1.setPrecioUnitario(145000);
        p1.setUsuarioId(1);
        p1.setBoletaId(1);

        Producto p2 = new Producto();
        p2.setId(2);
        p2.setNombreProducto("perfum de la no pam");
        p2.setMarca("benjitas perfumes");
        p2.setPrecioUnitario(15000);
        p2.setUsuarioId(2);
        p2.setBoletaId(2);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1,p2));

        List<Producto> resultado = productoServicio.getAll();

        assertEquals(2, resultado.size());
        assertEquals("perfum de la no pam", resultado.get(1).getNombreProducto());
    }

    @Test
    public void testGuardarProducto() {
        Producto producto = new Producto();
        producto.setNombreProducto("Yogurt");
        producto.setMarca("Soprole");
        producto.setPrecioUnitario(990);
        producto.setUsuarioId(15);
        producto.setBoletaId(200);

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoServicio.save(producto);

        assertEquals("Yogurt", resultado.getNombreProducto());
        assertEquals("Soprole", resultado.getMarca());
    }

    @Test
    public void testGetProductoById() {
        Producto producto = new Producto();
        producto.setId(3);
        producto.setNombreProducto("Jugo");
        producto.setMarca("Watts");

        when(productoRepository.findById(3)).thenReturn(Optional.of(producto));

        Producto resultado = productoServicio.getProductoById(3);

        assertNotNull(resultado);
        assertEquals("Jugo", resultado.getNombreProducto());
    }

    @Test
    public void testGetProductoByIdNoExiste() {
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        Producto resultado = productoServicio.getProductoById(99);

        assertNull(resultado);
    }

    @Test
    public void testEliminarProducto() {
        Producto producto = new Producto();
        producto.setId(4);
        producto.setNombreProducto("Pan");

        when(productoRepository.findById(4)).thenReturn(Optional.of(producto));

        productoServicio.deleteProductoById(4);

        verify(productoRepository).deleteById(4);
    }
}
