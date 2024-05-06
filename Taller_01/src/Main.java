import java.util.List;
import java.util.Scanner;
public class Main {
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {
        List<Vertice> vertices;
        int opc;
        vertices = Utils.verticesDefault();

        do {
            System.out.println("Elija una opcion:\n1) Añadir Vertice\n2) Chequear vacio\n3) Buscar Vertice\n4) Modificar Vertice\n5) Eliminar Vertice\n6) Ver Vertices\n7) Salir");
            opc = leer.nextInt();
            switch (opc) {
                case 1:
                    vertices.add(Utils.insert_vertice(Utils.ingresarNombre(), Utils.ingresarNumero()));
                    break;
                case 2:
                    Utils.checkVacio(vertices);
                    break;
                case 3:
                    Utils.buscarVertice(vertices, Utils.ingresarNombre());
                    break;
                case 4:
                    Utils.modVertice(vertices, Utils.ingresarNombre());
                    break;
                case 5:
                    Utils.deleteVertice(vertices, Utils.ingresarNombre());
                    break;
                case 6:
                    for(Vertice v : vertices){
                        System.out.println(v.getNombre()+" --> "+v.getNumero());
                    }
                    System.out.println("\n");
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
                    break;
            }
        }while (opc != 7);
    }
}
