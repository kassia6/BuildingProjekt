package ch.fhnw.oop2.presentationmodels.view;

import ch.fhnw.oop2.presentationmodels.Building;
import ch.fhnw.oop2.presentationmodels.PresentationModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Céline on 09.01.2017.
 */
public class LeftSide extends TableView<Building> implements ViewMixin {
        /*
    Die linke Seite der App ist die Tabellenansicht. Es soll in sich funktionell sein und abgekapselt,
    die Kommunikation läuft nur via PM. Deshalb sind die Methoden "private".
    TableView besteht hier aus 4 Kolonnen mit den Überschriften Status; Abfahrt; nach; Gleis.
     */

    // Variablen-Deklaration:
    private TableColumn<Building, Integer> rank;
    private TableColumn<Building, String> name;
    private TableColumn<Building, String> country;
    private PresentationModel pm;

    // Contructor (pro abgekapselter Klasse immer gleicher Aufbau!):
    public LeftSide(PresentationModel pm) {
        super(pm.getBuildings());
        getStyleClass().add("form");
        this.pm = pm;
        init();
    }

    // alle Variablen initialisieren:
    @Override
    public void initializeControls() {
        rank = new TableColumn("rank");
        rank.setCellValueFactory(param -> param.getValue().rankProperty().asObject());
        name= new TableColumn("name");
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        country = new TableColumn("country");
        country.setCellValueFactory(param -> param.getValue().countryProperty());

        // hier noch die Kolonnen mit den entsprechenden Headern erstellen/hinzufügen:
        getColumns().addAll(rank, name, country);
    }

    // Anordnung des Layouts inkl. Rezising-Verhalten:
    @Override
    public void layoutControls() {
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setMinWidth(250);
    }

    // falls ein Event auf einem Button ausgelöst werden soll:
    @Override
    public void addEventHandlers() {
        getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    pm.setSelectedBuilding(newValue);
                });
    }

    @Override
    public void addBindings() {
    }

    @Override
    public void addValueChangedListeners() {
        pm.selectedBuildingProperty().addListener((observable, oldValue, newValue) -> {
            requestFocus();
            if(getSelectionModel().getSelectedItem() == newValue){
                return;
            }
            getSelectionModel().select(newValue);
            scrollTo(newValue);
        });
    }

    // GETTER und SETTER
    public TableColumn<Building, Integer> getRank() {
        return rank;
    }
    public void setRank(TableColumn<Building, Integer> rank) {
        this.rank = rank;
    }

    public TableColumn<Building, String> getName() {
        return name;
    }
    public void setName(TableColumn<Building, String> name) {
        this.name = name;
    }

    public TableColumn<Building, String> getCountry() {
        return country;
    }
    public void setCountry(TableColumn<Building, String> country) {
        this.country = country;
    }

    public PresentationModel getPm() {
        return pm;
    }
    public void setPm(PresentationModel pm) {
        this.pm = pm;
    }
}


