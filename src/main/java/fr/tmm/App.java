package fr.tmm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * La classe principale de l'application.
 */
public class App extends Application {

    private static BorderPane root;

    private static Stage appStage;

    /**
     * La méthode principale qui lance l'application.
     * @author Even Tom
     */
    public static void main() {
        launch();
    }

    /**
     * Démarre l'application et configure la scène initiale.
     *
     * @param stage La scène principale de cette application.
     * @author Even Tom
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Oasis des légendes");
        root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/home.fxml"));
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("assets/logo.png"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
