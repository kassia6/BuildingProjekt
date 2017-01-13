import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ch.fhnw.oop2.presentationmodels.view.AppUI;
import ch.fhnw.oop2.presentationmodels.PresentationModel;

/**
 * Created by Céline on 09.01.2017.
 */
public class AppStarter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        PresentationModel pm = new PresentationModel();
        Parent rootPanel = new AppUI(pm);
        Scene scene = new Scene(rootPanel);
        String stylesheet = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        primaryStage.titleProperty().bind(pm.titleProperty());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
