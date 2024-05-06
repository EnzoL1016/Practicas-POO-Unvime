import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    static Scanner leer = new Scanner(System.in);

    public static void buscarVertice(List<Vertice> lista, String nombre){
        boolean encontrado = false;
        String text="";
        for(Vertice v : lista){
            if(v.getNombre().equalsIgnoreCase(nombre)){
                encontrado = true;
            }
        }
        text = (encontrado) ? "Encontrado!\n" : "No encontrado :(\n";
        System.out.println(text);
    }

    public static void modVertice(List<Vertice> lista, String nombre){
        boolean encontrado = false;
        for(Vertice v : lista){
            if(v.getNombre().equalsIgnoreCase(nombre)){
                System.out.println("Ingrese el nuevo numero del vertice:");
                v.setNumero(leer.nextInt());
                encontrado = true;
            }
        }
        if(encontrado){
            System.out.println("Vertice modificado con exito!\n");
        }else{
            System.out.println("El vertice que desea modificar no se ha encontrado.\n");
        }
    }

    public static void deleteVertice(List<Vertice> lista, String nombre){
        boolean del = false;
        del = lista.removeIf(v -> v.getNombre().equalsIgnoreCase(nombre));
        if(del){
            System.out.println("Vertice eliminado con exito!\n");
        }else{
            System.out.println("El vertice que quiere eliminar no fue encontrado.\n");
        }
    }


    public static void checkVacio(List<Vertice> lista){
        if(lista.isEmpty()){
            System.out.println("El arreglo esta vacio\n");
        }else{
            System.out.println("El arreglo no esta vacio\n");
        }
    }

    public static List<Vertice> verticesDefault(){
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(insert_vertice("Enzo",16));
        vertices.add(insert_vertice("Cindy",18));
        vertices.add(insert_vertice("Hernesto",20));
        vertices.add(insert_vertice("Juan",11));
        vertices.add(insert_vertice("Juana",13));
        return vertices;
    }

    public static String ingresarNombre(){
        String nombre;
        System.out.println("Ingrese el nombre:");
        return nombre = leer.next();
    }
    public static int ingresarNumero(){
        int num;
        System.out.println("Ingrese el numero:");
        return num = leer.nextInt();
    }
    public static Vertice insert_vertice(String nombre, int numero){
        return new Vertice(nombre, numero);
    }
}
