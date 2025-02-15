import java.util.Scanner;

class Producto {
    int id;
    String nombre;
    int stockInicial;
    int cantidadVendida;

    // Constructor
    public Producto(int id , String nombre, int stockInicial, int cantidadVendida) {
        this.id = id;
        this.nombre = nombre;
        this.stockInicial = stockInicial;
        this.cantidadVendida = cantidadVendida;
    }

    public int stock(){
        return stockInicial - cantidadVendida;
    }

    public boolean ventas(int dato){
        if (stock() >= dato){
            cantidadVendida += dato;
            return true;
        }
        else {
            return false;
        }
    }
    int nuevo = 0;
    public void duplico(){
        if(stock() == 0){
             nuevo = stockInicial * 2;
            System.out.println("Este inventario a sido duplicado");
        }
    }

    public void info(){
        System.out.println("Informacion del producto " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Stock al iniciar el dia: " + stockInicial);
        System.out.println("Cantidad vendida: " + cantidadVendida);
        System.out.println("Stock disponible: " + stock());
    }
    public void info2(){
        System.out.println("Informacion del producto " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Stock al iniciar el dia: " + stockInicial);
        System.out.println("Cantidad vendida: " + cantidadVendida);
        System.out.println("Stock disponible: " + nuevo);
    }

}

public class Programa {
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        try {
            boolean salir = false;
            System.out.println("Gestión de inventarios");
            int dato = validar(scanner, "Ingrese la cantidad de productos: ");

            Producto[] productos = new Producto[dato];

            while(!salir){
                System.out.println();
                System.out.println("Gestión de inventarios");
                System.out.println("1. Ingresar productos");
                System.out.println("2. Verificar inventarios");
                System.out.println("3. Registrar venta");
                System.out.println("4. Cerrar programa");

                int opcion = validar(scanner, "Seleccione una opcion: ");

                switch (opcion){
                    case 1:
                        System.out.println("------------------------------");
                        for (int i = 0 ; i < dato; i++) {
                            int idgen = i +1;
                            System.out.print("Ingrese el nombre del producto " + (i +1)+ ": ");
                            String nombre = scanner.next();

                            int stockInicial = validar(scanner, "Cantidad inicial en stock: ");

                            int cantidadVendida = validar(scanner, "Cantidad vendida: ");
                            scanner.nextLine();

                            productos[i] = new Producto(idgen ,nombre, stockInicial, cantidadVendida);
                        }
                        break;
                    case 2:
                        System.out.println();

                        for (Producto producto : productos){
                            System.out.println("------------------------------");
                            producto.info();
                        if (producto.stock() == 0){
                            producto.duplico();
                            producto.info2();
                        }
                    }
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Productos disponibles");
                        for (Producto producto : productos){
                            System.out.println("ID: "+ producto.id + " ,Nombre: " + producto.nombre + ", Stock disponible: " + producto.stock());
                        }
                        boolean encontrado = false;
                        int idbus = validar(scanner,"Ingrese el numero del producto a vender: ");
                        for (Producto producto : productos) {
                            if (producto.id == idbus) {
                                encontrado = true;

                                int vender = validar(scanner, "Ingrese la cantidad a vender: ");

                                if (producto.ventas(vender)) {
                                    System.out.println("✅ Hay suficiente stock disponible.");
                                    System.out.println("✅ Venta exitosa.");
                                    System.out.println("🛒 Total Restante en stock: " + producto.stock());
                                } else {
                                    System.out.println("❌ No hay suficiente stock disponible.");
                                }
                            } else {
                                continue;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("!!Producto no encontrado en el inventario!!");
                        }

                        break;
                    case 4:
                        salir = true;
                        System.out.println("Cerrando programa");
                        break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int validar(Scanner scanner, String message){
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Ingrese un número entero: ");
        }
        return scanner.nextInt();

    }
}