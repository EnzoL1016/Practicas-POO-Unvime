import java.util.Arrays;

public class Grafo {
    private final int MAX_NODOS = 10;
    private int[][] matrizEtiquetas;
    int maxNodo;

    public Grafo() {
        matrizEtiquetas = new int[MAX_NODOS][MAX_NODOS];
        for (int i = 0; i < MAX_NODOS; i++) {
            Arrays.fill(matrizEtiquetas[i], -1);
        }
    }

    public int[][] getMatrizEtiquetas() {
        return matrizEtiquetas;
    }

    public void setMaxNodo(int nodo) {
        if (nodo > maxNodo) {
            maxNodo = nodo;
        }
    }

    public int getMaxNodo() {
        return maxNodo;
    }
}
