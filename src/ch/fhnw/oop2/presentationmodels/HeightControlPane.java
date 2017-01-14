package ch.fhnw.oop2.presentationmodels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

import java.text.DecimalFormat;

public class HeightControlPane extends Region {

    // *** Variablen: Elemente des Custom Control für Height: *** //
    private Label feet;
    private Label meter;
    private Label lFeet; // Inhalt
    private Label lMeter; // Inhalt
    private Slider slider;

    // *** Variablen: Hilfsvariablen: *** //
    private String calcMtoFt; // Hilfsvariable, für Umrechnung Meter to Feet
    private DecimalFormat df = new DecimalFormat("#.00");
    private SimpleDoubleProperty ghost;

    // *** Variablen: 4 Panes benutzt, alles wird auf dem pasterPane platziert: *** //
    private Pane drawingPaneEifeli, drawingPaneSlider, dummyBuildPane, masterPane;

    // *** Variablen: für die Grösse/Resizing: *** //
    private static final double PREFERRED_SIZE = 300;
    private static final double MINIMUM_SIZE = 150;
    private static final double MAXIMUM_SIZE = 600;

    private final PresentationModel pm; // TODO: 10.01.2017  Einfügen PM bei oop2-Person

    // Constructor:
    public HeightControlPane(PresentationModel pm) {
        this.pm = pm;
        initialSelf();
        initializeControls();
        layoutControls();
        addValueChangeListeners();
        addBindings();
    }

    private void initialSelf() {
        String fonts = getClass().getResource("/font.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("/style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        // Resizing für Pane gem. Holz:
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();
        double horizontalPadding = padding.getLeft() + padding.getRight();
        setMinSize(MINIMUM_SIZE + horizontalPadding, MINIMUM_SIZE + verticalPadding);
        setPrefSize(PREFERRED_SIZE + horizontalPadding, PREFERRED_SIZE + verticalPadding);
        setMaxSize(MAXIMUM_SIZE + horizontalPadding, MAXIMUM_SIZE + verticalPadding);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        double width = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        double size = Math.max(Math.min(Math.min(width, height), MAXIMUM_SIZE), MINIMUM_SIZE);
        double scalingFactor = size / PREFERRED_SIZE;

        if (width > 0 && height > 0) {
            masterPane.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            masterPane.setScaleX(scalingFactor);
            masterPane.setScaleY(scalingFactor);
        }
    }

    private void initializeControls() {
        slider = new Slider();
        feet = new Label("ft:");
        meter = new Label("m:");
        lFeet = new Label();
        lMeter = new Label();
        drawingPaneEifeli = new Pane();
        drawingPaneSlider = new Pane();
        masterPane = new Pane();
        dummyBuildPane = new Pane();
        ghost = new SimpleDoubleProperty();
    }

    private void layoutControls() {
        // Verbindung zu style.css
        drawingPaneEifeli.getStyleClass().addAll("drawingPaneEifeli");
        drawingPaneSlider.getStyleClass().addAll("drawingPaneSlider");
        dummyBuildPane.getStyleClass().addAll("dummyBuild");
        dummyBuildPane.getStyleClass().addAll("dummyBuildPane");
        masterPane.getStyleClass().addAll("master");
        slider.getStyleClass().addAll("Slider");
        lFeet.getStyleClass().addAll("lFeet");
        lMeter.getStyleClass().addAll("lMeter");
        meter.getStyleClass().addAll("lMeter");
        feet.getStyleClass().addAll("lMeter");

        // Slider configurations:
        slider.setOrientation(Orientation.VERTICAL);
        slider.setPrefHeight(PREFERRED_SIZE);
        slider.setMinHeight(PREFERRED_SIZE);
        slider.setMajorTickUnit(200);
        slider.setMax(1500);
        slider.setShowTickLabels(false); // false= keine Anzeige der Ticks

        dummyBuildPane.setLayoutX(150);
        drawingPaneEifeli.relocate(40, 230);
        feet.setLayoutX(230);
        meter.setLayoutX(-80);
        lFeet.setLayoutX(250);
        lMeter.setLayoutX(-60);

        drawingPaneSlider.getChildren().addAll(slider, feet, meter, lFeet, lMeter); // weil der Slider in einer Pane ist!
        masterPane.getChildren().addAll(drawingPaneSlider, drawingPaneEifeli, dummyBuildPane);
        getChildren().addAll(masterPane);
    }

    private void addValueChangeListeners() {
        // *** Wenn sich der Wert des Sliders ändert, dann ändert sich die Höhe des Dummy-Gebäude
        // (mit Korrektur der Position und Umrechnung in Pixel): *** //
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            dummyBuildPane.setLayoutY(PREFERRED_SIZE - (newValue.doubleValue() * 0.19));
        });

        // *** Wenn sich der Wert des Sliders ändert, dann ändert sich der Inhalt von LabelMeter&LabelFeet: *** //
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lMeter.textProperty().set(df.format(newValue.doubleValue()));
        });
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lFeet.textProperty().set(calculateMtoFt(newValue.doubleValue()));
        });


        // *** Wenn sich der Wert des Sliders ändert, dann verschiebt sich die Position des LabelMeter&LabelFeet: *** //
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lFeet.setLayoutY(dummyBuildPane.getLayoutY() - 18);
            feet.setLayoutY(dummyBuildPane.getLayoutY() - 18);
            lMeter.setLayoutY(dummyBuildPane.getLayoutY() - 18);
            meter.setLayoutY(dummyBuildPane.getLayoutY() - 18);
        });
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pm.getBuildingProxy().setHeight_ft(calculateMtoFtdouble(newValue.doubleValue()));
        });
        ghost.addListener((observable, oldValue, newValue) -> {
            pm.getBuildingProxy().setHeight_m(calculateFttoMdouble(newValue.doubleValue()));
        });

    }

    private void addBindings() {
        slider.valueProperty().bindBidirectional(pm.getBuildingProxy().height_mProperty());//TODO: 10.01.2017  Einfügen PM bei oop2-Person
        dummyBuildPane.prefHeightProperty().bind(slider.valueProperty().multiply(0.19)); //calculation meter to pixel
        ghost.bind(pm.getBuildingProxy().height_ftProperty());
    }

    // *** Hilfsmethoden für die Umrechnung Meter zu Feet***//
    private String calculateMtoFt(double pmeter) {
        double ftValue = pmeter * 3.28084;
        calcMtoFt = String.valueOf(df.format(ftValue));
        return calcMtoFt;
    }

    private double calculateMtoFtdouble(double pmeter) {
        double ftValue = pmeter * 3.28084;
        double calcMtoFt = ftValue;
        return calcMtoFt;
    }

    private String calculateFttoM(double ft) {
        double meterVal = ft / 3.28084;
        return String.valueOf(meterVal);
    }

    private double calculateFttoMdouble(double ft) {
        double meterVal;
        return meterVal = ft / 3.28084;

    }

    public Label getFeet() {
        return feet;
    }

    public void setFeet(Label feet) {
        this.feet = feet;
    }

    public Label getMeter() {
        return meter;
    }

    public void setMeter(Label meter) {
        this.meter = meter;
    }

    public Label getlFeet() {
        return lFeet;
    }

    public void setlFeet(Label lFeet) {
        this.lFeet = lFeet;
    }

    public Label getlMeter() {
        return lMeter;
    }

    public void setlMeter(Label lMeter) {
        this.lMeter = lMeter;
    }
}
