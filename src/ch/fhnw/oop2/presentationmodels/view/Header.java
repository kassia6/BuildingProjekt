package ch.fhnw.oop2.presentationmodels.view;

import ch.fhnw.oop2.presentationmodels.Building;
import ch.fhnw.oop2.presentationmodels.HeightControlPane;
import ch.fhnw.oop2.presentationmodels.PresentationModel;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Created by Céline on 13.01.2017.
 */
public class Header extends GridPane implements ViewMixin {

    private final PresentationModel pm;

    private Label labelRank;
    private Label labelCity;
    private Label labelCountry;
    private Label labelName;
    private Label labelHeight_m;
    private HBox cityalignment;
    private HeightControlPane customControl;

    public Header(PresentationModel pm) {
        this.pm = pm;
        getStyleClass().add("form");
        init();
    }

    @Override
    public void initializeControls() {
        labelRank = new Label();
        labelRank.getStyleClass().add("rank");
        labelName = new Label("Name");
        labelName.getStyleClass().add("name");
        labelCity = new Label("City");
        labelCity.getStyleClass().add("cityCountryHeight");
        labelCountry = new Label("Country");
        labelCountry.getStyleClass().add("cityCountryHeight");
        labelHeight_m = new Label("Height_m");
        labelHeight_m.getStyleClass().add("cityCountryHeight");
        cityalignment = new HBox();
        customControl = new HeightControlPane(pm);

    }

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
        add(labelRank, 0, 0);
        add(labelName, 0, 1);
        add(labelHeight_m, 0, 3);
        cityalignment.getChildren().addAll(labelCity, labelCountry);
        add(cityalignment, 0, 2);
        add(customControl,3,0,4,4);
    }

    @Override
    public void addEventHandlers() {
    }

    @Override
    public void addBindings() {
        Building buildingProxy = pm.getBuildingProxy();
        labelRank.textProperty().bind(buildingProxy.rankProperty().asString());
        labelName.textProperty().bind(buildingProxy.nameProperty());
        labelCity.textProperty().bind(buildingProxy.cityProperty().concat(","));
        labelCountry.textProperty().bind(buildingProxy.countryProperty());
        labelHeight_m.textProperty().bind(buildingProxy.height_mProperty().asString("%.2f m"));

    }

    public Label getLabelRank() {
        return labelRank;
    }

    public void setLabelRank(Label labelRank) {
        this.labelRank = labelRank;
    }

    public Label getLabelName() {
        return labelName;
    }

    public void setLabelName(Label labelName) {
        this.labelName = labelName;
    }

    public Label getLabelCity() {
        return labelCity;
    }

    public void setLabelCity(Label labelCity) {
        this.labelCity = labelCity;
    }

    public Label getLabelCountry() {
        return labelCountry;
    }

    public void setLabelCountry(Label labelCountry) {
        this.labelCountry = labelCountry;
    }

    public Label getLabelHeight_m() {
        return labelHeight_m;
    }

    public void setLabelAHeight_m(Label labelHeight_m) {
        this.labelHeight_m = labelHeight_m;
    }

}
