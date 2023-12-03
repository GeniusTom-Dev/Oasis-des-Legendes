package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import static fr.tmm.App.setScene;

public class CreatureController {

    @FXML
    public Text title;

    private int indexEnclosure;

    private Creature creature;

    public void setIndexCreature(int indexEnclosure, int indexCreature){
        this.indexEnclosure = indexEnclosure;
        this.creature = Zoo.getInstance().getEnclosures().get(indexEnclosure).getCreaturesPresent().get(indexCreature);
        title.setText(creature.getName());
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosureController enclosureController = (EnclosureController) setScene("layout/enclosure.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexEnclosure(this.indexEnclosure);
    }
}
