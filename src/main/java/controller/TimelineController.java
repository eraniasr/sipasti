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
    static String perihalStr, noSuratStr;

    protected static void showTimeline(Surat surat, MouseEvent event) throws IOException {
        //dates = surat.createTimeline();
        perihalStr = surat.getPerihal().get();
        noSuratStr = surat.getNomorSurat().get();
        root = FXMLLoader.load(TimelineController.class.getResource("/fxml/timeline.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.show();
    }

    @FXML
    protected void backButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){
//        date1.setText(dates[0]);
//        date2.setText(dates[1]);
//        date3.setText(dates[2]);
//        date4.setText(dates[3]);
        noSurat.setText(noSuratStr);
        perihal.setText(perihalStr);
        date1.setText("tu");
        date2.setText("wa");
        date3.setText("ga");
        date4.setText("pat");
    }
}