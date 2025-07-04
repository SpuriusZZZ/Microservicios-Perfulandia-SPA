package proveedor.service.proveedor_service.modelos;

public class Inventario {
    private int stockActual;
    private int productoId;
    
    public int getStockActual() {
        return stockActual;
    }
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
    public int getProductoId() {
        return productoId;
    }
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
}
