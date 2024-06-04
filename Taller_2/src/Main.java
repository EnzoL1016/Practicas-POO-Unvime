import java.util.Scanner;

static Scanner leer = new Scanner(System.in);
    public static void main() {
        Grafo grafo = new Grafo();
        int opc;
        do{
            System.out.println("--Menu de Grafo--\n");
            System.out.println("1) Generar Grafo\n2) Crear Arista\n3) Imprimir Grafo\n4) Buscar Camino\n0) Salir\n");
            opc = leer.nextInt();
            switch (opc){
                case 1:
                    Utils.generarGrafo(grafo);
                    break;
                    case 2:
                        Utils.agregarArista(grafo);
                        break;
                        case 3:
                            Utils.imprimirGrafo(grafo);
                            break;
                            case 4:
                                int etiqueta = Utils.existeCamino(grafo);
                                if(etiqueta == -1){
                                    System.out.println("No Encontrado");
                                }else{
                                    System.out.println("Encontrado --> <Etiqueta: "+etiqueta+">");
                                }
                                break;
                                case 0:
                                    System.out.println("Saliendo...");
                                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while(opc != 0);
    }
