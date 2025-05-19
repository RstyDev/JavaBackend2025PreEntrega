package modelos.pedidos;

import modelos.excepciones.InvalidException;
import modelos.excepciones.StockInsuficienteException;
import modelos.productos.Producto;

import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private List<Item> items;

    public Pedido() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(int cantidad, Producto producto) throws StockInsuficienteException {
        boolean found = false;
        for (Item item: this.items) {
            if (producto.getId() == item.getProducto().getId()) {
                if (cantidad > (producto.getStock() - item.getCantidad())) {
                    throw new StockInsuficienteException("Cantidad ingresada menor a la cantidad restante");
                } else {
                    found = true;
                    item.agregarCantidad(cantidad);
                }
            }
        }
        if (!found) {
            if (cantidad > producto.getStock()) {
                throw new StockInsuficienteException("Cantidad ingresada menor a la cantidad restante");
            }
            this.items.add(new Item(cantidad, producto));
        }
    }

    public List<Item> getItems() {
        return this.items;
    }

    public double getCostoTotal() {
        double resultado = 0.0;
        for (Item item:this.items) {
            resultado += item.getCosto();
        }
        return resultado;
    }

    public void confirmarPedido() throws InvalidException {
        for (Item item:this.items) {
            item.confirmar();
        }
    }

    @Override
    public String toString() {
        return "\nPedido {" +
                "\n    items = " + items +
                "\n    total = "+ this.getCostoTotal() +
                "\n}\n";
    }

    public void mostrarPedido() {
        System.out.println(this.toString());
    }
}
