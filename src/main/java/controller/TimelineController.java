package controller;

import backend.SheetsQuickstart;
import backend.Surat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    @FXML
    private Label date1, date2, date3, date4, noSurat, perihal;

    @FXML
    private Button backButton;

    static String[] dates;
    static String perihalStr, noSuratStr, dekanStr, wadekStr, kabagStr, jftStr;

    protected static void showTimeline(Surat surat, MouseEvent event) throws IOException {
        //dates = surat.createTimeline();
        perihalStr = surat.getPerihal().get();
        noSuratStr = surat.getNomorSurat().get();
        dekanStr = surat.getDekan();
        wadekStr = surat.getWadek();
        kabagStr = surat.getKabag();
        jftStr = surat.getJft();
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
        date1.setText(dekanStr);
        date2.setText(wadekStr);
        date3.setText(kabagStr);
        date4.setText(jftStr);
    }
}