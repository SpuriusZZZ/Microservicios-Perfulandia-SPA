package inventario.service.inventario_service.modelos;

import java.time.LocalDateTime;

public class Reporte {
    private String tipoReporte = "Reporte de ventas por inventario";
    private LocalDateTime fecha = LocalDateTime.now();
    private int totalProductoVenta;
    private int totalRecaudadoProductos;
    private int inventarioId;
    private String mensaje;
    
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
    public int getInventarioId() {
        return inventarioId;
    }
    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
