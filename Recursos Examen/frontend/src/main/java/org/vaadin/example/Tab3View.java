package org.vaadin.example;

import java.io.IOException;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Tab3View extends VerticalLayout {

    public Tab3View() {
        // Crear un botón
        Button button = new Button("Convertir CSV a JSON, GET");
        Button button4 = new Button("Convertir CSV a JSON, POST");
        Button button2 = new Button("Exportar CSV, GET");
        Button button5 = new Button("Exportar CSV, POST");
        Button button3 = new Button("Exportar PDF, GET");
        Button button6 = new Button("Exportar PDF, POST");

        // Añadir el botón al layout
        add(button, button4, button2, button5, button3, button6);

        /* ------------------------------------ */
        /* ------------------------------------ */

        // Trabformar CSV GET
        button.addClickListener(event -> {
            tranformCSV_GET();
        });

        // Trabformar CSV POST
        button4.addClickListener(event -> {
            tranformCSV_POST();
        });

        /* ------------------------------------ */
        /* ------------------------------------ */

        // Exportar CSV GET
        button2.addClickListener(event -> {
            ExportCSV_GET();
        });

        // Exportar CSV POST
        button5.addClickListener(event -> {
            ExportCSV_POST();
        });

        /* ------------------------------------ */
        /* ------------------------------------ */

        // Exportar PDF GET
        button3.addClickListener(event -> {
            ExportPDF_GET();
        });

        // Exportar PDF POST
        button6.addClickListener(event -> {
            ExportPDF_POST();
        });

        /* ------------------------------------ */
        /* ------------------------------------ */

        // Generar botones exportar
        DownloadViewCSV(); // Exportar CSV
        DownloadViewPDF(); // Exportar PDF

    }

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------ Tranformar CSV/JSON ------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ----- (GET) --------------------------------- */
    /* --------------------------------------------- */

    private void tranformCSV_GET() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.tranformCSVtoJSON_GET();
            Notification.show("Transformacion Completada GET", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Transformacion GET", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* ----- (POST) -------------------------------- */
    /* --------------------------------------------- */

    private void tranformCSV_POST() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.tranformCSVtoJSON_POST();
            Notification.show("Transformacion Completada POST", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Transformacion POST", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    
    

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------ Exportar a CSV ------------------------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ----- (GET) --------------------------------- */
    /* --------------------------------------------- */

    private void ExportCSV_GET() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.exportCSV_GET();
            Notification.show("Exportacion CSV Completada GET", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Exportacion CSV GET", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* ----- (POST) -------------------------------- */
    /* --------------------------------------------- */

    private void ExportCSV_POST() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.exportCSV_POST();
            Notification.show("Exportacion CSV Completada POST", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Exportacion CSV POST", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    
    
    

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------ Exportar a PDF ------------------------------------------------------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* ----- (GET) --------------------------------- */
    /* --------------------------------------------- */

    private void ExportPDF_GET() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.exportPDF_GET();
            Notification.show("Exportacion PDF Completada, GET", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Exportacion PDF, GET", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* ----- (POST) -------------------------------- */
    /* --------------------------------------------- */

    private void ExportPDF_POST() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationService.exportPDF_POST();
            Notification.show("Exportacion PDF Completada, POST", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Error en la Exportacion PDF, POST", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    

    
    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ------ Downloads ----------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    /* --------------------------------------------- */
    /* ------ CSV ---------------------------------- */
    /* --------------------------------------------- */

    public void DownloadViewCSV() {
        Button downloadButton = new Button("Descargar CSV");
    
        // Agregar un listener al botón para abrir la URL de descarga
        downloadButton.addClickListener(event -> 
            UI.getCurrent().getPage().executeJs("window.location.href = $0", "http://localhost:8080/download-csv")
        );
    
        // Añadir el botón a la vista
        add(downloadButton);
    }

    /* --------------------------------------------- */
    /* ------ PDF ---------------------------------- */
    /* --------------------------------------------- */

    public void DownloadViewPDF() {
        Button downloadButton = new Button("Descargar PDF");
    
        // Agregar un listener al botón para abrir la URL de descarga
        downloadButton.addClickListener(event -> 
            UI.getCurrent().getPage().executeJs("window.location.href = $0", "http://localhost:8080/download-pdf")
        );
    
        // Añadir el botón a la vista
        add(downloadButton);
    }

    /* -------------------------------------------------------------------------------------------------------------- */
}

