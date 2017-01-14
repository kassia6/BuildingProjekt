package ch.fhnw.oop2.presentationmodels;

import javafx.beans.property.*;

/**
 * Created by Céline on 09.01.2017.
 */
public class Building {

    /*
    In dieser Klasse geth es nur darum, welche Kolonnen-Header aus der Datei zur Verfügung stehen.
    Alles, was die Datei ansonsten betrifft, ist im PM geregelt.
         */

    // Spaltenüberschriften:
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();  // SimpleIntegerProperty
    private final SimpleIntegerProperty rank = new SimpleIntegerProperty();// SimpleIntegerProperty
    private final StringProperty name= new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final SimpleDoubleProperty height_m = new SimpleDoubleProperty(); // SimpleDoubleProperty
    private final SimpleDoubleProperty height_ft = new SimpleDoubleProperty(); // SimpleDoubleProperty
    private final StringProperty floors = new SimpleStringProperty();
    private final StringProperty build = new SimpleStringProperty();
    private final StringProperty architect = new SimpleStringProperty();
    private final StringProperty architectual_style = new SimpleStringProperty();
    private final StringProperty cost = new SimpleStringProperty();
    private final StringProperty material = new SimpleStringProperty();
    private final StringProperty longitude = new SimpleStringProperty();
    private final StringProperty latitude = new SimpleStringProperty();
    private final StringProperty image = new SimpleStringProperty();


    // Constructor, ein String-Array "line". Beschreibt, wie die einzelne Zeile der Datei aufgebaut ist:
    public Building(String[] line){
        setId(Integer.parseInt(line[0]));
        setRank(Integer.parseInt(line[1]));
        setName(line[2]);
        setCity(line[3]);
        setCountry(line[4]);
        setHeight_m(Double.parseDouble(line[5]));
        setHeight_ft(Double.parseDouble(line[6]));
        setFloors(line[7]);
        setBuild(line[8]);
        setArchitect(line[9]);
        setArchitectual_style(line[10]);
        setCost(line[11]);
        setMaterial(line[12]);
        setLongitude(line[13]);
        setLatitude(line[14]);
       // setImage(line[15]);

    }

    // 2. Constructor für Proxy:
    public Building(){
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    // Daten zur Verfügung als kompletter String:
    public String infoAsLine(){
        return String.join(";",Integer.toString(getId()), Integer.toString(getRank()), getName(), getCity(), getCountry(),
                Double.toString(getHeight_m()),Double.toString(getHeight_ft()),getFloors(), getBuild(), getArchitect(), getArchitectual_style(),
                getCost(), getMaterial(), getLongitude(), getLatitude(), getImage());
    }

    // alle GETTER und SETTER
    public Integer getId() {
        return id.get();
    }
    public SimpleIntegerProperty idProperty() {  return id;} //SimpleIntegerProperty
    public void setId(Integer id) {
        this.id.set(id);
    }
    public Integer getRank() {
        return rank.get();
    }
    public SimpleIntegerProperty rankProperty() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank.set(rank);
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getCity() {
        return city.get();
    }
    public StringProperty cityProperty(){
        return city;
    }
    public void setCity(String city){
        this.city.set(city);
    }
    public String getCountry() {
        return country.get();
    }
    public StringProperty countryProperty() {
        return country;
    }
    public void setCountry(String country) {
        this.country.set(country);
    }
    public Double getHeight_m() {
        return height_m.get();
    }
    public SimpleDoubleProperty height_mProperty() {return height_m;}
    public void setHeight_m(Double height_m) {
        this.height_m.set(height_m);
    }

    public Double getHeight_ft() {
        return height_ft.get();
    }
    public SimpleDoubleProperty height_ftProperty() {
        return height_ft;
    }
    public void setHeight_ft(double height_ft) {
        this.height_ft.set(height_ft);
    }
    public String getFloors() {
        return floors.get();
    }
    public StringProperty floorsProperty() {
        return floors;
    }
    public void setFloors(String floors) {
        this.floors.set(floors);
    }
    public String getBuild() {
        return build.get();
    }
    public StringProperty buildProperty() {
        return build;
    }
    public void setBuild(String build) {
        this.build.set(build);
    }
    public String getArchitect() {
        return architect.get();
    }
    public StringProperty architectProperty() {
        return architect;
    }
    public void setArchitect(String architect) {
        this.architect.set(architect);
    }
    public String getArchitectual_style() {
        return architectual_style.get();
    }
    public StringProperty architectual_styleProperty() {
        return architectual_style;
    }
    public void setArchitectual_style(String architectual_style) {
        this.architectual_style.set(architectual_style);
    }
    public String getCost() {
        return cost.get();
    }
    public StringProperty costProperty() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost.set(cost);
    }
    public String getMaterial() {
        return material.get();
    }
    public StringProperty materialProperty() {
        return material;
    }
    public void setMaterial(String material) {
        this.material.set(material);
    }
    public String getLongitude() {
        return longitude.get();
    }
    public StringProperty longitudeProperty() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude.set(longitude);
    }
    public String getLatitude() {
        return latitude.get();
    }
    public StringProperty latitudeProperty() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude.set(latitude);
    }
    public String getImage() {
        return image.get();
    }
    public StringProperty imageProperty() {
        return image;
    }
    //public void setImage(String image) {
       // this.image.set(image);
    }


