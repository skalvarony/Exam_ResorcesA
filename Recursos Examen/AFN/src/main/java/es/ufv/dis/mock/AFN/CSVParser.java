package es.ufv.dis.mock.AFN;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVParser {
    private String path;

    public CSVParser(String path) {
        this.path = path;
    }

    public List<Observation2> parse() {
        List<Observation2> dataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(this.path))) {
            List<String[]> allRows = reader.readAll();

            // Asegurándonos de que hay filas para procesar
            if (allRows.size() <= 1) {
                System.err.println("The CSV file is empty or it only contains a header row.");
                return dataList;
            }

            // Asumiendo que la primera fila es el encabezado
            String[] headers = allRows.get(0);

            // Convirtiendo los nombres de las columnas a minúsculas y eliminando espacios adicionales
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim().toLowerCase();
            }

            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                Observation2 observation = new Observation2();

                for (int j = 0; j < row.length; j++) {
                    String value = row[j].trim();
                    switch (headers[j]) {
                        case "mscode":
                            observation.setMscode(value);
                            break;
                        case "year":
                                observation.setYear(value);
                            break;
                        case "estcode":
                            observation.setEstCode(value);
                            break;
                        case "estimate":
                            if (!value.isEmpty()) {
                                observation.setEstimate(Float.parseFloat(value));
                            }
                            break;
                        case "se":
                            if (!value.isEmpty()) {
                                observation.setSe(Float.parseFloat(value));
                            }
                            break;
                        case "lowercib":
                            if (!value.isEmpty()) {
                                observation.setLowerCIB(Float.parseFloat(value));
                            }
                            break;
                        case "uppercib":
                            if (!value.isEmpty()) {
                                observation.setUpperCIB(Float.parseFloat(value));
                            }
                            break;
                        case "flag":
                            observation.setFlag(value);
                            break;
                        default:
                            System.err.println("Column not recognized: " + headers[j]);
                    }
                }

                dataList.add(observation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
