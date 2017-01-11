package ch.fhnw.oop2.presentationmodels.view;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ch.fhnw.oop2.presentationmodels.PresentationModel;

/**
 * Created by Céline on 09.01.2017.
 */
public class AppUI extends BorderPane implements ViewMixin{
    /*
    UserInterface: Wie der Aufbau ist (das WIE des Programms)
    - Aufbau als BorderPane, davon nur TOP und CENTER gebraucht
    - Top-Bereich ist die HBox, dort sind alle Buttons platziert
    - Center-Bereich teilt sich anhand der SplitPane in linke und rechte Seite
    - linke Seite ist eine TableView, also die Tabellenansicht
    - rechte Seite ist eine Gridpane, also der der Editorbereich
    */

    // Variablen-Deklaration (hier die Bestandteile des UI):
    private HBox top; // Attribut des BorderPane TOP
    private SplitPane splitpane; // Attribut des BorderPane CENTER:
    private LeftSide leftSide; // Attribut des BoderPane CENTER linke Seite
    private RightSide rightSide; // Attribut des BoderPane CENTER rechte Seite
    private final PresentationModel pm;

    // Contructor (pro abgekapselter Klasse immer gleicher Aufbau!):
    public AppUI(PresentationModel pm) {
        this.pm = pm;
        init();
        // addEventHandlers(); --> braucht es nicht mehr, da in jeweiliger Klasse abgehandelt ist.
        // addValueChangedListener(); --> braucht es nicht mehr, da in jeweiliger Klasse abgehandelt ist.
        // addBindings();     --> braucht es nicht mehr, da in jeweiliger Klasse abgehandelt ist.
    }

    // alle Variablen initialisieren:
    public void initializeControls() {
        top = new Top(pm);
        splitpane = new SplitPane();
        splitpane.setDividerPositions(0.2);
        leftSide = new LeftSide(pm);
        rightSide = new RightSide(pm);
    }

    // Anordnung des Layouts inkl. Rezising-Verhalten:
    public void layoutControls() {
        setTop(top); // TOP: der Button-Bereich
        setCenter(splitpane); // CENTER: Splitpane, die sich in links und rechts aufteilt
        splitpane.setOrientation(Orientation.HORIZONTAL);
        splitpane.getItems().addAll(leftSide, rightSide); // alle Elemente der SplitPane!
    }
}

