
package recorrido_dfs;

// Librerías para crear el grafo y realizar el recorrido DFS.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainDFS {

    // Método que permite realizar el recorrido por profundidad.
     // Recibe el vértice actual, el grafo y los vértices visitados.
    public static void dfs(
            String actual,
            Map<String, ArrayList<String>> grafo,
            Set<String> visitados) {

        // Se agrega el vértice actual al conjunto de visitados
        // para evitar que vuelva a recorrerse.
        visitados.add(actual);

        // Se muestra el nombre del vértice que se está visitando.
        System.out.println(actual);

        // Se buscan los vértices que tienen conexión
            // con el vértice actual dentro del grafo.
                // La variable vecino toma cada uno de ellos.
        for (String vecino : grafo.get(actual)) {

            // Se comprueba si el vecino todavía
                // no se encuentra en el conjunto de visitados.
            if (!visitados.contains(vecino)) {

                // Si el vecino no ha sido visitado,
                    // se llama nuevamente al método dfs.
                        // El vecino pasa a ser el vértice actual
                            // y se continúa recorriendo sus conexiones.
                dfs(vecino, grafo, visitados);
            }
        }

        // Cuando no existen más vecinos sin visitar,
            // el método termina y regresa al vértice anterior
                // para continuar con las conexiones pendientes.
    }

    public static void main(String[] args) {

        // Se crea el grafo utilizando una lista de adyacencia.
            // String representa el nombre de cada vértice.
                // ArrayList almacena los vértices con los que tiene conexión.
        Map<String, ArrayList<String>> grafo = new HashMap<>();

        // Se agrega el primer vértice (Tokio) y sus conexiones
            // con Berlín, Nairobi y Denver.
        grafo.put("Tokio", new ArrayList<>(
                Arrays.asList("Berlín", "Nairobi", "Denver")));

        // Se agrega el vértice Berlín y sus conexiones con Tokio y Nairobi.
        grafo.put("Berlín", new ArrayList<>(
                Arrays.asList("Tokio", "Nairobi")));

        // Se agrega el vértice Nairobi y sus conexiones
            // con Tokio, Berlín, Denver y Helsinki.
        grafo.put("Nairobi", new ArrayList<>(
                Arrays.asList("Tokio", "Berlín", "Denver", "Helsinki")));

        // Se agrega el vértice Denver y sus conexiones
            // con Tokio y Nairobi.
        grafo.put("Denver", new ArrayList<>(
                Arrays.asList("Tokio", "Nairobi")));

        // Se agrega el vértice Helsinki, que solamente
            // tiene conexión con Nairobi.
        grafo.put("Helsinki", new ArrayList<>(
                Arrays.asList("Nairobi")));

        // Se crea un conjunto para almacenar los vértices que ya fueron visitados durante el recorrido.
            // HashSet permite comprobar si un vértice ya se encuentra dentro de este conjunto.
        Set<String> visitados = new HashSet<>();

        // Se muestra el título del recorrido.
        System.out.println("Recorrido DFS:");

        // Se llama al método dfs para comenzar el recorrido.
            // Tokio será el primer vértice que se visite.
                // También se envía el grafo y el conjunto de visitados.
        dfs("Tokio", grafo, visitados);
    }
}

