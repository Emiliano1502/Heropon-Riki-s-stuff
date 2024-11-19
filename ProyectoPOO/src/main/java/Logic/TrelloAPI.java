
package Logic;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloAPI {

    private static final String API_KEY = "";  // Tu clave de API
    private static final String TOKEN = "ATATT3xFfGF0EvBPZrPuuSojUhWwHBsyM0zPiF9KROlyqUxqths8TQy1WgMxIrR84MTNiis7zltllSenEpHxIm9bjoz8iA32xfo3htLZEAMQ9cclN89gGnSBOCqXe4JoL99VtT-muQtanqgkAmHmPKYf3fwGr18CueDSmEmt-UsTD9R1AjQZllM=B83E36AA";     // Tu token
    private static final String BOARD_ID = "TU_BOARD_ID"; // ID del tablero

    public static void main(String[] args) {
        try {
            // Construir la URL para obtener las listas de un tablero
            String url = String.format("https://api.trello.com/1/boards/%s/lists?key=%s&token=%s",
                    BOARD_ID, API_KEY, TOKEN);

            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear la solicitud HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            // Realizar la solicitud
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON
            if (response.statusCode() == 200) {
                JSONArray lists = new JSONArray(response.body());
                System.out.println("Listas en el tablero:");

                for (int i = 0; i < lists.length(); i++) {
                    JSONObject list = lists.getJSONObject(i);
                    String listId = list.getString("id");
                    String listName = list.getString("name");
                    System.out.println("Lista: " + listName);

                    // Obtener las tarjetas de cada lista
                    getCardsInList(client, listId);
                }
            } else {
                System.err.println("Error en la respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener las tarjetas de una lista
    private static void getCardsInList(HttpClient client, String listId) {
        try {
            String url = String.format("https://api.trello.com/1/lists/%s/cards?key=%s&token=%s",
                    listId, API_KEY, TOKEN);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray cards = new JSONArray(response.body());
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    System.out.println("\tTarjeta: " + card.getString("name"));
                }
            } else {
                System.err.println("Error obteniendo tarjetas: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
