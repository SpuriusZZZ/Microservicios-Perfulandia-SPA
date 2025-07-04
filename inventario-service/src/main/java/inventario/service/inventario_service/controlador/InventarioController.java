package inventario.service.inventario_service.controlador;

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

import inventario.service.inventario_service.entidades.Inventario;
import inventario.service.inventario_service.modelos.Alerta;
import inventario.service.inventario_service.modelos.Producto;
import inventario.service.inventario_service.modelos.Reporte;
import inventario.service.inventario_service.repositorio.InventarioRepository;
import inventario.service.inventario_service.servicio.InventarioService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioRepository inventarioRepository;

    @Autowired

    private InventarioService inventarioService;

    InventarioController(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Inventario>>obtenerInventario(@PathVariable("id")int id){
        Inventario inventario = inventarioService.getInventarioById(id);
        if (inventario==null) {
            return ResponseEntity.notFound().build();
        }
        
        EntityModel<Inventario> recurso = EntityModel.of(inventario);

        recurso.add(linkTo(methodOn(InventarioController.class).obtenerInventario(id)).withSelfRel());
        recurso.add(linkTo(methodOn(InventarioController.class).listarInventarios()).withRel("todos-los-inventarios"));
        recurso.add(linkTo(methodOn(InventarioController.class).listarAlerta(id)).withRel("alertas-del-inventario"));

        return ResponseEntity.ok(recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventario> eliminarInventario(@PathVariable("id")int id){
        Inventario inventario = inventarioService.getInventarioById(id);
        if (inventario==null) {
            return ResponseEntity.notFound().build();
        }
        inventarioService.deleteInventarioById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventarios(){
        List<Inventario> inventarios = inventarioService.getAll();
        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping
    public ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario){
        Inventario nuevoInventario = inventarioService.save(inventario);
        return ResponseEntity.ok(nuevoInventario);
    }


    @GetMapping("/productos/{inventarioId}")
    public ResponseEntity<List<Producto>>listarProducto(@PathVariable("inventarioId")int id){
        Inventario inventario = inventarioService.getInventarioById(id);
        if(inventario==null){
            return ResponseEntity.notFound().build();
        }
        List<Producto>productos = inventarioService.getProductos(id);
        return ResponseEntity.ok(productos);
    } 

    @GetMapping("/alertas/{inventarioId}")
    public ResponseEntity<List<Alerta>> listarAlerta(@PathVariable("inventarioId")int id){
        Inventario inventario = inventarioService.getInventarioById(id);
        if(inventario==null){
            return ResponseEntity.notFound().build();
        }
        List<Alerta>alertas = inventarioService.getAlertas(id);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/reportes/{inventarioId}")
    public ResponseEntity<List<Reporte>> listarReporte(@PathVariable("inventarioId")int id){
        Inventario inventario = inventarioService.getInventarioById(id);
        if(inventario==null){
            return ResponseEntity.notFound().build();
        }
        List<Reporte>reportes = inventarioService.getReportes(id);
        return ResponseEntity.ok(reportes);
    }
}