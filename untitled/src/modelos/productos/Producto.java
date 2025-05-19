package modelos.productos;

import modelos.excepciones.InvalidException;

public class Producto {
    private static int maxId = 0;
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public static int  getMaxId(){
        return maxId;
    }
    public Producto(String nombre, double precio, int stock) {
        this.id = maxId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) throws InvalidException {
        if (stock < 0 ){
            throw new InvalidException("El stock no puede ser menor a 0");
        }
        this.stock = stock;
    }

    public void restarCantidad(int stock) throws InvalidException {
        if (stock < this.stock) {
            throw new InvalidException("El stock no puede ser menor a 0");
        }
        this.stock -= stock;
    }

    @Override
    public String toString() {
        return "\nProducto {" +
                "\n    id = " + id +
                ",\n    nombre = '" + nombre + '\'' +
                ",\n    precio = " + precio +
                ",\n    stock = " + stock +
                "\n}\n";
    }
}
