package controller;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import model.Engine;
import net.ClientServerConnector;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/*
 * нужен для того, чтобы передовать координаты атаки
 */

public class SendingTargetCoordinate {

    private ClientServerConnector connector;
    private Engine engine;
    private Date currentDate = new Date();

    public SendingTargetCoordinate(Engine engine, ClientServerConnector connector) {
        this.engine = engine;
        this.connector = connector;
    }

    public void call() {
        if (connector.getServer().isClosed()) {
            sendStrikeCoordinateTo("client", connector.getClient().getOutputClientStream());
        } else {
            sendStrikeCoordinateTo("server", connector.getServer().getOutputServerStream());
        }
    }

    private void sendStrikeCoordinateTo(String who, OutputStream outputStream) {
        DataOutputStream outClient = new DataOutputStream(outputStream);
        System.out.println("isFollowStep " + engine.getStatus().isFollowStep());
        if (engine.getStatus().isFollowStep() && engine.getStatus().isReady()) {
            sendStrikeCoordinate(outClient, who);
        }
        sendStrikeCoordinate(outClient, who);
    }

    private void sendStrikeCoordinate(DataOutputStream out, String s) {
        try {
            int x = engine.getMap().getCoordOfAttackX();
            int y = engine.getMap().getCoordOfAttackY();
            String reportResultOfAttack = String.format("#attack of  %s (I AM) (%s) attacked coordinates: $%d%%%d*;",
                    s, currentDate, x, y);
            out.writeUTF(reportResultOfAttack);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}

