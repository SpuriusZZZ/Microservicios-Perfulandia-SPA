package rol.service.rol_service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.modelos.Usuario;
import rol.service.rol_service.repositorio.RolRepository;
import rol.service.rol_service.servicio.RolService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/rol")
public class RolController {
    private final RolRepository rolRepository;
    @Autowired
    private RolService rolService;


    RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>>obtenerRol(@PathVariable("id")int id){
        Rol rol = rolService.getRolById(id);
        if(rol==null){
            return ResponseEntity.notFound().build();
        }

        EntityModel<Rol> recurso = EntityModel.of(rol);

        recurso.add(linkTo(methodOn(RolController.class).obtenerRol(id)).withSelfRel());
        recurso.add(linkTo(methodOn(RolController.class).listarRol()).withRel("todos-los-roles"));
        recurso.add(linkTo(methodOn(RolController.class).listarUsuario(id)).withRel("roles-del-usuario"));

        return ResponseEntity.ok(recurso);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable("id")int id){
        Rol rol = rolService.getRolById(id);
        if(rol==null){
            return ResponseEntity.notFound().build();
        }
        rolService.deleteRolById(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping
    public ResponseEntity<List<Rol>> listarRol(){

        List<Rol> roles = rolService.getAll();
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }


    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol){
        Rol nuevoRol = rolService.save(rol);
        return ResponseEntity.ok(nuevoRol);
    }

    @GetMapping("/usuarios/{rolId}")
    public ResponseEntity<List<Usuario>>listarUsuario(@PathVariable("rolId")int id){
        Rol rol = rolService.getRolById(id);
        if(rol==null){
            return ResponseEntity.notFound().build();
        }
        List<Usuario>usuarios = rolService.getUsuarios(id);
        return ResponseEntity.ok(usuarios);
    }



}


