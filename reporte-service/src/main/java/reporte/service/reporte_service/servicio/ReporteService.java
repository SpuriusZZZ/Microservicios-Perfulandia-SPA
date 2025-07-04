package reporte.service.reporte_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reporte.service.reporte_service.entidades.Reporte;
import reporte.service.reporte_service.repositorio.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getAll(){
        return reporteRepository.findAll();
    }

    public Reporte getReporteById(int id){
        return reporteRepository.findById(id).orElse(null);
    }

    public Reporte save(Reporte reporte){
        Reporte nuevoReporte = reporteRepository.save(reporte);
        return nuevoReporte;
    }

    public void deleteReporteById(int id){
        reporteRepository.deleteById(id);
    }
}
