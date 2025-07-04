package proveedor.service.proveedor_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proveedor.service.proveedor_service.entidades.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer>{
    /*
     * findAll() --> Retorna a todos los usuarios
     * findById(Integer id) --> retorna el usuario segun la id
     * save (Usuario usuario) -->Guarda el nuevo usuario
     * deleteById(Integer id)--> Elimina un usuario según su id
     */

     List<Proveedor> findByInventarioId(int idInventario);
}