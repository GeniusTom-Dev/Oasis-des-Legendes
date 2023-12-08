package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static fr.tmm.App.setScene;

public class MoveCreatureController {
    @FXML
    public FlowPane listEnclausure;
    private Zoo zoo = Zoo.getInstance();

    private Enclosure enclosure;

    private Creature creature;

    private ArrayList<Enclosure> listEnclosureAviable = new ArrayList<>();

    public void sendData(Enclosure enclosure, Creature creature){
        this.enclosure = enclosure;
        this.creature = creature;

        for(Enclosure enc : zoo.getEnclosures()){
            if(!Objects.equals(enc, this.enclosure)){
                if(Objects.equals(enc.getCreaturesPresent().get(0).getType(), this.creature.getType()) && enc.getMaxCapacity() < enc.getCreaturesPresent().size()){
                    listEnclosureAviable.add(enc);
                }
            }
        }

        this.showList();
    }

    private void showList(){
        for (int i = 0; i < listEnclosureAviable.size(); i++) {
            Button b = new Button();
            b.setText(listEnclosureAviable.get(i).getName());
            b.setId("button" + i);
            b.setOnAction(this::moveToEnclosure);
            listEnclausure.getChildren().add(b);
        }
    }

    private void moveToEnclosure(ActionEvent actionEvent) {
        String id = ((Button) actionEvent.getSource()).getId();
        int index = Integer.parseInt(id.substring(6));
        this.zoo.getZooMaster().moveCreature(this.creature, this.enclosure,this.listEnclosureAviable.get(index));
    }

    public void backButton(ActionEvent actionEvent) {
        EnclosuresController enclosuresController = (EnclosuresController) setScene("layout/enclosures.fxml", "Enclos");
    }
}
