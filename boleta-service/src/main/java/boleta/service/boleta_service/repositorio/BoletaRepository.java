package boleta.service.boleta_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boleta.service.boleta_service.entidades.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Integer>{
    
    List<Boleta> findByUsuarioId(int usuarioId);
}
