package org.vaadin.example;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.io.IOException;

@Route("datagrid")
public class Tab1View extends VerticalLayout {

    private ComboBox<String> comboBox;
    private ComboBox<String> comboBox2;
    private Grid<Observation> observationGrid;
    private Grid<Observation> observationGrid2;

    public Tab1View() {

        // Configure ComboBox
        comboBox = new ComboBox<>("Select MsCode");
        comboBox.setLabel("MsCode");

        comboBox2 = new ComboBox<>("Select MsCode");
        comboBox2.setLabel("MsCode");

        // Configure Grid
        observationGrid = new Grid<>(Observation.class);
        observationGrid2 = new Grid<>(Observation.class);

        // Add ComboBox to the view
        add(comboBox, observationGrid, comboBox2, observationGrid2);

        //Cargar MsCodes en Combobox
        loadMsCodes_GET();
        loadMsCodes_POST();

        // Cargar Grid Filtrado
        comboBox.addValueChangeListener(event -> {
            loadGrid_GET(event.getValue());
        });

        // Cargar Grid Filtrado
        comboBox2.addValueChangeListener(event -> {
            loadGrid_POST(event.getValue());
        });
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    /* ---------- Cragar MsCodes en Combobox ------------------------------------------------------------------------ */
    /* -------------------------------------------------------------------------------------------------------------- */

    /* ----- (GET) --------------------------------- */
    /* --------------------------------------------- */

    public void loadMsCodes_GET() {
        ObservationsService observationService = new ObservationsService();
        try {
            comboBox.setItems(observationService.fetchMscodes_GET());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ----- (POST) -------------------------------- */
    /* --------------------------------------------- */

    public void loadMsCodes_POST() {
        ObservationsService observationService = new ObservationsService();
        try {
            comboBox2.setItems(observationService.fetchMscodes_POST());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    



    /* --------------------------------------------------------------------------------------------------------------- */
    /* ------- Observaciones en Grid Filtradas ----------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------------- */

    /* ----- (GET) --------------------------------- */
    /* --------------------------------------------- */

    public void loadGrid_GET(String KeyMsCode) {
        ObservationsService observationService = new ObservationsService();
        try {
            observationGrid.setItems(observationService.fetchFilteredObservations_GET(KeyMsCode));
            Notification.show("Solicitud GET Enviada", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Solicitud GET no Enviada", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* ----- (POST) -------------------------------- */
    /* --------------------------------------------- */

    public void loadGrid_POST(String KeyMsCode) {
        ObservationsService observationService = new ObservationsService();
        try {
            observationGrid2.setItems(observationService.fetchFilteredObservations_POST(KeyMsCode));
            Notification.show("Solicitud POST Enviada", 3000, Notification.Position.BOTTOM_START);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Notification.show("Solicitud POST no Enviada", 3000, Notification.Position.BOTTOM_START);
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
}


