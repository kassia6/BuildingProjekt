package ch.fhnw.oop2.presentationmodels.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ch.fhnw.oop2.presentationmodels.Building;
import ch.fhnw.oop2.presentationmodels.PresentationModel;
import javafx.util.converter.NumberStringConverter;

/**
 * Created by Céline on 09.01.2017.
 */
public class RightSide extends GridPane implements ViewMixin {
        /*
    Die rechte Seite der App ist der Editor-Bereich. Es soll in sich funktionell sein und abgekapselt,
    die Kommunikation läuft nur via PM. Deshalb sind die Methoden "private".
    Es besteht aus Labels, TextFields und einer Textarea.
    Gridpane ist eine Matrixdarstellung. Hier:
    5x2 (5 Zeilen à 2 Spalten)
     */

    // Variablen-Deklaration:
    private Label labelId;
    private TextField tfId;
    private Label labelRank;
    private TextField tfRank;
    private Label labelName;
    private TextField tfName;
    private Label labelCity;
    private TextField tfCity;
    private Label labelCountry;
    private TextField tfCountry;
    private Label labelHeight_m;
    private TextField tfHeight_m;
    private Label labelHeight_ft;
    private TextField tfHeight_ft;
    private Label labelFloors;
    private TextField tfFloors;
    private Label labelBuild;
    private TextField tfBuild;
    private Label labelArchitect;
    private TextField tfArchitect;
    private Label labelArchitectual_style;
    private TextField tfArchitectual_style;
    private Label labelCost;
    private TextField tfCost;
    private Label labelMaterial;
    private TextField tfMaterial;
    private Label labelLongitude;
    private TextField tfLongitude;
    private Label labelLatitude;
    private TextField tfLatitude;
    private Label labelImage;
    private TextField tfImage;
    private PresentationModel pm;

    // Contructor (pro abgekapselter Klasse immer gleicher Aufbau!):
    public RightSide(PresentationModel pm) {
        this.pm = pm;
        getStyleClass().add("form");
        init();
    }

    // alle Variablen initialisieren:
    @Override
    public void initializeControls() {
        labelId = new Label("ID");
        labelRank = new Label("Rank");
        labelName = new Label("Name");
        labelCity = new Label("City");
        labelCountry = new Label("Country");
        labelHeight_m = new Label("Height m");
        labelHeight_ft = new Label("Height ft");
        labelFloors = new Label("Floors");
        labelBuild = new Label("Build");
        labelArchitect = new Label("Architect");
        labelArchitectual_style = new Label("Architectual Style");
        labelCost = new Label("Cost");
        labelMaterial = new Label("Material");
        labelLongitude = new Label("Longitude");
        labelLatitude = new Label("Latitude");
        labelImage = new Label("Image");


        tfId = new TextField();
        tfRank = new TextField();
        tfName = new TextField();
        tfCity = new TextField();
        tfCountry = new TextField();
        tfHeight_m = new TextField();
        tfHeight_ft = new TextField();
        tfFloors = new TextField();
        tfBuild = new TextField();
        tfArchitect = new TextField();
        tfArchitectual_style = new TextField();
        tfCost = new TextField();
        tfMaterial = new TextField();
        tfLongitude = new TextField();
        tfLatitude = new TextField();
        tfImage = new TextField();


    }

    // Anordnung des Layouts inkl. Rezising-Verhalten:
    @Override
    public void layoutControls() {
        // Resizing-Verhalten bei den Kolonnen:
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(new ColumnConstraints(), cc);

        // Resizing-Verhalten bei den Zeilen:
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rc, rc);

        // alle Teile hinzufügen: columnIndex, rowIndex
        //add(labelRank, 0, 0);
        add(labelName, 0, 3);
        add(labelCity, 0, 4);
        add(labelHeight_m, 0, 5);
        add(labelFloors, 0, 6);
        add(labelArchitect, 0, 7);
        add(labelCost, 0, 8);
        add(labelLongitude, 0, 9);


