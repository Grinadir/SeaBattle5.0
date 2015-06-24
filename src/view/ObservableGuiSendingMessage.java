package view;


/**
 * Created by User on 20.06.2015.
 */
public interface ObservableGuiSendingMessage {
    void registerObserver(ObserverOfGuiSendingMessage o);

    void removeObserver(ObserverOfGuiSendingMessage o);

    void notifySendingMessage(String message);

}
