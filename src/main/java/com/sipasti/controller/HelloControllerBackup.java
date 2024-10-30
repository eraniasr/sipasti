package com.sipasti.controller;

import com.sipasti.backend.SheetsQuickstart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class HelloControllerBackup {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label prodi;
    @FXML
    private Label nomor;

    @FXML
    protected void loginButtonClick(ActionEvent event) throws IOException, GeneralSecurityException {
        System.out.println("click!");
        root = FXMLLoader.load(getClass().getResource("/fxml/load.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

//        Service<String> service = new Service<>() {
//            @Override
//            protected Task<String> createTask() {
//                return new Task<>() {
//                    @Override
//                    protected String call() throws Exception {
//                        Thread.sleep(1000);
//                        stage.show();
////                        updateProgress(0, 5);
////                        for (int i = 1; i <= 5; i++) {
////                            Thread.sleep(1000);
////                            updateProgress(i, 5);
////                        }
//                        return "Complete!";
//                    }
//                };
//            }
//        };
//
//        service.setOnSucceeded(event1 -> {
//            SheetsQuickstart sheet = new SheetsQuickstart();
//            List<List<Object>> val;
//            try {
//                val = sheet.getSheetsData();
//            } catch (IOException | GeneralSecurityException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            prodi.setText((String)val.get(0).get(0));
//            nomor.setText((String)val.get(0).get(1));
//            System.out.println("selesai");
//        });
//        service.start();


//        stage.addEventHandler(WindowEvent.ANY, new EventHandler<>(){
//            @Override
//            public void handle(WindowEvent event) {
//                System.out.println(event.getEventType());
//            }
//        });
//
//        System.out.println("try");
//        SheetsQuickstart sheet = new SheetsQuickstart();
//        List<List<Object>> val = sheet.getSheetsData();
//        System.out.println((String) val.get(0).get(1));

//        TableColumn<List<Object>, Object> jenis = new TableColumn<>((String) val.get(0).get(5));
//        TableColumn<List<Object>, Object> asal = new TableColumn<>((String) val.get(0).get(6));
//        TableColumn<List<Object>, Object> tgl = new TableColumn<>((String) val.get(0).get(7));
//        TableColumn<List<Object>, Object> nomor = new TableColumn<>((String) val.get(0).get(8));
//        TableColumn<List<Object>, Object> kepada = new TableColumn<>((String) val.get(0).get(9));
//        TableColumn<List<Object>, Object> perihal = new TableColumn<>((String) val.get(0).get(10));
//        TableColumn<List<Object>, Object> status = new TableColumn<>((String) val.get(0).get(41));


//        for (int i = 0; i < val.get(0).size(); i++) {
//            TableColumn<List<Object>, Object> col = new TableColumn<>((String) val.get(0).get(i));
//
//            final int colNo = i;
//            col.setCellValueFactory( param -> new SimpleObjectProperty<>(param.getValue().get(colNo)) );
//            table.getColumns().add(col);
//
////            col.setCellFactory( column -> {
////                return new TableCell<List<Object>, Object>(){
////                    @Override
////                    protected void updateItem(String item, boolean empty) {
////                        super.updateItem(item, empty);
////                        if (item != null && !empty) {
////                            int rowNum = getIndex();
////                            setText("["+rowNum+","+ colNo +"] " + item);
////                        }
////                    }
////                };
////            });
//        }

        stage.show();
        SheetsQuickstart sheet = new SheetsQuickstart();
        List<List<Object>> val = sheet.getSheetsData();

        root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        prodi.setText((String)val.get(0).get(0));
        nomor.setText((String)val.get(0).get(1));
        stage.show();
    }
//
//    @FXML
//    protected void

}