package factura.service.factura_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factura.service.factura_service.entidades.Factura;
import factura.service.factura_service.repositorio.FacturaRepository;

@Service
public class FacturaServicio {
    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getAll(){
        return facturaRepository.findAll();
    }

    public Factura getFacturaById(int id){
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura save (Factura factura){
        Factura nuevaFactura = facturaRepository.save(factura);
        return nuevaFactura;
    }

    public List<Factura> byUsuarioId(int usuarioId){
        return facturaRepository.findByusuarioId(usuarioId);

    } 

    public void deleteFacturaById(int id){
        facturaRepository.deleteById(id);
    }
}
