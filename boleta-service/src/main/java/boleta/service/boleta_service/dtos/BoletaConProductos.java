package boleta.service.boleta_service.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class BoletaConProductos {
    private int id;
    private String nombreEmpresa;
    private String rutEmpresa;
    private LocalDateTime fecha;
    private int cantidadProducto;
    private double totalBoleta;
    private String formaPago;
    private int usuarioId;
    private List<Producto> productos;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
