package view;


import model.ObserverOfMessage;

/**
 * Created by User on 20.06.2015.
 */
public interface ObservableGuiSendingMessage {
    void registerObserver(ObserverOfMessage o);
    void removeObserver(ObserverOfMessage o);
    void notify(String message);

}
