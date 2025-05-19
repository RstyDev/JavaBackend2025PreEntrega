package modelos.pedidos;

import modelos.excepciones.InvalidException;
import modelos.productos.Producto;

public class Item {
    private int cantidad;
    private Producto producto;

    public Item(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public double getCosto() {
        return this.producto.getPrecio() * this.cantidad;
    }

    @Override
    public String toString() {
        return "\nItem {" +
                "\n    cantidad = " + cantidad +
                ",\n    producto = " + producto +
                "\n}\n";
    }

    public void confirmar() throws InvalidException {
        try {
            this.producto.restarCantidad(this.cantidad);
        } catch (InvalidException e) {
            throw e;
        }
    }
}
