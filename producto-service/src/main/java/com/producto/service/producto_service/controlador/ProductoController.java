package com.producto.service.producto_service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producto.service.producto_service.entidades.Producto;
import com.producto.service.producto_service.servicio.ProductoServicio;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto>productos = productoServicio.getAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    // public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") int id){
    public ResponseEntity<EntityModel<Producto>>obtenerProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoById(id);
        if(producto==null){
            return ResponseEntity.notFound().build();
        }
        
        EntityModel<Producto> recurso = EntityModel.of(producto);
        recurso.add(linkTo(methodOn(ProductoController.class).obtenerProducto(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ProductoController.class).listarProductos()).withRel("todos-los-productos"));
        recurso.add(linkTo(methodOn(ProductoController.class).listarProductoPorUsuarioId(id)).withRel("productos-del-usuario"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoServicio.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    //------------------------------------
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity <List<Producto>> listarProductoPorUsuarioId(@PathVariable("usuarioId") int id){
        List<Producto> productos = productoServicio.byUsuarioId(id);
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/boleta/{boletaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorBoletaId(@PathVariable("boletaId") int boletaId) {
        List<Producto> productos = productoServicio.byBoletaId(boletaId);

        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoById(id);
        if(producto==null){
            return ResponseEntity.notFound().build();
        }
        productoServicio.deleteProductoById(id);
        return ResponseEntity.noContent().build();

    }

}
