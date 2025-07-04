package rol.service.rol_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.modelos.Usuario;
import rol.service.rol_service.repositorio.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAll(){
        return rolRepository.findAll();
    }

    public Rol getRolById(int id){
        return rolRepository.findById(id).orElse(null);
    }

    public Rol save(Rol rol){
        Rol nuevoRol = rolRepository.save(rol);
        return nuevoRol;
    }

    public void deleteRolById(int id){
        rolRepository.deleteById(id);
    }

    @Autowired
    private RestTemplate restTemplate;
    public List<Usuario> getUsuarios(int rolId){
        List<Usuario>roles = restTemplate.getForObject("http://localhost:8008/rol/usuarios/"+rolId, List.class);
        return roles;
    }

    
}
