package fr.tmm.controlers;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.enclosure.Enclosure;
import javafx.scene.text.Text;

public class EnclosureController {
    public Text title;
    private Enclosure enclosure;

    public void setIndexEnclosure(int indexEnclosure) {
        this.enclosure = Zoo.getInstance().getEnclosures().get(indexEnclosure);
        title.setText(this.enclosure.getName());
    }
}
