package proveedor.service.proveedor_service.controlador;

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

import proveedor.service.proveedor_service.entidades.Proveedor;
import proveedor.service.proveedor_service.repositorio.ProveedorRepository;
import proveedor.service.proveedor_service.servicio.ProveedorService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;
    @Autowired

    private ProveedorService proveedorService;

    ProveedorController(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedor>> obtenerProveedor(@PathVariable("id")int id){
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if (proveedor == null){
            return ResponseEntity.notFound().build();
        }

        EntityModel<Proveedor> recurso = EntityModel.of(proveedor);

        recurso.add(linkTo(methodOn(ProveedorController.class).obtenerProveedor(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ProveedorController.class).listarInventarios()).withRel("todos-los-usuarios"));

        return ResponseEntity.ok(recurso);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> eliminarProveedor(@PathVariable("id")int id){
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if (proveedor==null) {
            return ResponseEntity.notFound().build();
        }
        proveedorService.deleteProveedorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> listarInventarios(){
        List<Proveedor> proveedores = proveedorService.getAll();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guerdarProveedor(@RequestBody Proveedor proveedor){
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.ok(nuevoProveedor);
    } 

    @GetMapping("/inventario/{inventarioId}")
    public ResponseEntity <List<Proveedor>> listarProveedorPorInventarioId(@PathVariable("inventarioId") int id){
        List<Proveedor> proveedores = proveedorService.byInventarioId(id);
        if(proveedores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }
}
