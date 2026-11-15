
package algoritmo_prim;

public class MainPrim {

    public static void main(String[] args) {

        // Se crea un arreglo que contiene los nombres de las ciudades.
         // Cada ciudad representa un vértice dentro del grafo.
            // Las posiciones comienzan desde 0 hasta 4.
                // Por ejemplo: Tokio = 0, Berlín = 1 y Nairobi = 2.
        String[] ciudades = {
                "Tokio", "Berlín", "Nairobi",
                "Denver", "Helsinki"
        };

        // Se crea una matriz de adyacencia para representar el grafo.
            // Cada fila y columna corresponde a una ciudad.
                // Los valores indican el peso de la conexión entre dos ciudades.
                    // Cuando el valor es 0 significa que no existe una conexión directa.

        // Ejemplo:
        // grafo[0][1] = 4 indica que Tokio se conecta con Berlín.
        // grafo[0][2] = 2 indica que Tokio se conecta con Nairobi.
        // grafo[0][3] = 7 indica que Tokio se conecta con Denver.

        // Como el grafo no es dirigido, las conexiones aparecen
            // en ambas direcciones dentro de la matriz.
        int[][] grafo = {
                {0, 4, 2, 7, 0},
                {4, 0, 3, 0, 0},
                {2, 3, 0, 6, 5},
                {7, 0, 6, 0, 0},
                {0, 0, 5, 0, 0}
        };

        // Se obtiene la cantidad de ciudades almacenadas en el arreglo.
            // y se controla la cantidad de repeticiones de los ciclos.
        int cantidadVertices = ciudades.length;

        // Se crea un arreglo booleano para identificar los vértices
            // que ya fueron incorporados al árbol de expansión mínima.

        // Inicialmente todos tienen el valor false.
            // false = todavía no pertenece al árbol.
            // true = ya pertenece al árbol.
        boolean[] visitados =
                new boolean[cantidadVertices];

        // Se crea un arreglo para guardar el menor peso conocido
            // que permite conectar cada ciudad con el árbol.

        // Ejemplo:
            // Si Berlín inicialmente tiene una conexión de peso 4,
                // pero después encontramos otra de peso 3,
                    // se actualiza su peso de 4 a 3.
        int[] pesos = new int[cantidadVertices];

        // Se crea un arreglo para guardar desde cuál ciudad
            // se realiza la conexión de menor peso.

        // Ejemplo:
        // Si Nairobi permite conectar Berlín con peso 3,
            // padres[1] = 2 porque Berlín está en la posición 1
                // y Nairobi se encuentra en la posición 2.
        int[] padres = new int[cantidadVertices];

        // Este ciclo recorre las cinco posiciones de los arreglos.
         // Se utiliza para establecer los valores iniciales.
        for (int i = 0; i < cantidadVertices; i++) {

            // Se asigna el valor máximo posible de un entero.
                // Esto representa que todavía no conocemos
                 // una conexión de menor peso para ese vértice.
            pesos[i] = Integer.MAX_VALUE;

            // Se asigna -1 porque inicialmente ningún vértice
                // tiene una ciudad padre o conexión seleccionada.
            padres[i] = -1;
        }

        // Se establece Tokio como el vértice inicial.
            // Tokio está en la posición 0 del arreglo ciudades.

        // Su peso es 0 porque es el punto de inicio
            // y no se necesita una arista para incorporarlo al árbol.
        pesos[0] = 0;

        // Este ciclo permite seleccionar los cinco vértices
            // que formarán parte del árbol de expansión mínima.

        // En cada repetición se incorpora un vértice nuevo.
        for (int i = 0; i < cantidadVertices; i++) {

            // Se inicializa el menor peso con un valor muy grande.
                // Posteriormente se reemplazará cuando se encontre
                    // un vértice que tenga un peso menor.
            int menorPeso = Integer.MAX_VALUE;

            // Se utiliza -1 para indicar que todavía
                // no se ha seleccionado un vértice.
            int verticeActual = -1;

            // Este ciclo revisa todas las ciudades para encontrar
                // cuál tiene el menor peso y todavía no fue visitada.
            for (int j = 0; j < cantidadVertices; j++) {

                // Se comprueban dos condiciones:

                // !visitados[j]:
                // Verifica que la ciudad todavía no esté en el árbol.

                // pesos[j] < menorPeso:
                // Verifica que su peso sea menor al encontrado.

                // Ejemplo:
                // Después de revisar Tokio, se revisa:
                // Berlín = 4, Nairobi = 2 y Denver = 7.
                    // Se selecciona Nairobi porque tiene peso 2.
                if (!visitados[j] &&
                        pesos[j] < menorPeso) {

                    // Se guarda el nuevo peso más pequeño.
                    menorPeso = pesos[j];

                    // Se guarda la posición de la ciudad seleccionada.
                        // Ejemplo: Nairobi corresponde a la posición 2.
                    verticeActual = j;
                }
            }

            // Si verticeActual continúa siendo -1,
            // significa que no se encontró otro vértice que pueda incorporarse al árbol.

            // Esto puede ocurrir cuando el grafo no está conectado.
            if (verticeActual == -1) {

                // Se muestra un mensaje indicando el problema.
                System.out.println("El grafo no está conectado.");

                // Se finaliza la ejecución del programa.
                return;
            }

            // Se marca como visitada la ciudad seleccionada.
                // A partir de este momento pertenece al árbol.

            // Ejemplo:
            // Si verticeActual = 2, se marca Nairobi como true.
            visitados[verticeActual] = true;

            // Este ciclo revisa las conexiones que tiene
            // el vértice que se acaba de incorporar al árbol.
            for (int j = 0; j < cantidadVertices; j++) {

                // Se comprueban tres condiciones:

                // grafo[verticeActual][j] > 0:
                // Verifica que exista una conexión entre las ciudades.

                // !visitados[j]:
                // Verifica que la ciudad de destino todavía no forme parte del árbol.

                // grafo[verticeActual][j] < pesos[j]:
                // Verifica si se encontró una conexión de menor peso.

                // Ejemplo:
                    // Berlín tenía una conexión con Tokio de peso 4.
                        // Cuando revisamos Nairobi encontramos peso 3.
                            // Como 3 < 4, actualizamos la conexión de Berlín.
                if (grafo[verticeActual][j] > 0 &&
                        !visitados[j] &&
                        grafo[verticeActual][j] < pesos[j]) {

                    // Se actualiza el menor peso conocido
                        // para conectar la ciudad de destino.
                    pesos[j] = grafo[verticeActual][j];

                    // Se guarda cuál ciudad permite realizar
                        // esta nueva conexión de menor peso.

                    // Ejemplo:
                        // padres[1] = 2 significa que Berlín
                            // se conectará mediante Nairobi.
                    padres[j] = verticeActual;
                }
            }
        }

        // Se crea una variable para sumar los pesos
            // de las aristas seleccionadas por Prim.
                // Inicialmente el peso total es 0.
        int pesoTotal = 0;

        // Se muestra el título del resultado en consola.
        System.out.println(
                "ÁRBOL DE EXPANSIÓN MÍNIMA - PRIM");

        // Este ciclo recorre los vértices desde la posición 1.

        // No comienza desde 0 porque Tokio es el vértice inicial
            // y no tiene una arista padre que debamos mostrar.
        for (int i = 1; i < cantidadVertices; i++) {

            // Se muestra la ciudad padre, la ciudad de destino
                // y el peso de la conexión seleccionada.

            // Ejemplo:
            // padres[1] = 2, por lo tanto ciudades[2] = Nairobi.
            // ciudades[1] = Berlín.
            // pesos[1] = 3.

            // Resultado: Nairobi - Berlín: 3.
            System.out.println(
                    ciudades[padres[i]] + " - " +
                            ciudades[i] + ": " + pesos[i]);

            // Se suma el peso de cada conexión seleccionada.
                // Los pesos del árbol son 3, 2, 6 y 5.
                    // Su suma es 16.
            pesoTotal += pesos[i];
        }

        // Se muestra la suma total de las aristas
            // que forman el árbol de expansión mínima.
        System.out.println(
                "Peso total del árbol: " + pesoTotal);
    }
}
