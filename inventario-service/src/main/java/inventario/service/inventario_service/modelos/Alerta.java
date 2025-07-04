package inventario.service.inventario_service.modelos;

import java.time.LocalDateTime;

public class Alerta {
    private LocalDateTime fecha = LocalDateTime.now();
    private String mensaje;
    private int inventarioId;

    public Alerta(){
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getInventarioId() {
        return inventarioId;
    }
    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

}