        add(labelBuild, 4, 4);
        add(labelHeight_ft, 4, 5);
        add(labelCountry, 4, 6);
        add(labelArchitectual_style, 4, 7);
        add(labelMaterial, 4, 8);
        add(labelLatitude, 4, 9);
        //add(labelImage, 3, 7);

        //add(tfRank, 1, 0);
        add(tfName, 1, 3);
        add(tfCity, 1, 4);
        add(tfHeight_m, 1, 5);
        add(tfFloors, 1, 6);
        add(tfArchitect, 1, 7);
        add(tfCost, 1, 8);
        add(tfLongitude, 1, 9);



        add(tfBuild, 5, 4);
        add(tfHeight_ft, 5, 5);
        add(tfCountry, 5, 6);
        add(tfArchitectual_style, 5, 7);
        add(tfMaterial, 5, 8);
        add(tfLatitude, 5, 9);
        //add(tfImage, 4, 7);




    }

    // falls ein Event auf einem Button ausgelöst werden soll:
    @Override
    public void addEventHandlers() {
    }

    @Override
    public void addBindings() {
        Building buildingProxy = pm.getBuildingProxy();
        tfId.textProperty().bindBidirectional(buildingProxy.idProperty(),new NumberStringConverter());
        tfRank.textProperty().bindBidirectional(buildingProxy.rankProperty(),new NumberStringConverter());
        tfName.textProperty().bindBidirectional(buildingProxy.nameProperty());
        tfCity.textProperty().bindBidirectional(buildingProxy.cityProperty());
        tfCountry.textProperty().bindBidirectional(buildingProxy.countryProperty());
        tfHeight_m.textProperty().bindBidirectional(buildingProxy.height_mProperty(),new NumberStringConverter());
        tfHeight_ft.textProperty().bindBidirectional(buildingProxy.height_ftProperty(),new NumberStringConverter());
        tfFloors.textProperty().bindBidirectional(buildingProxy.floorsProperty());
        tfBuild.textProperty().bindBidirectional(buildingProxy.buildProperty());
        tfArchitect.textProperty().bindBidirectional(buildingProxy.architectProperty());
        tfArchitectual_style.textProperty().bindBidirectional(buildingProxy.architectual_styleProperty());
        tfCost.textProperty().bindBidirectional(buildingProxy.costProperty());
        tfMaterial.textProperty().bindBidirectional(buildingProxy.materialProperty());
        tfLongitude.textProperty().bindBidirectional(buildingProxy.longitudeProperty());
        tfLatitude.textProperty().bindBidirectional(buildingProxy.latitudeProperty());
       // tfImage.textProperty().bindBidirectional(buildingProxy.imageProperty());


    }

    // GETTER UND SETTER
    public Label getLabelId() {
        return labelId;
    }
    public void setLabelId(Label labelId) {
        this.labelId = labelId;
    }
    public TextField getTfId() {
        return tfId;
    }
    public void setTfId(TextField tfId) {
        this.tfId = tfId;
    }


    public Label getLabelRank() {
        return labelRank;
    }
    public void setLabelRank(Label labelRank) {
        this.labelRank = labelRank;
    }
    public TextField getTfRank() {
        return tfRank;
    }
    public void setTfRank(TextField tfRank) {
        this.tfRank = tfRank;
    }


    public Label getLabelName() {
        return labelName;
    }
    public void setLabelName(Label labelName) {
        this.labelName = labelName;
    }
    public TextField getTfName() {
        return tfName;
    }
    public void setTfName(TextField tfName) {
        this.tfName = tfName;
    }


    public Label getLabelCity() {
        return labelCity;
    }
    public void setLabelCity(Label labelCity) {
        this.labelCity = labelCity;
    }
    public TextField getTfCity() {
        return tfCity;
    }
    public void setTfCity(TextField tfCity) {
        this.tfCity = tfCity;
    }


    public Label getLabelCountry() {
        return labelCountry;
    }
    public void setLabelCountry(Label labelCountry) {
        this.labelCountry = labelCountry;
    }
    public TextField getTfCountry() {
        return tfCountry;
    }
    public void setTfCountry(TextField tfCountry) {
        this.tfCountry = tfCountry;
    }


    public Label getLabelHeight_m() {
        return labelHeight_m;
    }
    public void setLabelAHeight_m(Label labelHeight_m) {
        this.labelHeight_m = labelHeight_m;
    }
    public TextField getTfHeight_m() {
        return tfHeight_m;
    }
    public void setTfHeight_m(TextField tfHeight_m) {
        this.tfHeight_m = tfHeight_m;
    }


    public Label getLabelHeight_ft() {
        return labelHeight_ft;
    }
    public void setLabelHeight_ft (Label labelHeight_ft) {
        this.labelHeight_ft = labelHeight_ft;
    }
    public TextField getTfHeight_ft() {
        return tfHeight_ft;
    }
    public void setTfHeight_ft(TextField tfHeight_ft) {
        this.tfHeight_ft = tfHeight_ft;
    }


    public Label getLabelFloors() {
        return labelFloors;
    }
    public void setLabelFloors (Label labelFloors) {
        this.labelFloors = labelFloors;
    }
    public TextField getTfFloors() {
        return tfFloors;
    }
    public void setTfFloors(TextField tfFloors) {
        this.tfFloors = tfFloors;
    }


    public Label getLabelBuild() {
        return labelBuild;
    }
    public void setLabelBuild (Label labelBuild) {
        this.labelBuild = labelBuild;
    }
    public TextField getTfBuild() {
        return tfBuild;
    }
    public void setTfBuild(TextField tfBuild) {
        this.tfBuild = tfBuild;
    }


    public Label getLabelArchitect() {
        return labelArchitect;
    }
    public void setLabelArchitect(Label labelArchitect) {
        this.labelArchitect= labelArchitect;
    }
    public TextField getTfArchitect() {
        return tfArchitect;
    }
    public void setTfArchitect(TextField tfArchitect) {
        this.tfArchitect = tfArchitect;
    }


    public Label getLabelArchitectual_style() {
        return labelArchitectual_style;
    }
    public void setLabelArchitectual_style (Label labelArchitectual_style) {
        this.labelArchitectual_style = labelArchitectual_style;
    }
    public TextField getTfArchitectual_style() {
        return tfArchitectual_style;
    }
    public void setTfArchitectual_style(TextField tfArchitectual_style) {
        this.tfArchitectual_style = tfArchitectual_style;
    }


    public Label getLabelCost() {
        return labelCost;
    }
    public void setLabelCost(Label labelCost) {
        this.labelCost = labelCost;
    }
    public TextField getTfCost() {
        return tfCost;
    }
    public void setTfCost(TextField tfCost) {
        this.tfCost = tfCost;
    }


    public Label getLabelMaterial() {
        return labelMaterial;
    }
    public void setLabelMaterial (Label labelMaterial) {
        this.labelMaterial = labelMaterial;
    }
    public TextField getTfMaterial() {
        return tfMaterial;
    }
    public void setTfMaterial(TextField tfMaterial) {
        this.tfMaterial = tfMaterial;
    }


    public Label getLabelLongitude() {
        return labelLongitude;
    }
    public void setLabelLongitude(Label labelLongitude) {
        this.labelLongitude = labelLongitude;
    }
    public TextField getTfLongitude() {
        return tfLongitude;
    }
    public void setTfLongitude(TextField tfLongitude) {
        this.tfLongitude = tfLongitude;
    }


    public Label getLabelLatiitude() {
        return labelLatitude;
    }
    public void setLabelLatitude (Label labelLatitude) {
        this.labelLatitude = labelLatitude;
    }
    public TextField getTfLatitude() {
        return tfLatitude;
    }
    public void setTfLatitude(TextField tfLatitude) {
        this.tfLatitude= tfLatitude;
    }


    public Label getLabelImage() {
        return labelImage;
    }
    public void setLabelImage (Label labelImage) {
        this.labelImage = labelImage;
    }
    public TextField getTfImage() {
        return tfImage;
    }
    public void setTfImage(TextField tfImage) {
        this.tfImage = tfImage;
    }


}
