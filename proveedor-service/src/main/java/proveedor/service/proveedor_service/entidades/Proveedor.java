package proveedor.service.proveedor_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreProveedor;
    private String tipoDeVenta;
    private String productoVenta;
    private int inventarioId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    public String getTipoDeVenta() {
        return tipoDeVenta;
    }
    public void setTipoDeVenta(String tipoDeVenta) {
        this.tipoDeVenta = tipoDeVenta;
    }
    public String getProductoVenta() {
        return productoVenta;
    }
    public void setProductoVenta(String productoVenta) {
        this.productoVenta = productoVenta;
    }
    public int getInventarioId() {
        return inventarioId;
    }
    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }
    
    
}
