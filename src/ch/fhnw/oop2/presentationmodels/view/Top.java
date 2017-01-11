package ch.fhnw.oop2.presentationmodels.view;

import ch.fhnw.oop2.presentationmodels.PresentationModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Created by Céline on 09.01.2017.
 */
public class Top extends HBox implements ViewMixin{
    /*
    /*
    Der Top-Bereich der App ist die Leiste mit den Buttons. Es soll in sich funktionell sein und abgekapselt,
    die Kommunikation läuft nur via PM. Deshalb sind die Methoden "private".
    HBox ist horizontal ausgerichtet und beinhaltet die Buttons save; add; delete; evt. undo; evt. redo; evt. search.
     */

    // Variablen-Deklaration:
    private Button saveDataButton;
    private Button newDataButton;
    private Button deleteDataButton;
    private Button undoButton;
    private Button redoButton;
    private PresentationModel pm;

    // Contructor (pro abgekapselter Klasse immer gleicher Aufbau!):
    public Top(PresentationModel pm) {
        this.pm = pm;
        init();
    }

    // alle Variablen initialisieren:
    @Override
    public void initializeControls() {
        saveDataButton = new Button("save");
        newDataButton = new Button("+");
        deleteDataButton = new Button("x");
        undoButton = new Button("undo");
        redoButton = new Button("redo");
    }
    // Anordnung des Layouts inkl. Rezising-Verhalten:
    @Override
    public void layoutControls() {
        setPadding(new Insets(20)); // Padding am Rand rundherum gleichmässig
        setSpacing(5);  // Spacing zwischen den Elementen , wenn Buttons beim vergrösserten Fenster mitwachsen sollen,
        // braucht es eine MaxGrösse

        // Resizing-Verhalten:
        saveDataButton.setMaxWidth(1024.0);
        saveDataButton.setMaxHeight(1024.0);
        newDataButton.setMaxWidth(1024.0);
        newDataButton.setMaxHeight(1024.0);
        deleteDataButton.setMaxWidth(1024.0);
        deleteDataButton.setMaxHeight(1024.0);
        undoButton.setMaxWidth(1024.0);
        undoButton.setMaxHeight(1024.0);
        redoButton.setMaxWidth(1024.0);
        redoButton.setMaxHeight(1024.0);
        // hier alle Buttons zur Zeile hinzufügen:
        getChildren().addAll(saveDataButton, newDataButton, deleteDataButton, undoButton, redoButton);
    }

    // falls ein Event auf einem Button ausgelöst werden soll:
    @Override
    public void addEventHandlers() {
        saveDataButton.setOnAction(event -> pm.save());
        newDataButton.setOnAction(event -> pm.addNewBuilding());
        deleteDataButton.setOnAction(event -> pm.removeBuilding());
        undoButton.setOnAction(event -> pm.undo());
        redoButton.setOnAction(event -> pm.redo());
    }

    // wie auf eine Wertänderung reagiert werden soll (Kommunikation via PM):
    @Override
    public void addValueChangedListeners(){
    }

    @Override
    public void addBindings() {
        undoButton.disableProperty().bind(pm.undoDisabledProperty());
        redoButton.disableProperty().bind(pm.redoDisabledProperty());
    }

    // GETTER und SETTER
    public Button getSaveDataButton() {
        return saveDataButton;
    }

    public void setSaveDataButton(Button saveDataButton) {
        this.saveDataButton = saveDataButton;
    }

    public Button getNewDataButton() {
        return newDataButton;
    }

    public void setNewDataButton(Button newDataButton) {
        this.newDataButton = newDataButton;
    }

    public Button getDeleteDataButton() {
        return deleteDataButton;
    }

    public void setDeleteDataButton(Button deleteDataButton) {
        this.deleteDataButton = deleteDataButton;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public void setUndoButton(Button undoButton) {
        this.undoButton = undoButton;
    }

    public Button getRedoButton() {
        return redoButton;
    }

    public void setRedoButton(Button redoButton) {
        this.redoButton = redoButton;
    }

    public PresentationModel getPm() {
        return pm;
    }

    public void setPm(PresentationModel pm) {
        this.pm = pm;
    }
}
