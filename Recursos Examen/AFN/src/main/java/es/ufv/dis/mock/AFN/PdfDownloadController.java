package es.ufv.dis.mock.AFN;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfDownloadController {

    private static String projectRoot = System.getProperty("user.dir");
    private static final String JSON_FILE_PATH2 = projectRoot + "/backend/Datos/Original.json";

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPDF() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

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

            for (Observation2 obs : observations) {
                String info = String.format("mscode: %s, year: %s, estCode: %s, estimate: %f, se: %f, lowerCIB: %f, upperCIB: %f, flag: %s, _id: %s",
                                            obs.getMscode(), obs.getYear(), obs.getEstCode(), obs.getEstimate(),
                                            obs.getSe(), obs.getLowerCIB(), obs.getUpperCIB(), obs.getFlag(), obs.get_id());
                document.add(new Paragraph(info));
            }

            document.close();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=datos.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(byteArrayOutputStream.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
