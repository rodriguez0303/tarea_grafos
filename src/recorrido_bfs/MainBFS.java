
package recorrido_bfs;

// Librerías que se necesita para crear el grafo,
// almacenar los vértices y realizar el recorrido BFS.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.List;

public class MainBFS {

    public static void main(String[] args) {

        // Se crea el grafo utilizando una lista de adyacencia.
        // String representa el nombre de cada vértice.
        // ArrayList almacena los vértices con los que tiene conexión.
        Map<String, ArrayList<String>> grafo = new HashMap<>();

        // Se agrega el primer nodo (Tokio) y sus conexiones
        // con Berlín, Nairobi y Denver.
        grafo.put("Tokio", new ArrayList<>(
                Arrays.asList("Berlín", "Nairobi", "Denver")));

        // Se agrega el nodo Berlín y sus conexiones
        // con Tokio y Nairobi.
        grafo.put("Berlín", new ArrayList<>(
                Arrays.asList("Tokio", "Nairobi")));

        // Se agrega el nodo Nairobi y sus conexiones
        // con Tokio, Berlín, Denver y Helsinki.
        grafo.put("Nairobi", new ArrayList<>(
                Arrays.asList("Tokio", "Berlín", "Denver", "Helsinki")));

        // Se agrega el nodo Denver y sus conexiones
        // con Tokio y Nairobi.
        grafo.put("Denver", new ArrayList<>(
                Arrays.asList("Tokio", "Nairobi")));

        // Se agrega el nodo Helsinki, que solamente
        // tiene conexión con Nairobi.
        grafo.put("Helsinki", new ArrayList<>(
                List.of("Nairobi")));

        // Se usa una cola para guardar los vértices
        // que están pendientes de recorrer.
        // La cola trabaja con FIFO, es decir, el primero en entrar será el primero en salir.
        Queue<String> cola = new LinkedList<>();

        // Creamos un conjunto para almacenar los vértices
        // que ya fueron visitados y evitar repetirlos.
        Set<String> visitados = new HashSet<>();

        // Se agrega el nodo Tokio a la cola porque sera el vértice donde comenzará el recorrido.
        cola.add("Tokio");

        // Se define el nodo Tokio como visitado para evitar
        // que vuelva a agregarse a la cola.
        visitados.add("Tokio");

        // Se agrega el título del recorrido.
        System.out.println("Recorrido BFS:");

        // El ciclo continúa mientras existan vértices pendientes dentro de la cola.
        while (!cola.isEmpty()) {

            // Se saca el primer vértice de la cola y se guarda en la variable actual.
            String actual = cola.poll();

            // Se muestra el nombre del vértice que se esta recorriendo.
            System.out.println(actual);

            // Se busca en el grafo los vértices que tienen conexión con el vértice actual.
                // La variable vecino toma cada uno de esos vértices, uno por uno.
            for (String vecino : grafo.get(actual)) {

                // Se comprueba si el vecino todavía no se encuentra entre los visitados.
                if (!visitados.contains(vecino)) {

                    // Si no ha sido visitado, se agrega al conjunto de vértices visitados.
                    visitados.add(vecino);

                    // Se agrega el vecino al final de la cola
                    // para recorrerlo cuando llegue su turno.
                    cola.add(vecino);
                }
            }
        }
    }
}
