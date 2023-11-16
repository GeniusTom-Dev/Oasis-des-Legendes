package fr.tmm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

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
        appStage = stage;
        stage.setResizable(false);
        stage.setTitle("Oasis des légendes");
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("layout/home.fxml")));
        Image icon = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("assets/logo.png")));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Définit la scène sur le fichier FXML spécifié et renvoie le contrôleur associé.
     *
     * @param fxmlPath Le chemin vers le fichier FXML.
     * @param <T>      Le type du contrôleur.
     * @return Le contrôleur associé au fichier FXML.
     * @author Even Tom
     */
    public static <T> T setScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource(fxmlPath));
            root = loader.load();
            appStage.setScene(new Scene(root));
            if(Objects.equals(title, "")){
                appStage.setTitle("Oasis des légendes");
            }else{
                appStage.setTitle("Oasis des légendes | " + title);
            }
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
