package fr.tmm.controlers;

import fr.tmm.modele.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.tmm.App.setScene;

public class LogsController implements Initializable {
    @FXML
    public VBox listMessageLogs;

    Log logs = Log.getInstance();

    @FXML
    public void backButton(ActionEvent actionEvent) {
        HomeController homeController = (HomeController) setScene("layout/home.fxml", "Enclo");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(String log : logs.getLogs()) {
            Text text = new Text(log);
            text.setStyle("-fx-font-size: 18");
            text.setFill(Paint.valueOf("#FFFFFF"));
            listMessageLogs.getChildren().add(text);
        }
    }
}
