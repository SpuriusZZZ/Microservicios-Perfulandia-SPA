package reporte.service.reporte_service.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Reporte {
    @Id
    @GeneratedValue

    private int id;
    private String tipoReporte = "Reporte de ventas por inventario";
    private LocalDateTime fecha = LocalDateTime.now();
    private int totalProductoVenta;
    private int totalRecaudadoProductos;
    private String mensaje;
    private int inventarioId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoReporte() {
        return tipoReporte;
    }
    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public int getTotalProductoVenta() {
        return totalProductoVenta;
    }
    public void setTotalProductoVenta(int totalProductoVenta) {
        this.totalProductoVenta = totalProductoVenta;
    }
    public int getTotalRecaudadoProductos() {
        return totalRecaudadoProductos;
    }
    public void setTotalRecaudadoProductos(int totalRecaudadoProductos) {
        this.totalRecaudadoProductos = totalRecaudadoProductos;
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
