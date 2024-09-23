package controller;
import backend.SheetsQuickstart;
import backend.Surat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
            colStatusTerkait, colStatusDisposisi, colStatusPengajuan, colPermasalahan, colStatusPembayaran;

    @FXML
    private TableColumn<Surat, String> colDekan, colWadek, colKabag, colJft;

    private List<List<Object>> val;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize (URL url, ResourceBundle rb){

        // Get data from Google Sheets
        try {
            SheetsQuickstart sheet = new SheetsQuickstart();
            val = sheet.getSheetsData();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        //table = new TableView<>();
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

        table.setItems(obList);
    }

    // Detail data, nanti nampilin timeline
    @FXML
    protected void clickItem(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1)
        {
            System.out.println(table.getSelectionModel().getSelectedItem().getDekan());
            TimelineController.showTimeline(table.getSelectionModel().getSelectedItem(), event);
        }
    }

}