package proveedor.service.proveedor_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proveedor.service.proveedor_service.entidades.Proveedor;
import proveedor.service.proveedor_service.repositorio.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAll(){
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(int id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor save(Proveedor proveedor){
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return nuevoProveedor;
    }

    public void deleteProveedorById(int id){
        proveedorRepository.deleteById(id);
    }

    public List<Proveedor> byInventarioId(int inventarioId){
        return proveedorRepository.findByInventarioId(inventarioId);
    }
}
