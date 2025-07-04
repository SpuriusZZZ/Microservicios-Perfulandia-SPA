package factura.service.factura_service.controlador;

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

import factura.service.factura_service.entidades.Factura;
import factura.service.factura_service.servicio.FacturaServicio;
import factura.service.factura_service.dtos.Producto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/factura")
public class FacturaController {
   
    @Autowired
    private FacturaServicio facturaServicio;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas(){
        List<Factura>facturas = facturaServicio.getAll();
        if (facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Factura>>obtenerFactura(@PathVariable("id")int id){
        Factura factura = facturaServicio.getFacturaById(id);
        if (factura== null){
            return ResponseEntity.notFound().build();
        }

        EntityModel<Factura> recurso = EntityModel.of(factura);

        recurso.add(linkTo(methodOn(FacturaController.class).obtenerFactura(id)).withSelfRel());
        recurso.add(linkTo(methodOn(FacturaController.class).listarFacturas()).withRel("todas-las-facturas"));
        recurso.add(linkTo(methodOn(FacturaController.class).listarFacturaPorUsuarioId(id)).withRel("facturas-del-usuario"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Factura> guardarFactura(@RequestBody Factura factura){
        Factura nuevaFactura = facturaServicio.save(factura);
        return ResponseEntity.ok(nuevaFactura);
    }


    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity <List<Factura>> listarFacturaPorUsuarioId(@PathVariable("usuarioId")int id){
        List<Factura> facturas = facturaServicio.byUsuarioId(id);
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{facturaId}/productos")
    public ResponseEntity<List<Producto>> obtenerProductosPorFactura(@PathVariable("facturaId") int facturaId) {
        String url = "http://localhost:8002/producto/boleta/" + facturaId;
        Producto[] productos = restTemplate.getForObject(url, Producto[].class);

        if (productos == null || productos.length == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(Arrays.asList(productos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> eliminarFactura(@PathVariable("id")int id){
        Factura factura = facturaServicio.getFacturaById(id);
        if(factura==null){
            return ResponseEntity.notFound().build();
        }
        facturaServicio.deleteFacturaById(id);
        return ResponseEntity.noContent().build();

    }
    
}
