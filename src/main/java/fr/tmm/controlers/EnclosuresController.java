package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static fr.tmm.App.setScene;

public class EnclosuresController implements Initializable {
    @FXML
    public HBox enclosuresLine1;
    @FXML
    public HBox enclosuresLine2;

    public void openEnclosure(ActionEvent event) {
        Node source = (Node) event.getSource();
        String id = source.getId().replace("enclosure", "");
        EnclosureController enclosureController = (EnclosureController) setScene("layout/enclosure.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexEnclosure(Integer.parseInt(id));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Zoo zoo = Zoo.getInstance();
        ArrayList<Enclosure> enclosures = zoo.getEnclosures();
        for (int i = 0; i < enclosures.size(); i++) {
            Button button = new Button(enclosures.get(i).getName());
            button.setId("enclosure" + i);
            button.setOnAction(this::openEnclosure);

            if(enclosuresLine1.getChildren().size() < 5) {
                enclosuresLine1.getChildren().add(button);
            } else {
                enclosuresLine2.getChildren().add(button);
            }
        }
    }
}
