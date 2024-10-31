package com.sipasti.controller;

import com.sipasti.backend.Surat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {

    private static Stage stage;
    private static Parent root;

    @FXML
    private Label date1, date2, date3, date4, date5,
            noSurat, perihal, prosesWadek, prosesKabag, prosesJftKeuangan, prosesJftPengadaan;

    @FXML
    private Button backButton;

    static String[] dates;
    static String perihalStr, noSuratStr;

    protected static void showTimeline(Surat surat, MouseEvent event) throws IOException {
        dates = surat.createTimeline();
        perihalStr = surat.getPerihal().get();
        noSuratStr = surat.getNomorSurat().get();
        root = FXMLLoader.load(TimelineController.class.getResource("/fxml/timeline.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    protected void backButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        noSurat.setText(noSuratStr);
        perihal.setText(perihalStr);
        date1.setText(dates[0]);
        date2.setText(dates[1]);
        date3.setText(dates[2]);
        date4.setText(dates[3]);
        date5.setText(dates[4]);
        prosesWadek.setText("Proses: " + dates[5]);
        prosesKabag.setText("Proses: " + dates[6]);
        prosesJftKeuangan.setText("Proses: " + dates[7]);
        prosesJftPengadaan.setText("Proses: " + dates[8]);
    }
}