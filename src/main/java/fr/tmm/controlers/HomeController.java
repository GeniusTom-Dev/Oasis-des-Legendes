package fr.tmm.controlers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import static fr.tmm.App.setScene;

/**
 * Classe HomeController : Contrôleur de la vue home.fxml.
 * Permet de gérer les interactions et les fonctionnalités de la vue Home.
 * @Version 1.0
 * @author Even Tom
 */
public class HomeController {

    @FXML
    private BorderPane borderPane;


    @FXML
    private void toEnclosure() {
        EnclosuresController enclosuresController = (EnclosuresController) setScene("layout/enclosures.fxml", "Enclos");
    }

}
