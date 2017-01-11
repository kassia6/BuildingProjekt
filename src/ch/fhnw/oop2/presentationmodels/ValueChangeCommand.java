package ch.fhnw.oop2.presentationmodels;

import javafx.beans.property.Property;

/**
 * Created by Céline on 09.01.2017.
 */
public class ValueChangeCommand<T> implements Command {

    private final PresentationModel pm;
    private final Property<T> property;
    private final T oldValue;
    private final T  newValue;

    public ValueChangeCommand(PresentationModel pm, Property property, T oldValue, T newValue) {
        this.pm = pm;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public void undo() {
        pm.setPropertyValueWithoutUndoSupport(property, oldValue);
    }

    public void redo() {
        pm.setPropertyValueWithoutUndoSupport(property, newValue);
    }
}

