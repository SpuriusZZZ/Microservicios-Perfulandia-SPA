package reporte.service.reporte_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reporte.service.reporte_service.entidades.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte,Integer>{
    /*
     * findAll()
     * findById(Integer id)
     * save (Usuario usuario)
     * deleteById(Integer id)
     */
}
