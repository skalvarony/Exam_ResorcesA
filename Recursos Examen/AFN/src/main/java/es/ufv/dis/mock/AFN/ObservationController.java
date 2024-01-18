package es.ufv.dis.mock.AFN;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ObservationController {

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ----------- DECLARACION VARIABLES ------------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    private static String projectRoot = System.getProperty("user.dir");
    private static final String JSON_FILE_PATH = projectRoot + "/AFN/Datos/MsCode.json";
    private static final String JSON_FILE_PATH2 = projectRoot + "/AFN/Datos/Original.json";
    private static final String CSV_FILE_PATH1 = projectRoot + "/AFN/Datos/cp-national-datafile-csv.csv";
    private static final String CSV_FILE_PATH2 = projectRoot + "/AFN/Datos/Export_csv.csv";
    private static final String PDF_FILE_PATH1 = projectRoot + "/AFN/Datos/Export_pdf.pdf";
    private static final String JSON_FILE_PATH3 = projectRoot + "/AFN/Datos/Originall.json";
  
    private static CSVParser csvParser;

    /* ---------------------------------------------------------------------------------------------------------------- */



    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- TRANSFORM CSV/JSON ------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ------ (GET) ------------------------------- */
    /* -------------------------------------------- */

    @GetMapping("/tranformCSV")
    public ResponseEntity<?> tranformCSV_GET() throws IOException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Leemos el archivo CSV
        csvParser = new CSVParser(CSV_FILE_PATH1);
        List<Observation2> observations = csvParser.parse();
        
        // Abrir el archivo JSON para escritura
        FileWriter writer = new FileWriter(JSON_FILE_PATH3);

        // Serializar la lista actualizada y escribirla en el archivo
        gson.toJson(observations, writer);

        // Cerrar el escritor de archivos
        writer.close();

        // Devolver la observación agregada y una respuesta OK
        return ResponseEntity.ok(observations);

    }

    /* ------- (POST) ----------------------------- */
    /* -------------------------------------------- */

    @PostMapping("/tranformCSV")
    public ResponseEntity<?> tranformCSV_POST() throws IOException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Leemos el archivo CSV
        csvParser = new CSVParser(CSV_FILE_PATH1);
        List<Observation2> observations = csvParser.parse();
        
        // Abrir el archivo JSON para escritura
        FileWriter writer = new FileWriter(JSON_FILE_PATH3);

        // Serializar la lista actualizada y escribirla en el archivo
        gson.toJson(observations, writer);

        // Cerrar el escritor de archivos
        writer.close();

        // Devolver la observación agregada y una respuesta OK
        return ResponseEntity.ok(observations);

    }

    /* ---------------------------------------------------------------------------------------------------------------- */





    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- EXPORT CSV --------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* -------- GET ------------------------------- */
    /* -------------------------------------------- */

    @GetMapping("/exportCSV")
    public ResponseEntity<?> exportCSV_GET() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        FileReader reader = new FileReader(JSON_FILE_PATH2);

        // Especificar el tipo del contenido del JSON
        Type listType = new TypeToken<List<Observation2>>(){}.getType();

        // Deserializar el contenido del archivo JSON a una List
        List<Observation2> allObservations = gson.fromJson(reader, listType);

        // Cerrar el lector de archivos
        reader.close();
        
        // Escribir las observaciones en un archivo CSV
        try (Writer writer = new FileWriter(CSV_FILE_PATH2)) {
            ColumnPositionMappingStrategy<Observation2> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Observation2.class);
            String[] columns = new String[]{"mscode", "year", "estCode", "estimate", "se", "lowerCIB", "upperCIB", "flag", "_id"}; // Ajusta según los campos de Observation2
            strategy.setColumnMapping(columns);

            StatefulBeanToCsv<Observation2> beanToCsv = new StatefulBeanToCsvBuilder<Observation2>(writer)
                    .withMappingStrategy(strategy)
                    .build();

            beanToCsv.write(allObservations);
        }

        // Devolver una respuesta HTTP
        return ResponseEntity.ok(allObservations);

    }

    /* ------- POST ------------------------------- */
    /* -------------------------------------------- */

    @PostMapping("/exportCSV")
    public ResponseEntity<?> exportCSV_POST() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        FileReader reader = new FileReader(JSON_FILE_PATH2);

        // Especificar el tipo del contenido del JSON
        Type listType = new TypeToken<List<Observation2>>(){}.getType();

        // Deserializar el contenido del archivo JSON a una List
        List<Observation2> allObservations = gson.fromJson(reader, listType);

        // Cerrar el lector de archivos
        reader.close();
        
        // Escribir las observaciones en un archivo CSV
        try (Writer writer = new FileWriter(CSV_FILE_PATH2)) {
            ColumnPositionMappingStrategy<Observation2> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Observation2.class);
            String[] columns = new String[]{"mscode", "year", "estCode", "estimate", "se", "lowerCIB", "upperCIB", "flag", "_id"}; // Ajusta según los campos de Observation2
            strategy.setColumnMapping(columns);

            StatefulBeanToCsv<Observation2> beanToCsv = new StatefulBeanToCsvBuilder<Observation2>(writer)
                    .withMappingStrategy(strategy)
                    .build();

            beanToCsv.write(allObservations);
        }

        // Devolver una respuesta HTTP
        return ResponseEntity.ok(allObservations);

    }

    /* ---------------------------------------------------------------------------------------------------------------- */




    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- EXPORT PDF --------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* -------- GET ------------------------------- */
    /* -------------------------------------------- */

    @GetMapping("/exportPDF")
    public ResponseEntity<?> exportPDF_GET() throws IOException, DocumentException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        try (FileReader reader = new FileReader(JSON_FILE_PATH2)) {
            // Especificar el tipo del contenido del JSON
            Type listType = new TypeToken<List<Observation2>>(){}.getType();

            // Deserializar el contenido del archivo JSON a una List
            List<Observation2> allObservations = gson.fromJson(reader, listType);

            // Crear un documento PDF y escribir las observaciones
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH1));
            document.open();
            
            for (Observation2 obs : allObservations) {
                String formattedText = String.format("mscode: %s, year: %s, estCode: %s, estimate: %f, se: %f, lowerCIB: %f, upperCIB: %f, flag: %s, _id: %s",
                                                     obs.getMscode(), obs.getYear(), obs.getEstCode(), obs.getEstimate(),
                                                     obs.getSe(), obs.getLowerCIB(), obs.getUpperCIB(), obs.getFlag(), obs.get_id());
                document.add(new Paragraph(formattedText));
            }

            document.close();

            // Devolver una respuesta HTTP indicando el éxito
            return ResponseEntity.ok("PDF exportado con éxito a: " + PDF_FILE_PATH1);
        }
    }

    /* ------- POST ------------------------------- */
    /* -------------------------------------------- */

    @PostMapping("/exportPDF")
    public ResponseEntity<?> exportPDF_POST() throws IOException, DocumentException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        try (FileReader reader = new FileReader(JSON_FILE_PATH2)) {
            // Especificar el tipo del contenido del JSON
            Type listType = new TypeToken<List<Observation2>>(){}.getType();

            // Deserializar el contenido del archivo JSON a una List
            List<Observation2> allObservations = gson.fromJson(reader, listType);

            // Crear un documento PDF y escribir las observaciones
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH1));
            document.open();
            
            for (Observation2 obs : allObservations) {
                String formattedText = String.format("mscode: %s, year: %s, estCode: %s, estimate: %f, se: %f, lowerCIB: %f, upperCIB: %f, flag: %s, _id: %s",
                                                     obs.getMscode(), obs.getYear(), obs.getEstCode(), obs.getEstimate(),
                                                     obs.getSe(), obs.getLowerCIB(), obs.getUpperCIB(), obs.getFlag(), obs.get_id());
                document.add(new Paragraph(formattedText));
            }

            document.close();

            // Devolver una respuesta HTTP indicando el éxito
            return ResponseEntity.ok("PDF exportado con éxito a: " + PDF_FILE_PATH1);
        }
    }

    /* ---------------------------------------------------------------------------------------------------------------- */




    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- LEER TODAS LAS KEYS ------------------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* -------- GET ------------------------------- */
    /* -------------------------------------------- */

    @GetMapping("/getMsCodes")
    public List<String> getMsCodes_GET() {
        try {
            // Crear una instancia de Gson
            Gson gson = new Gson();

            // Abrir el archivo JSON para lectura
            FileReader reader = new FileReader(JSON_FILE_PATH);

            // Especificar el tipo del contenido del JSON
            Type type = new TypeToken<Map<String, List<Observation>>>(){}.getType();

            // Deserializar el contenido del archivo JSON a un Map
            Map<String, List<Observation>> msCodes = gson.fromJson(reader, type);

            // Cerrar el lector de archivos
            reader.close();

            // Lista para almacenar los MsCodes
            List<String> msCodeKeys = new ArrayList<>();

            // Recorrer el Map y agregar cada clave (MsCode) a la lista
            for (String msCodeKey : msCodes.keySet()) {
                msCodeKeys.add(msCodeKey);
            }

            // Ordenar alfabéticamente la lista de MsCodes
            Collections.sort(msCodeKeys);

            // Devolver la lista de MsCodes
            return msCodeKeys;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ------- POST ------------------------------- */
    /* -------------------------------------------- */

    @PostMapping("/getMsCodes")
    public List<String> getMsCodes_POST() {
        try {
            // Crear una instancia de Gson
            Gson gson = new Gson();

            // Abrir el archivo JSON para lectura
            FileReader reader = new FileReader(JSON_FILE_PATH);

            // Especificar el tipo del contenido del JSON
            Type type = new TypeToken<Map<String, List<Observation>>>(){}.getType();

            // Deserializar el contenido del archivo JSON a un Map
            Map<String, List<Observation>> msCodes = gson.fromJson(reader, type);

            // Cerrar el lector de archivos
            reader.close();

            // Lista para almacenar los MsCodes
            List<String> msCodeKeys = new ArrayList<>();

            // Recorrer el Map y agregar cada clave (MsCode) a la lista
            for (String msCodeKey : msCodes.keySet()) {
                msCodeKeys.add(msCodeKey);
            }

            // Ordenar alfabéticamente la lista de MsCodes
            Collections.sort(msCodeKeys);

            // Devolver la lista de MsCodes
            return msCodeKeys;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ---------------------------------------------------------------------------------------------------------------- */




    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- OBSERVACIONES FILTRADAS -------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ------- GET -------------------------------- */
    /* -------------------------------------------- */

    @GetMapping("/loadgridfiltered")
    public List<Observation> getObservationsFiltered_GET(@RequestParam(name = "KeyMsCode") String keyMsCode) {
        try {
            // Crear una instancia de Gson
            Gson gson = new Gson();

            // Abrir el archivo JSON para lectura
            FileReader reader = new FileReader(JSON_FILE_PATH);

            // Especificar el tipo del contenido del JSON
            Type type = new TypeToken<Map<String, List<Observation>>>(){}.getType();

            // Deserializar el contenido del archivo JSON a un Map
            Map<String, List<Observation>> msCodes = gson.fromJson(reader, type);

            // Cerrar el lector de archivos
            reader.close();

            // Devolver las observaciones asociadas con el MsCode especificado
            return msCodes.getOrDefault(keyMsCode, null);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ------- POST ------------------------------- */
    /* -------------------------------------------- */

    @PostMapping("/loadgridfiltered")
    public List<Observation> getObservationsFiltered_POST(@RequestBody String keyMsCode) throws IOException {
        try {
            // Crear una instancia de Gson
            Gson gson = new Gson();

            // Abrir el archivo JSON para lectura
            FileReader reader = new FileReader(JSON_FILE_PATH);

            // Especificar el tipo del contenido del JSON
            Type type = new TypeToken<Map<String, List<Observation>>>(){}.getType();

            // Deserializar el contenido del archivo JSON a un Map
            Map<String, List<Observation>> msCodes = gson.fromJson(reader, type);

            // Cerrar el lector de archivos
            reader.close();

            // Devolver las observaciones asociadas con el MsCode especificado
            return msCodes.getOrDefault(keyMsCode, null);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ---------------------------------------------------------------------------------------------------------------- */




    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------- TAB 1 -------------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------- */
    /* ------- LEER TODAS LAS OBSERVACIONES ------- */
    /* -------------------------------------------- */

    @GetMapping("/loadgrid")
    public List<Observation2> getObservations() {
        try {
            // Crear una instancia de Gson
            Gson gson = new Gson();

            // Abrir el archivo JSON para lectura
            FileReader reader = new FileReader(JSON_FILE_PATH2);

            // Especificar el tipo del contenido del JSON
            Type listType = new TypeToken<List<Observation2>>(){}.getType();

            // Deserializar el contenido del archivo JSON a una List
            List<Observation2> allObservations = gson.fromJson(reader, listType);

            // Cerrar el lector de archivos
            reader.close();

            // Devolver todas las observaciones
            return allObservations;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* -------------------------------------------- */
    /* ------- AÑADIR UNA NUEVA OBSERVACION ------- */
    /* -------------------------------------------- */

    @PostMapping("/observations/add")
    public ResponseEntity<?> addObservation(@RequestBody Observation2 observation) throws IOException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        FileReader reader = new FileReader(JSON_FILE_PATH2);

        // Especificar el tipo del contenido del JSON
        Type listType = new TypeToken<List<Observation2>>(){}.getType();

        // Deserializar el contenido del archivo JSON a una List
        List<Observation2> observations = gson.fromJson(reader, listType);

        // Cerrar el lector de archivos
        reader.close();

        // Añadir la nueva observación a la lista
        observations.add(observation);

        // Abrir el archivo JSON para escritura
        FileWriter writer = new FileWriter(JSON_FILE_PATH2);

        // Serializar la lista actualizada y escribirla en el archivo
        gson.toJson(observations, writer);

        // Cerrar el escritor de archivos
        writer.close();

        // Devolver la observación agregada y una respuesta OK
        return ResponseEntity.ok(observation);
    }

    /* ------------------------------------------------------- */
    /* ------- ACTUALIZAR OBSERVACION EN FUNCION DE ID ------- */
    /* ------------------------------------------------------- */

    @PutMapping("/observations/{id}")
    public ResponseEntity<?> updateObservation(@PathVariable String id, @RequestBody Observation2 updatedObservation) throws IOException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        FileReader reader = new FileReader(JSON_FILE_PATH2);

        // Especificar el tipo del contenido del JSON
        Type listType = new TypeToken<List<Observation2>>(){}.getType();

        // Deserializar el contenido del archivo JSON a una List
        List<Observation2> observations = gson.fromJson(reader, listType);

        // Cerrar el lector de archivos
        reader.close();

        // Buscar y actualizar la observación correspondiente
        boolean found = false;
        for (int i = 0; i < observations.size(); i++) {
            if (observations.get(i).get_id().equals(id)) {
                // Actualizar la observación
                updatedObservation.set_id(id); // Asegurarse de que el ID se mantiene
                observations.set(i, updatedObservation);
                found = true;
                break;
            }
        }

        if (!found) {
            return ResponseEntity.notFound().build();
        }

        // Abrir el archivo JSON para escritura
        FileWriter writer = new FileWriter(JSON_FILE_PATH2);

        // Serializar la lista actualizada y escribirla en el archivo
        gson.toJson(observations, writer);

        // Cerrar el escritor de archivos
        writer.close();

        return ResponseEntity.ok().build();
    }

    /* ----------------------------------------------------- */
    /* ------- ELIMINAR OBSERVACION EN FUNCION DE ID ------- */
    /* ----------------------------------------------------- */

    @DeleteMapping("/observations/{id}")
    public ResponseEntity<?> deleteObservation(@PathVariable String id) throws IOException {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Abrir el archivo JSON para lectura
        FileReader reader = new FileReader(JSON_FILE_PATH2);

        // Especificar el tipo del contenido del JSON
        Type listType = new TypeToken<List<Observation2>>(){}.getType();

        // Deserializar el contenido del archivo JSON a una List
        List<Observation2> observations = gson.fromJson(reader, listType);

        // Cerrar el lector de archivos
        reader.close();
    
        // Searches for and deletes the remark with the corresponding ID
        boolean found = false;
        for (int i = 0; i < observations.size(); i++) {
            if (observations.get(i).get_id().equals(id)) {
                observations.remove(i);
                found = true;
                break;
            }
        }
    
        if (!found) {
            return ResponseEntity.notFound().build();
        }
    
        // Abrir el archivo JSON para escritura
        FileWriter writer = new FileWriter(JSON_FILE_PATH2);

        // Serializar la lista actualizada y escribirla en el archivo
        gson.toJson(observations, writer);

        // Cerrar el escritor de archivos
        writer.close();
        
    
        return ResponseEntity.ok().build();
    }
     
    /* ---------------------------------------------------------------------------------------------------------------- */

}

