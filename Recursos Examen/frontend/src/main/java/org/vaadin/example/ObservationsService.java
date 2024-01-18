package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class ObservationsService {

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ----------- Inicializacion de Variables ------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    private static final String BACKEND_URL = "http://localhost:8080";
    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();

    /* ---------------------------------------------------------------------------------------------------------------- */


    
    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- TRANSFORM CSV/JSON ------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ---------- (GET) -------------------- */
    /* ------------------------------------- */

    public void tranformCSVtoJSON_GET() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/tranformCSV"))
                .GET()
                .build();
    
        client.send(request, HttpResponse.BodyHandlers.ofString());
    
    }

    /* ---------- (POST) ------------------- */
    /* ------------------------------------- */

    public void tranformCSVtoJSON_POST() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/tranformCSV"))
                .POST(HttpRequest.BodyPublishers.noBody()) // Utilizando POST sin cuerpo
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* ---------------------------------------------------------------------------------------------------------------- */



    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- EXPORT CSV --------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ---------- (GET) -------------------- */
    /* ------------------------------------- */

    public void exportCSV_GET() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/exportCSV"))
                .GET()
                .build();
    
        client.send(request, HttpResponse.BodyHandlers.ofString());
    
    }

    /* ---------- (POST) ------------------- */
    /* ------------------------------------- */

    public void exportCSV_POST() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/exportCSV"))
                .POST(HttpRequest.BodyPublishers.noBody()) // Utilizando POST sin cuerpo
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* ---------------------------------------------------------------------------------------------------------------- */



    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- EXPORT PDF --------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ---------- (GET) -------------------- */
    /* ------------------------------------- */

    public void exportPDF_GET() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/exportPDF"))
                .GET()
                .build();
    
        client.send(request, HttpResponse.BodyHandlers.ofString());
    
    }

    /* ---------- (POST) ------------------- */
    /* ------------------------------------- */

    public void exportPDF_POST() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/exportPDF"))
                .POST(HttpRequest.BodyPublishers.noBody()) // Utilizando POST sin cuerpo
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* ---------------------------------------------------------------------------------------------------------------- */



    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- LEER TODAS LAS KEYS ------------------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ---------- (GET) -------------------- */
    /* ------------------------------------- */

    public List<String> fetchMscodes_GET() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/getMsCodes"))
                .GET()
                .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> observations = new Gson().fromJson(response.body(), listType);
    
        return observations;
    }

    /* ---------- (POST) ------------------- */
    /* ------------------------------------- */

    public List<String>  fetchMscodes_POST() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/getMsCodes"))
                .POST(HttpRequest.BodyPublishers.noBody()) // Utilizando POST sin cuerpo
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> observations = new Gson().fromJson(response.body(), listType);
    
        return observations;
    }

    /* ---------------------------------------------------------------------------------------------------------------- */

    

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- OBSERVACIONES FILTRADAS -------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ---------- (GET) -------------------- */
    /* ------------------------------------- */

    public List<Observation> fetchFilteredObservations_GET(String Mscode) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/loadgridfiltered?KeyMsCode=" + Mscode))
                .GET()
                .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
        Type listType = new TypeToken<List<Observation>>() {}.getType();
        List<Observation> observations = new Gson().fromJson(response.body(), listType);
    
        return observations;
    }

    /* ---------- (POST) ------------------- */
    /* ------------------------------------- */

    public List<Observation> fetchFilteredObservations_POST(String Mscode) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/loadgridfiltered"))
                .header("Content-Type", "text/plain") // Cambiado a text/plain
                .POST(HttpRequest.BodyPublishers.ofString(Mscode))
                .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        Type listType = new TypeToken<List<Observation>>() {}.getType();
        List<Observation> observations = new Gson().fromJson(response.body(), listType);
    
        return observations;
    }

    /* ---------------------------------------------------------------------------------------------------------------- */




    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- TAB 1 -------------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------ */
    /* ----------------- Leer todas las Observaciones (Observation) ----------------- */
    /* ------------------------------------------------------------------------------ */

    public List<Observation2> fetchObservations() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/loadgrid"))
                .GET()
                .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
        Type listType = new TypeToken<List<Observation2>>() {}.getType();
        List<Observation2> observations = new Gson().fromJson(response.body(), listType);
    
        return observations;
    }

    /* ---------------------------------------------------------------------------- */
    /* ------------------------ Actualizar una Observacion ------------------------ */
    /* ---------------------------------------------------------------------------- */

    public void updateObservation(Observation2 observation, String id) throws IOException, InterruptedException {
        String json = gson.toJson(observation);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/observations/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
    
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* ----------------------------------------------------------------------------- */
    /* ------------------------ Crear una nueva Observacion ------------------------ */
    /* ----------------------------------------------------------------------------- */

    public void createObservation(Observation2 observation) throws IOException, InterruptedException {
        String json = gson.toJson(observation);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/observations/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* -------------------------------------------------------------------------- */
    /* ------------------------ Eliminar una Observacion ------------------------ */
    /* -------------------------------------------------------------------------- */

    public void deleteObservation(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/observations/" + id))
                .DELETE()
                .build();
    
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /* ---------------------------------------------------------------------------------------------------------------- */
}
