package inventario.service.inventario_service.modelos;

import java.time.LocalDateTime;

public class Boleta {
    private String nombreEmpresa = "PerfulandiaSPA";
    private String rutEmpresa = "76.087.419-1";
    private LocalDateTime fecha = LocalDateTime.now();
    private int cantidadProducto;
    private double totalBoleta;
    private String formaPago;
    private int usuarioId;

    public Boleta() {
    }
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public String getRutEmpresa() {
        return rutEmpresa;
    }
    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public int getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public double getTotalBoleta() {
        return totalBoleta;
    }
    public void setTotalBoleta(double totalBoleta) {
        this.totalBoleta = totalBoleta;
    }
    public String getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
