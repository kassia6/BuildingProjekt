package ch.fhnw.oop2.presentationmodels;

/**
 * Created by Céline on 10.01.2017.
 */
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PresentationModel {
/*
    PresentationModel: Welche Daten abgelegt sind (das WAS des Programms)
    */

    // Variablen-Deklaration (alle Konstanten werden GROSS geschrieben):
    private StringProperty title = new SimpleStringProperty("Buildings");
    private static final String FILE_NAME = "/buildings.csv";
    private static final String SPLITTER = ";"; // bei .txt wäre hier Spliter nun TAB = "//t"
    private final ObservableList<Building> buildings = FXCollections.observableArrayList();
    private ObjectProperty<Building> selectedBuilding = new SimpleObjectProperty<>(); // muss ObjectProperty sein!
    private final  Building buildingProxy = new Building();

    private final ObservableList<Command> undoStack = FXCollections.observableArrayList();
    private final ObservableList<Command> redoStack = FXCollections.observableArrayList();
    private final BooleanProperty undoDisabled = new SimpleBooleanProperty();
    private final BooleanProperty redoDisabled = new SimpleBooleanProperty();
    private final ChangeListener propertyChangeListenerForUndoSupport = (observable, oldValue, newValue) -> {
        redoStack.clear();
        undoStack.add(0, new ValueChangeCommand(PresentationModel.this, (Property) observable, oldValue, newValue));
    };

    // 1. Wo das File abgelegt ist:
    private Path getPath(String FILE_NAME, boolean locatedInSameFolder) {
        try {
            if (!true) {
                FILE_NAME = "/" + FILE_NAME;
            }
            return Paths.get(getClass().getResource(FILE_NAME).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    // 2. Wie das File eingelesen wird: Jede Zeile wird einzeln eingelesen:
    private Stream<String> getStreamofLines(String FILE_NAME, boolean locatedInSameFolder) {
        try {
            return Files.lines(getPath(FILE_NAME, locatedInSameFolder), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // 3. Das Lesen des Files: zur Verfügung gestellt als Liste
    private List<Building> readFromFile() {
        try (Stream<String> stream = getStreamofLines(FILE_NAME, true)) {
            return stream.skip(1) // die 1.Zeile ist die Headerzeile, diese soll übersprungen werden
                    .map(s -> new Building(s.split(SPLITTER)))
                    .collect(Collectors.toList());
        }
    }

    // Constructor
    public PresentationModel() {
        buildings.addAll(readFromFile());
        Platform.runLater(() -> setSelectedBuilding(buildings.get(0)));
        // damit zuerst UI-Teil hochfährt und genug Zeit hat, um die Daten aus der TableView korrekt darzustellen.

        // im Constructor: undo/redo-Binding-Logik
        undoDisabled.bind(Bindings.isEmpty(undoStack));
        redoDisabled.bind(Bindings.isEmpty(redoStack));
        selectedBuildingProperty().addListener((observable, oldSelection, newSelection) -> {
                    if (oldSelection != null) {
                        unbindFromProxy(oldSelection);
                        disableUndoSupport(oldSelection);
                    }
                    if (newSelection != null) {
                        bindToProxy(newSelection);
                        enableUndoSupport(newSelection);
                    }
                }
        );
        selectedBuildingProperty().addListener(propertyChangeListenerForUndoSupport);
    }

    // Kommunikation mit den UI-Teilen via Proxy:
    private void bindToProxy(Building building) {
        buildingProxy.idProperty().bindBidirectional(building.idProperty());
        buildingProxy.rankProperty().bindBidirectional(building.rankProperty());
        buildingProxy.nameProperty().bindBidirectional(building.nameProperty());
        buildingProxy.cityProperty().bindBidirectional(building.cityProperty());
        buildingProxy.countryProperty().bindBidirectional(building.countryProperty());
        buildingProxy.height_mProperty().bindBidirectional(building.height_mProperty());
        buildingProxy.height_ftProperty().bindBidirectional(building.height_ftProperty());
        buildingProxy.floorsProperty().bindBidirectional(building.floorsProperty());
        buildingProxy.buildProperty().bindBidirectional(building.buildProperty());
        buildingProxy.architectProperty().bindBidirectional(building.architectProperty());
        buildingProxy.architectual_styleProperty().bindBidirectional(building.architectual_styleProperty());
        buildingProxy.costProperty().bindBidirectional(building.costProperty());
        buildingProxy.materialProperty().bindBidirectional(building.materialProperty());
        buildingProxy.longitudeProperty().bindBidirectional(building.longitudeProperty());
        buildingProxy.latitudeProperty().bindBidirectional(building.latitudeProperty());
        //buildingProxy.imageProperty().bindBidirectional(buildings.imageProperty());
    }

    private void unbindFromProxy(Building building){
        buildingProxy.idProperty().unbindBidirectional(building.idProperty());
        buildingProxy.rankProperty().unbindBidirectional(building.rankProperty());
        buildingProxy.nameProperty().unbindBidirectional(building.nameProperty());
        buildingProxy.cityProperty().unbindBidirectional(building.cityProperty());
        buildingProxy.countryProperty().unbindBidirectional(building.countryProperty());
        buildingProxy.height_mProperty().unbindBidirectional(building.height_mProperty());
        buildingProxy.height_ftProperty().unbindBidirectional(building.height_ftProperty());
        buildingProxy.floorsProperty().unbindBidirectional(building.floorsProperty());
        buildingProxy.buildProperty().unbindBidirectional(building.buildProperty());
        buildingProxy.architectProperty().unbindBidirectional(building.architectProperty());
        buildingProxy.architectual_styleProperty().unbindBidirectional(building.architectual_styleProperty());
        buildingProxy.costProperty().unbindBidirectional(building.costProperty());
        buildingProxy.materialProperty().unbindBidirectional(building.materialProperty());
        buildingProxy.longitudeProperty().unbindBidirectional(building.longitudeProperty());
        buildingProxy.latitudeProperty().unbindBidirectional(building.latitudeProperty());
       // buildingProxy.imageProperty().unbindBidirectional(buildings.imageProperty());




    }

    public Building getBuilding(int id){
        Optional<Building> pmOptional = buildings.stream()
                .filter(building ->  building.getId() == id)
                .findAny();
        return pmOptional.isPresent() ? pmOptional.get() : null;
    }

    private void setRanking() {
        Collections.sort(buildings,
                (o1, o2) -> (int) (Double.valueOf(o2.getHeight_ft()) - Double.valueOf(o1.getHeight_m())));

        for (int i = 0; i < buildings.size(); i++) {
            buildings.get(i).setRank(i+1);
        }

    }


    // die 3 Basisfunktionalitäten "speichern", "neue Zeile hinzufügen" und "Zeile löschen"
    // save-Methode (1:1 von "nationalratswahlen")
    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(FILE_NAME, true))) {
            writer.write("#id;Rank;Building;city;country;height m");
            writer.newLine();
            setRanking();
            buildings.stream().forEach(building -> {
                try {
                    writer.write(building.infoAsLine());
                    writer.newLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            });
        } catch (IOException e) {
            throw new IllegalStateException("saving failed!");
        }
    }

    // new-Methode
    public void addNewBuilding() {
        //int scroll = 0;
        String[] newLine = {"0","0", " ", " ", " ", "0.0","0.0","","","","","","","",""};
       Building newBuilding = new Building(newLine);
        buildings.add(0, newBuilding);
        setSelectedBuilding(newBuilding);
    }

    // delete-Methode
    public void removeBuilding() {
        buildings.remove(getSelectedBuilding());
        setSelectedBuilding(buildings.get(0));
        setRanking();
    }

    // Zusatzfeature: undo/redo
    public <T> void setPropertyValueWithoutUndoSupport(Property<T> property, T newValue){
        property.removeListener(propertyChangeListenerForUndoSupport);
        property.setValue(newValue);
        property.addListener(propertyChangeListenerForUndoSupport);
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }
        Command cmd = undoStack.get(0);
        undoStack.remove(0);
        redoStack.add(0, cmd);
        cmd.undo();
    }
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command cmd = redoStack.get(0);
        redoStack.remove(0);
        undoStack.add(0, cmd);
        cmd.redo();
    }

    private void disableUndoSupport(Building building) {
        building.idProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.rankProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.nameProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.cityProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.countryProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.height_mProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.height_ftProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.floorsProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.buildProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.architectProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.architectual_styleProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.costProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.materialProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.longitudeProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.latitudeProperty().removeListener(propertyChangeListenerForUndoSupport);
        building.imageProperty().removeListener(propertyChangeListenerForUndoSupport);
    }
    private void enableUndoSupport(Building building) {
        building.idProperty().addListener(propertyChangeListenerForUndoSupport);
        building.rankProperty().addListener(propertyChangeListenerForUndoSupport);
        building.nameProperty().addListener(propertyChangeListenerForUndoSupport);
        building.cityProperty().addListener(propertyChangeListenerForUndoSupport);
        building.countryProperty().addListener(propertyChangeListenerForUndoSupport);
        building.height_mProperty().addListener(propertyChangeListenerForUndoSupport);
        building.height_ftProperty().addListener(propertyChangeListenerForUndoSupport);
        building.floorsProperty().addListener(propertyChangeListenerForUndoSupport);
        building.buildProperty().addListener(propertyChangeListenerForUndoSupport);
        building.architectProperty().addListener(propertyChangeListenerForUndoSupport);
        building.architectual_styleProperty().addListener(propertyChangeListenerForUndoSupport);
        building.materialProperty().addListener(propertyChangeListenerForUndoSupport);
        building.longitudeProperty().addListener(propertyChangeListenerForUndoSupport);
        building.latitudeProperty().addListener(propertyChangeListenerForUndoSupport);
        building.imageProperty().addListener(propertyChangeListenerForUndoSupport);
    }

    // alle GETTER und SETTER
    public String getTitle() {
        return title.get();
    }
    public StringProperty titleProperty() {
        return title;
    }
    public void setTitle(String title) {
        this.title.set(title);
    }
    public static String getFileName() {
        return FILE_NAME;
    }
    public static String getSplitter() {
        return SPLITTER;
    }
    public ObservableList<Building> getBuildings() {
        return buildings;
    }
    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding.set(selectedBuilding);
    }
    public ObjectProperty<Building> selectedBuildingProperty() {
        return selectedBuilding;
    }
    public Building getSelectedBuilding() {
        return selectedBuilding.get();
    }
    public Building getBuildingProxy() {
        return buildingProxy;
    }
    public ObservableList<Command> getUndoStack() {
        return undoStack;
    }
    public ObservableList<Command> getRedoStack() {
        return redoStack;
    }
    public boolean getUndoDisabled() {
        return undoDisabled.get();
    }
    public BooleanProperty undoDisabledProperty() {
        return undoDisabled;
    }
    public void setUndoDisabled(boolean undoDisabled) {
        this.undoDisabled.set(undoDisabled);
    }
    public boolean getRedoDisabled() {
        return redoDisabled.get();
    }
    public BooleanProperty redoDisabledProperty() {
        return redoDisabled;
    }
    public void setRedoDisabled(boolean redoDisabled) {
        this.redoDisabled.set(redoDisabled);
    }
    public ChangeListener getPropertyChangeListenerForUndoSupport() {
        return propertyChangeListenerForUndoSupport;
    }

}
