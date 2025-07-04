package inventario.service.inventario_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import inventario.service.inventario_service.entidades.Inventario;
import inventario.service.inventario_service.modelos.Alerta;
import inventario.service.inventario_service.modelos.Producto;
import inventario.service.inventario_service.modelos.Reporte;
import inventario.service.inventario_service.repositorio.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private RestTemplate restTemplate;
    public List<Producto> getProductos(int productoId){
        List<Producto>productos = restTemplate.getForObject("http://localhost:8002/producto/inventario/"+productoId, List.class);
        return productos;
    }

    @Autowired
    private RestTemplate restTemplate2;
    public List<Alerta> getAlertas(int alertaId){
        List<Alerta>alertas = restTemplate2.getForObject("http://localhost:8006/alerta/inventario/"+alertaId, List.class);
        return alertas;
    }

    @Autowired
    private RestTemplate restTemplate3;
    public List<Reporte> getReportes(int reporteId){
        List<Reporte>reportes = restTemplate3.getForObject("http://localhost:8005/reporte/inventario/"+reporteId, List.class);
        return reportes;
    }

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getAll(){
        return inventarioRepository.findAll();
    }

    public Inventario getInventarioById(int id){
        return inventarioRepository.findById(id).orElse(null);
    }

    public Inventario save(Inventario inventario){
        Inventario nuevoInventario = inventarioRepository.save(inventario);
        return nuevoInventario;
    }

    public void deleteInventarioById(int id){
        inventarioRepository.deleteById(id);
    }
}
