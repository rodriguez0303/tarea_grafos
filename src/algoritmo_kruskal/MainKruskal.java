
package algoritmo_kruskal;

import java.util.Arrays;
import java.util.Comparator;

public class MainKruskal {

    // Esta clase representa una conexión entre dos ciudades.
        // Cada arista contiene:
            // origen: ciudad donde inicia la conexión.
                // destino: ciudad donde termina la conexión.
                    // peso: valor asignado a esa conexión.
    static class Arista {

        int origen;
        int destino;
        int peso;

        // Constructor para crear una arista.
        Arista(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Este método busca el representante del conjunto al que pertenece un vértice.

    // Ejemplo:
    // Si Tokio y Nairobi están conectados,
    // ambos tendrán el mismo nodo principal.

    // Se utiliza para determinar si dos ciudades ya pertenecen al mismo grupo.
    static int buscar(int[] padres, int vertice) {

        // Si el vértice es su propio padre,
            // significa que es el nodo principal del grupo.
        if (padres[vertice] == vertice) {
            return vertice;
        }

        // Si no es su propio padre, seguimos buscando
            // hasta encontrar el nodo principal del grupo.

        // Además, se actualiza el padre directamente para facilitar futuras búsquedas.
        padres[vertice] =
                buscar(padres, padres[vertice]);

        return padres[vertice];
    }

    // Este método permite unir dos conjuntos.

        // Ejemplo:
            // Si Tokio pertenece al grupo 0
                // y Nairobi pertenece al grupo 2,
                    // después de unirlos ambos estarán dentro del mismo grupo.
    static void unir(int[] padres,
                     int[] tamanos,
                     int grupo1,
                     int grupo2) {

        // Se busca el nodo principal de cada grupo.
        int raiz1 = buscar(padres, grupo1);
        int raiz2 = buscar(padres, grupo2);

        // Solo se unen si son grupos diferentes.
        if (raiz1 != raiz2) {

            // Se coloca el grupo más pequeño dentro del grupo más grande.

            // Esto ayuda a mantener una estructura más eficiente para las búsquedas.
            if (tamanos[raiz1] < tamanos[raiz2]) {

                padres[raiz1] = raiz2;

                tamanos[raiz2] += tamanos[raiz1];

            } else {

                padres[raiz2] = raiz1;

                tamanos[raiz1] += tamanos[raiz2];
            }
        }
    }

    public static void main(String[] args) {

        // Se crea un arreglo con los nombres de las cinco ciudades.

        // Cada ciudad representa un vértice.

        // Posiciones:
        // Tokio = 0
        // Berlín = 1
        // Nairobi = 2
        // Denver = 3
        // Helsinki = 4
        String[] ciudades = {
                "Tokio", "Berlín", "Nairobi",
                "Denver", "Helsinki"
        };

        // Se crea un arreglo con las seis aristas
        // del mismo grafo utilizado en Prim.

        // Cada arista contiene tres valores:
        // origen, destino y peso.

        // Ejemplo:
        // new Arista(0, 1, 4)
        // representa Tokio - Berlín con peso 4.
        Arista[] aristas = {
                new Arista(0, 1, 4),
                new Arista(0, 2, 2),
                new Arista(0, 3, 7),
                new Arista(1, 2, 3),
                new Arista(2, 3, 6),
                new Arista(2, 4, 5)
        };

        // Se obtiene la cantidad de vértices.
        // En este caso son cinco ciudades.
        int cantidadVertices = ciudades.length;

        // Se ordenan las aristas de menor a mayor peso.

        // Comparator.comparingInt compara el peso
        // de cada arista para establecer su orden.

        // Resultado de los pesos:
        // 2, 3, 4, 5, 6, 7.
        Arrays.sort(
                aristas,
                Comparator.comparingInt(a -> a.peso)
        );

        // Se crea un arreglo para registrar el nodo principal de cada conjunto.
        int[] padres = new int[cantidadVertices];

        // Se crea un arreglo para guardar la cantidad de vértices de cada grupo.
        int[] tamanos = new int[cantidadVertices];

        // Inicialmente cada ciudad pertenece a su propio conjunto.

        // padres = [0, 1, 2, 3, 4]
        // tamanos = [1, 1, 1, 1, 1]
        for (int i = 0; i < cantidadVertices; i++) {

            padres[i] = i;

            tamanos[i] = 1;
        }

        // Se crea un contador para registrar cuántas aristas fueron seleccionadas.
        int aristasSeleccionadas = 0;

        // Se crea una variable para acumular el peso de las conexiones seleccionadas.
        int pesoTotal = 0;

        // Se muestra el título en consola.
        System.out.println(
                "ÁRBOL DE EXPANSIÓN MÍNIMA - KRUSKAL"
        );

        // Este ciclo recorre las aristas ordenadas de menor a mayor peso.
        for (Arista arista : aristas) {

            // Se busca el representante del conjunto al que pertenece la ciudad de origen.
            int grupoOrigen =
                    buscar(padres, arista.origen);

            // Se busca el nodo principal del conjunto al que pertenece la ciudad de destino.
            int grupoDestino =
                    buscar(padres, arista.destino);

            // Si los nodos principales son diferentes,
                // significa que las ciudades todavía
                    // no pertenecen al mismo conjunto.

            // Por lo tanto, se puede incorporar la arista sin formar un ciclo.
            if (grupoOrigen != grupoDestino) {

                // Se muestra la conexión seleccionada.
                System.out.println(
                        ciudades[arista.origen] + " - " +
                                ciudades[arista.destino] + ": " +
                                arista.peso
                );

                // Se unen los conjuntos de las dos ciudades conectadas.
                unir(padres, tamanos,
                        grupoOrigen, grupoDestino);

                // Se suma el peso de la arista.
                pesoTotal += arista.peso;

                // Se incrementa la cantidad de aristas seleccionadas.
                aristasSeleccionadas++;

                // Un árbol con V vértices necesita
                // exactamente V - 1 aristas.

                // Como tenemos cinco ciudades, se necesitan cuatro aristas.
                if (aristasSeleccionadas ==
                        cantidadVertices - 1) {

                    // Se detiene el ciclo porque el árbol ya está completo.
                    break;
                }

            } else {

                // Si ambos vértices pertenecen
                // al mismo conjunto, no se incorpora esta arista porque formaría un ciclo.

                // Por eso no se selecciona.
                System.out.println(
                        "Descartada: " +
                                ciudades[arista.origen] + " - " +
                                ciudades[arista.destino] +
                                " (formaría un ciclo)"
                );
            }
        }

        // Se verifica que se hayan seleccionado
        // las V - 1 aristas necesarias.

        // Si no se alcanza esa cantidad, el grafo no está conectado.
        if (aristasSeleccionadas !=
                cantidadVertices - 1) {

            System.out.println(
                    "El grafo no está conectado."
            );

            return;
        }

        // Se muestra el peso total del árbol.
        System.out.println(
                "Peso total del árbol: " + pesoTotal
        );
    }
}
