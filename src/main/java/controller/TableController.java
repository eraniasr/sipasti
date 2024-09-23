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

        //colNomor = new TableColumn<>("No.");
        colNomor.setCellValueFactory(c -> c.getValue().getNomor());

        //colUnitPengaju = new TableColumn<>("Unit Pengusul Ajuan");
        colUnitPengaju.setCellValueFactory(c -> c.getValue().getUnitPengaju());

        //colNomorSurat = new TableColumn<>("No. Surat Ajuan");
        colNomorSurat.setCellValueFactory(c -> c.getValue().getNomorSurat());

        //colPerihal = new TableColumn<>("Perihal Surat Ajuan");
        colPerihal.setCellValueFactory(c -> c.getValue().getPerihal());

        //colTanggal = new TableColumn<>("Tanggal diajukan");
        colTanggal.setCellValueFactory(c -> c.getValue().getTanggalPengajuan());

        //colJam = new TableColumn<>("Jam diajukan");
        colJam.setCellValueFactory(c -> c.getValue().getJamPengajuan());

        //colStatusDisposisi = new TableColumn<>("Status Disposisi");
        colStatusDisposisi.setCellValueFactory(c -> c.getValue().getStatusDisposisi());

        //colPelaksanaTugas = new TableColumn<>("Pelaksana Tugas");
        colPelaksanaTugas.setCellValueFactory(c -> c.getValue().getPelaksanaTugas());

        //colStatusPengajuan = new TableColumn<>("Status Surat Ajuan");
        colStatusPengajuan.setCellValueFactory(c -> c.getValue().getStatusPengajuan());

        //colPermasalahan = new TableColumn<>("Permasalahan");
        colPermasalahan.setCellValueFactory(c -> c.getValue().getPermasalahan());

        //colStatusTerkait = new TableColumn<>("Status Bagian Terkait");
        //colStatusTerkait.getColumns().addAll(colPelaksanaTugas, colStatusPengajuan, colPermasalahan);

        //colStatusPembayaran = new TableColumn<>("Status Pembayaran");
        colStatusPembayaran.setCellValueFactory(c -> c.getValue().getStatusPembayaran());

//        table.getColumns().add(colNomor);
//        table.getColumns().add(colUnitPengaju);
//        table.getColumns().add(colNomorSurat);
//        table.getColumns().add(colPerihal);
//        table.getColumns().add(colTanggal);
//        table.getColumns().add(colJam);
//        table.getColumns().add(colStatusDisposisi);
//        //table.getColumns().add(colStatusTerkait);
//        table.getColumns().add(colStatusPembayaran);

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