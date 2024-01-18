package org.vaadin.example;

import java.io.IOException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class Tab2View extends VerticalLayout {

    private TextField msCodeField;
    private TextField yearField;
    private TextField estCodeField;
    private NumberField estimateField;
    private NumberField seField;
    private NumberField lowerCIBField;
    private NumberField upperCIBField;
    private TextField flagField;
    private TextField idField;

    private Grid<Observation2> observationGrid;
    private Button button;

    public Tab2View() {
        observationGrid = new Grid<>(Observation2.class);
        button = new Button("Añadir Observacion");

        add(observationGrid, button);

        // Opcional: Si también deseas centrar los componentes verticalmente en el layout
        setAlignItems(Alignment.CENTER);
        refreshGrid();

        /* -------- Modificar o Eliminar Observacion Existente -------- */
        
        observationGrid.addItemDoubleClickListener(event -> {
            Observation2 observation = event.getItem();
            setupDialog(observation); // Llama a la función para preparar y mostrar el Dialog
        });

        /* -------- Aádir nueva observación -------- */

        button.addClickListener(event -> {
            setupNewDialog(); // Llama a la función para preparar y mostrar el Dialog
        });

    }

    /* -------------------------------------------------------------------------------------------------------------- */
    /* ------- Todas las Observaciones en Grid  --------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */

    private void refreshGrid() {
        ObservationsService observationService = new ObservationsService();
        try {
            observationGrid.setItems(observationService.fetchObservations());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    /* ---------- Dialog Nueva Observacion -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */

    public void setupNewDialog() {
        // Crear un nuevo diálogo
        Dialog dialog = new Dialog();
    
        // Usar FormLayout para los campos de entrada
        FormLayout formLayout = new FormLayout();
    
        // Crear campos de formulario
        msCodeField = new TextField("MS Code");
        yearField = new TextField("Year");
        estCodeField = new TextField("Est Code");
        estimateField = new NumberField("Estimate");
        seField = new NumberField("SE");
        lowerCIBField = new NumberField("Lower CIB");
        upperCIBField = new NumberField("Upper CIB");
        flagField = new TextField("Flag");
    
        // Añadir campos al formulario
        formLayout.add(msCodeField, yearField, estCodeField, estimateField, seField, lowerCIBField, upperCIBField, flagField);
    
        // Botones
        Button saveButton = new Button("Guardar");
        Button cancelButton = new Button("Cancelar");
    
        /* ----------- Botón Guardar ----------- */
        saveButton.addClickListener(event -> { 
            // Crear una nueva instancia de Observation2 con los datos del formulario
            Observation2 observationToUpdate = new Observation2();
            //inicializamos
            observationToUpdate.setMscode(msCodeField.getValue());
            observationToUpdate.setYear(yearField.getValue());
            observationToUpdate.setEstCode(estCodeField.getValue());
            observationToUpdate.setEstimate(estimateField.getValue() != null ? estimateField.getValue().floatValue() : null);
            observationToUpdate.setSe(seField.getValue() != null ? seField.getValue().floatValue() : null);
            observationToUpdate.setLowerCIB(lowerCIBField.getValue() != null ? lowerCIBField.getValue().floatValue() : null);
            observationToUpdate.setUpperCIB(upperCIBField.getValue() != null ? upperCIBField.getValue().floatValue() : null);
            observationToUpdate.setFlag(flagField.getValue());

            // Enviar los datos al backend
            ObservationsService observationService = new ObservationsService();
            try {
                observationService.createObservation(observationToUpdate);
                refreshGrid();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            dialog.close();

         });
        
        /* ----------- Botón Cancelar ----------- */
        cancelButton.addClickListener(event -> dialog.close());
    
        // Añadir formLayout y botones al diálogo
        dialog.add(formLayout, saveButton, cancelButton);
    
        // Abrir el diálogo
        dialog.open();
    }
    
    /* -------------------------------------------------------------------------------------------------------------- */
    /* ---------- Dialog Modificar/Eliminar ------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */

    public void setupDialog(Observation2 obs) {
        // Crear un nuevo diálogo
        Dialog dialog = new Dialog();
    
        // Usar FormLayout para los campos de entrada
        FormLayout formLayout = new FormLayout();
    
        // Crear campos de formulario
        msCodeField = new TextField("MS Code");
        yearField = new TextField("Year");
        estCodeField = new TextField("Est Code");
        estimateField = new NumberField("Estimate");
        seField = new NumberField("SE");
        lowerCIBField = new NumberField("Lower CIB");
        upperCIBField = new NumberField("Upper CIB");
        flagField = new TextField("Flag");
        idField = new TextField("ID");

        // Establecer valores de la observación en los campos del formulario
        msCodeField.setValue(obs.getMscode());
        yearField.setValue(obs.getYear());
        estCodeField.setValue(obs.getEstCode());
        estimateField.setValue(obs.getEstimate() != null ? obs.getEstimate().doubleValue() : null);
        seField.setValue(obs.getSe() != null ? obs.getSe().doubleValue() : null);
        lowerCIBField.setValue(obs.getLowerCIB() != null ? obs.getLowerCIB().doubleValue() : null);
        upperCIBField.setValue(obs.getUpperCIB() != null ? obs.getUpperCIB().doubleValue() : null);
        flagField.setValue(obs.getFlag());
        idField.setValue(obs.get_id());

        idField.setReadOnly(true); // El ID no debe ser editable
    
        // Añadir campos al formulario
        formLayout.add(msCodeField, yearField, estCodeField, estimateField, seField, lowerCIBField, upperCIBField, flagField);
    
        // Botones
        Button saveButton = new Button("Guardar");
        Button cancelButton = new Button("Cancelar");
        Button deleteButton = new Button("Eliminar");
    
        /* ----------- Botón Guardar ----------- */
        saveButton.addClickListener(event -> { 
            // Crear una nueva instancia de Observation2 con los datos del formulario
            Observation2 observationToUpdate = new Observation2();
            //inicializamos
            observationToUpdate.setMscode(msCodeField.getValue());
            observationToUpdate.setYear(yearField.getValue());
            observationToUpdate.setEstCode(estCodeField.getValue());
            observationToUpdate.setEstimate(estimateField.getValue() != null ? estimateField.getValue().floatValue() : null);
            observationToUpdate.setSe(seField.getValue() != null ? seField.getValue().floatValue() : null);
            observationToUpdate.setLowerCIB(lowerCIBField.getValue() != null ? lowerCIBField.getValue().floatValue() : null);
            observationToUpdate.setUpperCIB(upperCIBField.getValue() != null ? upperCIBField.getValue().floatValue() : null);
            observationToUpdate.setFlag(flagField.getValue());

            // Enviar los datos al backend
            ObservationsService observationService = new ObservationsService();
            String observationId = idField.getValue();
            try {
                observationService.updateObservation(observationToUpdate, observationId);
                Notification.show("You updated the observation succesfully", 3000, Notification.Position.BOTTOM_START);
                refreshGrid();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Notification.show("There was a problem", 3000, Notification.Position.BOTTOM_START);
            }

            dialog.close();

         });
        
         /* ----------- Botón Cancelar ----------- */
        cancelButton.addClickListener(event -> dialog.close());
         
        /* ----------- Botón Eliminar ----------- */
        deleteButton.addClickListener(event -> {
            String observationId = idField.getValue();
            try {
                ObservationsService observationService = new ObservationsService();
                observationService.deleteObservation(observationId);
                refreshGrid();
                Notification.show("You deleted the observation succesfully", 3000, Notification.Position.BOTTOM_START);
                dialog.close();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                Notification.show("There was a problem", 3000, Notification.Position.BOTTOM_START);
            }
        });

        // Añadir formLayout y botones al diálogo
        dialog.add(formLayout, saveButton, cancelButton, deleteButton);
    
        // Abrir el diálogo
        dialog.open();
    }

}
