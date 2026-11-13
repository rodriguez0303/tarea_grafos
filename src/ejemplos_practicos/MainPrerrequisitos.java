
package ejemplos_practicos;

public class MainPrerrequisitos {

    public static void main(String[] args) {

        // Nombres de las materias (vértices del grafo).
        String[] materias = {
                "Introducción a la Programación",
                "Algoritmos I",
                "Bases de Datos I",
                "Algoritmos II",
                "Paradigmas de Programacion"
        };

        // Matriz de adyacencia (grafo dirigido).
        int[][] grafo = new int[materias.length][materias.length];

        // Relaciones de prerrequisito.
        grafo[0][1] = 1; // Introducción a la Programación -> Algoritmos I
        grafo[0][2] = 1; // Introducción a la Programación -> Bases de Datos I
        grafo[1][3] = 1; // Algoritmos I -> Algoritmos II
        grafo[2][4] = 1; // Bases de Datos I -> Proyecto de Programación
        grafo[3][4] = 1; // Algoritmos II -> Paradigmas de Programación

        // Se muestra las relaciones del grafo.
        System.out.println("PRERREQUISITOS UNIVERSITARIOS");
        System.out.println("-----------------------------");

        for (int i = 0; i < materias.length; i++) {

            for (int j = 0; j < materias.length; j++) {

                if (grafo[i][j] == 1) {
                    System.out.println(materias[i] + " -> " + materias[j]);
                }
            }
        }
    }
}
