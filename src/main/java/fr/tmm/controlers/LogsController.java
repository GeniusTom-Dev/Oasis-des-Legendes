package fr.tmm.controlers;

import javafx.event.ActionEvent;

import static fr.tmm.App.setScene;

public class LogsController {
    public void backButton(ActionEvent actionEvent) {
        HomeController homeController = (HomeController) setScene("layout/home.fxml", "Enclo");
    }
}
