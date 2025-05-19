import modelos.excepciones.StockInsuficienteException;
import modelos.pedidos.Item;
import modelos.pedidos.Lista;
import modelos.pedidos.Pedido;
import modelos.productos.Producto;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Lista lista = new Lista();

        List<Pedido> pedidos = new ArrayList<>();



        int selection = 0;
        do {
            System.out.println(
                    "Elija una opcion:\n" +
                            "1) Agregar producto\n" +
                            "2) Listar productos\n" +
                            "3) Buscar producto\n" +
                            "4) Eliminar producto\n" +
                            "5) Crear pedido\n" +
                            "6) Listar pedidos\n" +
                            "7) Salir\n");
            try {
                selection = scanner.nextInt();
                if (selection < 1 || selection > 7){
                    throw new InputMismatchException("Numero ingresado: "+selection);
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                String message = e.getMessage() != null ? e.getMessage() : e.toString();
                System.out.println("Por favor ingrese un numero entre las opciones mencionadas, "+message);
                scanner.nextLine();
                continue;
            }
            switch (selection) {
                case 1:
                    try {
                        lista.agregarProducto();
                    } catch (InputMismatchException e) {
                        String message = e.getMessage() != null ? e.getMessage() : e.toString();
                        System.out.println("Error al agregar producto: "+e);
                    }
                    break;
                case 2:
                    lista.mostrarProductos();
                    break;
                case 3:
                    lista.buscarPorId();
                    break;
                case 4:
                    lista.borrarProducto();
                    break;
                case 5:
                    Optional<Pedido> pedido = crearPedido(lista);
                    if (pedido.isPresent()) {
                        pedidos.add(pedido.get());
                        for (Item item:pedido.get().getItems()) {
                            Optional<Producto> prod = lista.getById(item.getProducto().getId());
                            if (prod.isPresent()) {
                                prod.get().setStock(prod.get().getStock() - item.getCantidad());
                            }
                        }
                    } else {
                        System.out.println("Pedido cancelado");
                    }
                    break;
                case 6:
                    System.out.println(pedidos);
                    break;
            }
        } while (selection != 7);
        System.out.println("Programa terminado");
    }
    private static Optional<Pedido> crearPedido(Lista lista){
        int seleccion = 0;
        Pedido pedido = new Pedido();
        Optional<Pedido> resultado = Optional.empty();
        do {
            System.out.println(
                    "Elija una opcion:\n" +
                            "1) Agregar producto al pedido\n" +
                            "2) Listar productos del pedido\n" +
                            "3) Confirmar pedido\n" +
                            "4) Cancelar pedido\n");
            try {
                seleccion = scanner.nextInt();
                if (seleccion < 1 || seleccion > 4){
                    throw new InputMismatchException("Numero ingresado: "+seleccion);
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                String message = e.getMessage() != null ? e.getMessage() : e.toString();
                System.out.println("Por favor ingrese un numero entre las opciones mencionadas, "+message);
                scanner.nextLine();
                continue;
            }
            switch (seleccion) {
                case 1:
                    Optional<Producto> prod = lista.buscarPorNombre();
                    System.out.println("Ingrese la cantidad de producto");
                    int cantidad = 0;
                    try {
                        cantidad = scanner.nextInt();
                        if (cantidad < 1){
                            throw new InputMismatchException("Numero ingresado: "+cantidad);
                        }
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        String message = e.getMessage() != null ? e.getMessage() : e.toString();
                        System.out.println("Por favor ingrese un numero mayor a 0, "+message);
                        scanner.nextLine();
                        continue;
                    }
                    if (prod.isPresent()) {
                        try {
                            pedido.agregarProducto(cantidad,prod.get());
                        } catch (StockInsuficienteException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Producto no encontrado");
                    }
                    break;
                case 2:
                    pedido.mostrarPedido();
                    break;
                case 3:
                    return Optional.of(pedido);
                case 4:
                    return Optional.empty();
            }
        } while (seleccion != 4);
        return resultado;
    }
}