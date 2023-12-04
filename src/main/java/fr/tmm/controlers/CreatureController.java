package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static fr.tmm.App.setScene;

public class CreatureController {

    @FXML
    public Text title;
    @FXML
    public ImageView creatureImg;
    @FXML
    public Text creatureType;
    @FXML
    public Text creatureName;
    @FXML
    public Text creatureSex;
    @FXML
    public Text creatureWeight;
    @FXML
    public Text creatureHeight;
    @FXML
    public Text creatureAge;
    @FXML
    public Text creatureSatiety;
    @FXML
    public Text creatureEnergy;
    @FXML
    public Text creatureHealth;
    @FXML
    public ProgressBar creatureSatietyBar;
    @FXML
    public ProgressBar creatureEnergyBar;
    @FXML
    public ProgressBar creatureHealthBar;

    private int indexEnclosure;

    private Creature creature;

    public void setIndexCreature(int indexEnclosure, int indexCreature){
        this.indexEnclosure = indexEnclosure;
        this.creature = Zoo.getInstance().getEnclosures().get(indexEnclosure).getCreaturesPresent().get(indexCreature);

        title.setText(creature.getName());
        creatureImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("assets/creatures/" + creature.getType().toLowerCase() + ".png")));
        creatureType.setText(creature.getType());
        creatureName.setText(creature.getName());
        creatureSex.setText(creature.getSex());
        creatureWeight.textProperty().set(creature.getWeight() + " kg");
        creatureHeight.textProperty().set(creature.getHeight() + " cm");
        creatureAge.textProperty().set(creature.getAge() + " ans");
        creatureSatiety.textProperty().set(creature.getSatiety() + " %");
        creatureEnergy.textProperty().set(creature.getEnergy() + " %");
        creatureHealth.textProperty().set(creature.getHealth() + " %");
        creatureSatietyBar.progressProperty().set(creature.getSatiety() / 100.0);
        creatureEnergyBar.progressProperty().set(creature.getEnergy() / 100.0);
        creatureHealthBar.progressProperty().set(creature.getHealth() / 100.0);
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosureController enclosureController = (EnclosureController) setScene("layout/enclosure.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexEnclosure(this.indexEnclosure);
    }
}
