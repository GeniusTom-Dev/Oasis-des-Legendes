package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static fr.tmm.App.setScene;

public class CreatureController {

    @FXML
    public Text title;
    @FXML
    public ImageView creatureImg;

    private int indexEnclosure;

    private Creature creature;

    public void setIndexCreature(int indexEnclosure, int indexCreature){
        this.indexEnclosure = indexEnclosure;
        this.creature = Zoo.getInstance().getEnclosures().get(indexEnclosure).getCreaturesPresent().get(indexCreature);

        title.setText(creature.getName());
        creatureImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("assets/creatures/" + creature.getType().toLowerCase() + ".png")));
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosureController enclosureController = (EnclosureController) setScene("layout/enclosure.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexEnclosure(this.indexEnclosure);
    }
}
