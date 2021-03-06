package net;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientServerConnector implements ObservableClientServerConnector {
    private Server server;
    private Client client;
    private SystemOfIncomingMessage systemOfIncomingMessage;
    private ArrayList observers;
    private String WhoClientOrServer;

    public ClientServerConnector() {
        observers = new ArrayList();
    }

    public String getWhoClientOrServer() {
        return WhoClientOrServer;
    }

    public void call() throws Exception {
        server = new Server();
        client = new Client();
        systemOfIncomingMessage = new SystemOfIncomingMessage(ClientServerConnector.this);
        tryFunctionToConnection();
        if (this.getServer().isClosed()) {
            WhoClientOrServer = "Client";
        } else {
            WhoClientOrServer = "Server";
        }
        systemOfIncomingMessage.mainFunctionOfIncomingMessage();
    }


    private void tryFunctionToConnection() throws IOException, InterruptedException {
        do {
            server.serverWorking();
            if (server.isClosed()) {
                try {
                    client.clientWorking();
                } catch (SocketException e) {
                    server.serverWorking();
                }
            }

        }
        while (server.isClosed() && client.getClientSocket() == null);
    }

    public void updateMessage(String message) {
        notify(message);
    }

    public Server getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

    public InputMessage getInputMessage() {
        return systemOfIncomingMessage.getInputMessage();
    }

    @Override
    public void registerObserver(ObserverOfModelIncomingMessage o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(ObserverOfModelIncomingMessage o) {

    }

    @Override
    public void notify(String message) {
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfModelIncomingMessage observer = (ObserverOfModelIncomingMessage) observers.get(i);
            observer.updateModelIncomingMessage(message);
        }

    }
}
