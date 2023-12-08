package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    public Button actionCounter;

    private int indexEnclosure;

    private Creature creature;

    private Zoo zoo;

    public void setIndexCreature(int indexEnclosure, int indexCreature){
        this.indexEnclosure = indexEnclosure;
        this.zoo = Zoo.getInstance();
        this.creature = this.zoo.getEnclosures().get(indexEnclosure).getCreaturesPresent().get(indexCreature);

        title.setText(creature.getName());
        creatureImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("assets/creatures/" + creature.getType().toLowerCase() + ".png")));
        creatureType.textProperty().bind(creature.typeProperty());
        creatureName.textProperty().bind(creature.nameProperty());
        creatureSex.setText(creature.getSex().toString());
        creatureWeight.textProperty().bind(Bindings.createStringBinding(() -> String.format("%.1f kg", creature.getWeight()), creature.weightProperty()));
        creatureHeight.textProperty().bind(Bindings.createStringBinding(() -> String.format("%.1f m", creature.getHeight()), creature.heightProperty()));
        creatureAge.textProperty().bind(creature.ageProperty().asString().concat(" ans"));
        creatureSatiety.textProperty().bind(creature.satietyProperty().asString().concat(" %"));
        creatureEnergy.textProperty().bind(creature.energyProperty().asString().concat(" %"));
        creatureHealth.textProperty().bind(creature.healthProperty().asString().concat(" %"));
        creatureSatietyBar.progressProperty().bind(creature.satietyProperty().divide(100.0));
        creatureEnergyBar.progressProperty().bind(creature.energyProperty().divide(100.0));
        creatureHealthBar.progressProperty().bind(creature.healthProperty().divide(100.0));
        actionCounter.textProperty().bind(this.zoo.getZooMaster().actionsProperty().asString());
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosureController enclosureController = (EnclosureController) setScene("layout/enclosure.fxml", "Enclo");
        assert enclosureController != null;
        enclosureController.setIndexEnclosure(this.indexEnclosure);
    }


    public void moveCreature() {
        MoveCreatureController moveCreatureController = (MoveCreatureController) setScene("layout/moveCreature.fxml", "Envoyer vers...");
        assert moveCreatureController != null;
        moveCreatureController.sendData(this.zoo.getEnclosures().get(this.indexEnclosure), this.creature);
    }
}
