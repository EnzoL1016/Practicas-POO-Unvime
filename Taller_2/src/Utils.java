import org.w3c.dom.ls.LSOutput;

import java.util.LinkedList;
import java.util.Scanner;

public class Utils {
    private static final int MAX_NODOS = 10;
    static Scanner leer = new Scanner(System.in);

    public static void agregarArista(Grafo grafo) {
        int inicio, fin, etiqueta;
        System.out.println("A continuacion ingrese:\nInicio:");
        inicio = leer.nextInt();
        System.out.println("Final: ");
        fin = leer.nextInt();
        System.out.println("Etiqueta: ");
        etiqueta = leer.nextInt();

        if (inicio >= 0 && inicio < MAX_NODOS && fin >= 0 && fin < MAX_NODOS && etiqueta >= 0) {
            grafo.getMatrizEtiquetas()[inicio][fin] = etiqueta;
            grafo.setMaxNodo(inicio);
            grafo.setMaxNodo(fin);
        } else {
            System.out.println("Nodos o etiqueta fuera de rango");
        }
    }

    public static void generarGrafo(Grafo grafo) {
        grafo.getMatrizEtiquetas()[0][1] = 1;
        grafo.getMatrizEtiquetas()[1][2] = 2;
        grafo.getMatrizEtiquetas()[2][3] = 3;
        grafo.getMatrizEtiquetas()[3][4] = 4;
        grafo.getMatrizEtiquetas()[4][5] = 5;
        grafo.getMatrizEtiquetas()[0][5] = 6;

        grafo.setMaxNodo(5);
        System.out.println("Grafo generado con exito!\n");
    }


    public static int existeCamino(Grafo grafo) {
        int inicio, fin;
        System.out.println("A continuacion ingrese:\nInicio:");
        inicio = leer.nextInt();
        System.out.println("Final: ");
        fin = leer.nextInt();
        if (inicio < 0 || inicio > grafo.getMaxNodo() || fin < 0 || fin > grafo.getMaxNodo()) {
            return -1;
        }

        boolean[] visitado = new boolean[MAX_NODOS];
        LinkedList<Integer> cola = new LinkedList<>();
        int[] etiquetas = new int[MAX_NODOS];

        cola.add(inicio);
        visitado[inicio] = true;
        etiquetas[inicio] = -1;

        while (!cola.isEmpty()) {
            int actual = cola.poll(); //Devuelve y elimina el primer valor de la lista

            if (actual == fin) {
                return etiquetas[actual];
            }

            for (int i = 0; i < MAX_NODOS; i++) {
                if (grafo.getMatrizEtiquetas()[actual][i] != -1 && !visitado[i]) {
                    cola.add(i);
                    visitado[i] = true;
                    etiquetas[i] = grafo.getMatrizEtiquetas()[actual][i]; // Guarda la etiqueta en el nodo actual
                }
            }
        }
        return -1;
    }

    public static void imprimirGrafo(Grafo grafo) {
        int[][] matrizEtiquetas = grafo.getMatrizEtiquetas();
        System.out.println("Grafo Actual:\n");
        for (int i = 0; i < MAX_NODOS; i++) {
            for (int j = 0; j < MAX_NODOS; j++) {
                if (matrizEtiquetas[i][j] != -1) {
                    System.out.println("("+i +") --> (" + j + ") <Etiqueta: " + matrizEtiquetas[i][j] + ">");
                }
            }
        }
        System.out.println("\n");
    }
}
