package ch.fhnw.oop2.presentationmodels.view;

/**
 * Created by Céline on 09.01.2017.
 */
public interface ViewMixin {
    default void init(){
        initializeControls();
        layoutControls();
        addEventHandlers();
        addValueChangedListeners();
        addBindings();
    }

    void initializeControls();
    void layoutControls();

    default void addEventHandlers(){
    }

    default void addValueChangedListeners(){
    }

    default void addBindings(){
    }
}

