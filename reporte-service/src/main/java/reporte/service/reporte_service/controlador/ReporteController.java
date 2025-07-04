package reporte.service.reporte_service.controlador;

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

import reporte.service.reporte_service.entidades.Reporte;
import reporte.service.reporte_service.repositorio.ReporteRepository;
import reporte.service.reporte_service.servicio.ReporteService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    private final ReporteRepository reporteRepository;
    @Autowired

    private ReporteService reporteService;

    ReporteController(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Reporte>>obtenerReporte(@PathVariable("id")int id){
        Reporte reporte = reporteService.getReporteById(id);
        if (reporte==null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Reporte> recurso = EntityModel.of(reporte);

        recurso.add(linkTo(methodOn(ReporteController.class).obtenerReporte(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ReporteController.class).listarReportes()).withRel("todos-los-reportes"));

        return ResponseEntity.ok(recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reporte> eliminarReporte(@PathVariable("id")int id){
        Reporte reporte = reporteService.getReporteById(id);
        if (reporte==null) {
            return ResponseEntity.notFound().build();
        }
        reporteService.deleteReporteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes(){
        List<Reporte> reportes = reporteService.getAll();
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping
    public ResponseEntity<Reporte> guerdarReporte(@RequestBody Reporte reporte){
        Reporte nuevoReporte = reporteService.save(reporte);
        return ResponseEntity.ok(nuevoReporte);
    } 
}
