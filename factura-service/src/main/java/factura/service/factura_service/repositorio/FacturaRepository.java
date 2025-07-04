package factura.service.factura_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factura.service.factura_service.entidades.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Integer> {
    List <Factura> findByusuarioId(int usuarioId);
}

