package inventario.service.inventario_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventario.service.inventario_service.entidades.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer>{
    /*
     * findAll()
     * findById(Integer id)
     * save (Usuario usuario)
     * deleteById(Integer id)
     */
}