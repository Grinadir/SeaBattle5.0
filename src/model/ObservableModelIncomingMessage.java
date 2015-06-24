package model;


/**
 * Created by User on 20.06.2015.
 */
public interface ObservableModelIncomingMessage {
    void registerObserver(ObserverOfModelIncomingMessage o);

    void removeObserver(ObserverOfModelIncomingMessage o);

    void notify(String message);

}
