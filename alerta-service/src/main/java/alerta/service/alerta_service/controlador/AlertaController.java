package alerta.service.alerta_service.controlador;

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

import alerta.service.alerta_service.entidades.Alerta;
import alerta.service.alerta_service.repositorio.AlertaRepository;
import alerta.service.alerta_service.servicio.AlertaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/alerta")
public class AlertaController {


    private final AlertaRepository alertaRepository;
    @Autowired


    private AlertaService alertaService;


    AlertaController(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>>obtenerAlerta(@PathVariable("id")int id){
        Alerta alerta = alertaService.getAlertaById(id);
        if (alerta==null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Alerta> recurso = EntityModel.of(alerta);

        recurso.add(linkTo(methodOn(AlertaController.class).obtenerAlerta(id)).withSelfRel());
        recurso.add(linkTo(methodOn(AlertaController.class).listarAlertas   ()).withRel("todas-las-alertas"));

        return ResponseEntity.ok(recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alerta> eliminarAlerta(@PathVariable("id")int id){
        Alerta alerta = alertaService.getAlertaById(id);
        if (alerta==null) {
            return ResponseEntity.notFound().build();
        }
        alertaService.deleteAlertaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Alerta>> listarAlertas(){
        List<Alerta> alertas = alertaService.getAll();
        if (alertas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alertas);
    }

    @PostMapping
    public ResponseEntity<Alerta> guardarAlerta(@RequestBody Alerta alerta){
        Alerta nuevaAlerta = alertaService.save(alerta);
        return ResponseEntity.ok(nuevaAlerta);
    } 
    
}
