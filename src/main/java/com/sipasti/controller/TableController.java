package com.sipasti.controller;
import com.sipasti.backend.SheetsQuickstart;
import com.sipasti.backend.Surat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<Surat> table;

    @FXML
    private TableColumn<Surat, String> colNomor, colUnitPengaju, colNomorSurat, colPerihal, colTanggal, colJam, colPelaksanaTugas,
            colStatusDisposisi, colStatusPengajuan, colPermasalahan, colStatusPembayaran;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private TextField textField;

    private List<List<Object>> val;

    @FXML
    public void initialize (URL url, ResourceBundle rb){

        // Get data from Google Sheets
        try {
            SheetsQuickstart sheet = new SheetsQuickstart();
            val = sheet.getSheetsData();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        table.setFixedCellSize(35.0);
        table.setRowFactory(suratTableView -> {
            TableRow<Surat> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                try {
                    clickItem(mouseEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return row;
        });

        ObservableList<Surat> obList = FXCollections.observableArrayList();

        int counter = 1;
        for (List<Object> surat : val){

            // Clear empty lists (sheets ngambil data kosong karena ada satu column yang keisi "Pilih")
            if (surat.get(8).equals("")){
                val.subList(counter-1, val.size()).clear();
                break;
            }

            obList.add(new Surat(surat, counter));
            counter++;
        }

        colNomor.setCellValueFactory(c -> c.getValue().getNomor());
        colUnitPengaju.setCellValueFactory(c -> c.getValue().getUnitPengaju());
        colNomorSurat.setCellValueFactory(c -> c.getValue().getNomorSurat());
        colPerihal.setCellValueFactory(c -> c.getValue().getPerihal());
        colTanggal.setCellValueFactory(c -> c.getValue().getTanggalPengajuan());
        colJam.setCellValueFactory(c -> c.getValue().getJamPengajuan());
        colStatusDisposisi.setCellValueFactory(c -> c.getValue().getStatusDisposisi());
        colPelaksanaTugas.setCellValueFactory(c -> c.getValue().getPelaksanaTugas());
        colStatusPengajuan.setCellValueFactory(c -> c.getValue().getStatusPengajuan());
        colPermasalahan.setCellValueFactory(c -> c.getValue().getPermasalahan());
        colStatusPembayaran.setCellValueFactory(c -> c.getValue().getStatusPembayaran());


        FilteredList<Surat> flSurat = new FilteredList<>(obList, p -> true);
        table.setItems(flSurat);

        // Search box
        choiceBox.getItems().setAll("Perihal", "No. Surat");
        choiceBox.setValue("Perihal");

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
                    switch (choiceBox.getValue().toString())//Switch on choiceBox value
                    {
                        case "Perihal":
                            flSurat.setPredicate(p -> p.getPerihal().get().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                            break;
                        case "No. Surat":
                            flSurat.setPredicate(p -> p.getNomorSurat().get().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                            break;
                    }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });
    }

    // Detail data, nanti nampilin timeline
    @FXML
    protected void clickItem(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1)
        {
            TimelineController.showTimeline(table.getSelectionModel().getSelectedItem(), event);
        }
    }

}