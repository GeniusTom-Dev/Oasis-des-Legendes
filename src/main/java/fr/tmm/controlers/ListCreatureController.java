package fr.tmm.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static fr.tmm.App.setScene;

public class ListCreatureController implements Initializable {
    @FXML
    public FlowPane listCreature;

    @FXML
    public void backButton(ActionEvent actionEvent) {
        HomeController homeController = (HomeController) setScene("layout/home.fxml", "Enclo");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String folderPath = "src/main/resources/assets/creatures";

        File folder = new File(folderPath);

        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            ImageView image = new ImageView();
            image.setImage(new Image(file.toURI().toString()));

            image.setFitHeight(150);
            image.setFitWidth(150);

            listCreature.getChildren().add(image);
        }
    }
}
