package net;


/**
 * Created by User on 20.06.2015.
 */
public interface ObservableClientServerConnector {
    void registerObserver(ObserverOfModelIncomingMessage o);

    void removeObserver(ObserverOfModelIncomingMessage o);

    void notify(String message);

    Server getServer();

    Client getClient();

    void updateMessage(String string);
}
