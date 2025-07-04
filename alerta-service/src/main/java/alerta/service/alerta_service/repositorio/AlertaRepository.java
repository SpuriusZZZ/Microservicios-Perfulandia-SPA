package alerta.service.alerta_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alerta.service.alerta_service.entidades.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta,Integer>{
    /*
     * findAll()
     * findById(Integer id)
     * save (Usuario usuario)
     * deleteById(Integer id)
     */
}