package boleta.service.boleta_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boleta.service.boleta_service.entidades.Boleta;
import boleta.service.boleta_service.repositorio.BoletaRepository;

@Service
public class BoletaServicio {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> getAll(){
        return boletaRepository.findAll();
    }

    public Boleta getBoletaById(int id){
        return boletaRepository.findById(id).orElse(null);
    }

    public Boleta save (Boleta boleta){
        Boleta nuevaBoleta = boletaRepository.save(boleta);
        return nuevaBoleta;
    }

    public List<Boleta> byUsuarioId(int usuarioId){
        return boletaRepository.findByUsuarioId(usuarioId);
    }

    public void deleteBoletaById(int id){
        boletaRepository.deleteById(id);
    }
}
