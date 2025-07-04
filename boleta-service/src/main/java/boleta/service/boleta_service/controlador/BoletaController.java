package boleta.service.boleta_service.controlador;

import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;

import boleta.service.boleta_service.dtos.Producto;
import boleta.service.boleta_service.entidades.Boleta;
import boleta.service.boleta_service.servicio.BoletaServicio;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/boleta")
public class BoletaController {

    @Autowired
    private BoletaServicio boletaServicio;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Boleta>> listarBoletas(){
        List<Boleta>boletas = boletaServicio.getAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Boleta>>obtenerBoleta(@PathVariable("id")int id){
        Boleta boleta = boletaServicio.getBoletaById(id);
        if(boleta==null){
            return ResponseEntity.notFound().build();
        }
        
        EntityModel<Boleta> recurso = EntityModel.of(boleta);

        recurso.add(linkTo(methodOn(BoletaController.class).obtenerBoleta(id)).withSelfRel());
        recurso.add(linkTo(methodOn(BoletaController.class).listarBoletas()).withRel("todos-los-usuarios"));
        recurso.add(linkTo(methodOn(BoletaController.class).listarBoletaPorUsuarioId(id)).withRel("boletas-del-usuario"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Boleta> guardarBoleta(@RequestBody Boleta boleta){
        Boleta nuevaBoleta = boletaServicio.save(boleta);
        return ResponseEntity.ok(nuevaBoleta);
    }

    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity <List<Boleta>> listarBoletaPorUsuarioId(@PathVariable("usuarioId")int id){
        List<Boleta> boletas = boletaServicio.byUsuarioId(id);
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @GetMapping("/{boletaId}/productos")
    public ResponseEntity<List<Producto>> obtenerProductosPorBoleta(@PathVariable("boletaId") int boletaId) {
        String url = "http://localhost:8002/producto/boleta/" + boletaId;
        Producto[] productos = restTemplate.getForObject(url, Producto[].class);

        if (productos == null || productos.length == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(Arrays.asList(productos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boleta> eliminarBoleta(@PathVariable("id")int id){
        Boleta boleta = boletaServicio.getBoletaById(id);
        if(boleta==null){
            return ResponseEntity.notFound().build();
        }
        boletaServicio.deleteBoletaById(id);
        return ResponseEntity.noContent().build();

    }
}
