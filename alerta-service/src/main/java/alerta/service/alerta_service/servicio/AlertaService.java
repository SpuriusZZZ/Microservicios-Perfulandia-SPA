package alerta.service.alerta_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alerta.service.alerta_service.entidades.Alerta;
import alerta.service.alerta_service.repositorio.AlertaRepository;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public List<Alerta> getAll(){
        return alertaRepository.findAll();
    }

    public Alerta getAlertaById(int id){
        return alertaRepository.findById(id).orElse(null);
    }

    public Alerta save(Alerta alerta){
        Alerta nuevaAlerta = alertaRepository.save(alerta);
        return nuevaAlerta;
    }

    public void deleteAlertaById(int id){
        alertaRepository.deleteById(id);
    }
}