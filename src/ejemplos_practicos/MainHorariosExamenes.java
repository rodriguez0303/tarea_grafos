
package ejemplos_practicos;

public class MainHorariosExamenes {

    public static void main(String[] args) {

        // Nombres de las materias (vértices del grafo).
        String[] materias = {
                "Matemática",
                "Física",
                "Programación",
                "Inglés"
        };

        // Matriz de adyacencia (grafo no dirigido).
        int[][] grafo = new int[materias.length][materias.length];

        // Relaciones de conflicto entre exámenes.
        grafo[0][1] = 1; // Matemática - Física
        grafo[1][0] = 1;

        grafo[0][2] = 1; // Matemática - Programación
        grafo[2][0] = 1;

        grafo[1][3] = 1; // Física - Inglés
        grafo[3][1] = 1;

        // Mostrar los conflictos entre materias.
        System.out.println("CONFLICTOS DE HORARIOS");
        System.out.println("-----------------------------");

        for (int i = 0; i < materias.length; i++) {

            for (int j = i + 1; j < materias.length; j++) {

                if (grafo[i][j] == 1) {
                    System.out.println(materias[i] + " - " + materias[j]);
                }
            }
        }

        // Asignación de horarios sin conflictos.
        System.out.println();
        System.out.println("ASIGNACIÓN DE HORARIOS");
        System.out.println("-----------------------------");

        System.out.println("Horario 1:");
        System.out.println(materias[0]); // Matemática
        System.out.println(materias[3]); // Inglés

        System.out.println();

        System.out.println("Horario 2:");
        System.out.println(materias[1]); // Física
        System.out.println(materias[2]); // Programación
    }
}
