package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Locale;

import static fr.tmm.App.setScene;

public class EnclosureController {
    @FXML
    public Text title;

    @FXML
    public VBox listCreature;
    @FXML
    public Text enclosureName;
    @FXML
    public Text enclosureSurface;
    @FXML
    public Text enclosureCapacity;
    @FXML
    public Text enclosureCountCreature;
    @FXML
    public Text enclosureClean;

    private int indexEnclosure;

    private Enclosure enclosure;

    private Zoo zoo = Zoo.getInstance();;

    private void showCreature(ActionEvent event) {
        Node source = (Node) event.getSource();
        String id = source.getId().replace("creature", "");
        CreatureController enclosureController = (CreatureController) setScene("layout/creature.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexCreature(this.indexEnclosure, Integer.parseInt(id));
    }

    public void setIndexEnclosure(int indexEnclosure) {
        this.indexEnclosure = indexEnclosure;
        this.enclosure = this.zoo.getEnclosures().get(indexEnclosure);
        ArrayList<Creature> creatures = this.enclosure.getCreaturesPresent();
        title.setText(this.enclosure.getName());

        enclosureName.setText(this.enclosure.getName());
        enclosureSurface.setText(String.valueOf(this.enclosure.getSurfaceArea()));
        enclosureCapacity.setText(String.valueOf(this.enclosure.getMaxCapacity()));
        enclosureCountCreature.setText(String.valueOf(creatures.size()));
        enclosureClean.textProperty().bind(this.enclosure.cleanProperty());


        for (int i = 0; i < creatures.size(); i++) {
            String buttonString = creatures.get(i).getName() + " | " + creatures.get(i).getSex().toString().toUpperCase() + " | " + creatures.get(i).getType();
            Button button = new Button(buttonString);
            button.setId("creature" + i);
            button.setOnAction(this::showCreature);
            button.getStyleClass().add("buttonList");
            listCreature.getChildren().add(button);
        }
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosuresController enclosuresController = (EnclosuresController) setScene("layout/enclosures.fxml", "Enclo");
    }

    public void cleanEnclosure() {
        this.zoo.getZooMaster().cleanEnclosure(this.enclosure);
    }

    public void feedCreature() {
        this.zoo.getZooMaster().feedEnclosure(this.enclosure);
    }
}
