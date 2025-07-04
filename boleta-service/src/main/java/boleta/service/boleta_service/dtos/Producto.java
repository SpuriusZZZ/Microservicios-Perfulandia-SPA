package boleta.service.boleta_service.dtos;

public class Producto {
    private int id;
    private String nombreProducto;
    private String marca;
    private double precioUnitario;
    private int usuarioId;
    private int boletaId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public int getBoletaId() {
        return boletaId;
    }
    public void setBoletaId(int boletaId) {
        this.boletaId = boletaId;
    }
}
