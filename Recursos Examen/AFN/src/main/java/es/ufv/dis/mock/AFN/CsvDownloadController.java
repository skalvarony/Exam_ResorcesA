package es.ufv.dis.mock.AFN;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@RestController
public class CsvDownloadController {

    private static String projectRoot = System.getProperty("user.dir");
    private static final String JSON_FILE_PATH2 = projectRoot + "/backend/Datos/Original.json";

    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCSV() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(out)) {
            
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

            StatefulBeanToCsv<Observation2> beanToCsv = new StatefulBeanToCsvBuilder<Observation2>(writer).build();
            beanToCsv.write(observations);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=datos.csv");
            headers.setContentType(MediaType.parseMediaType("text/csv"));

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(out.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
