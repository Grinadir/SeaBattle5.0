package model;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class SendingMessage {


    private Date currentDate = new Date();
    private ClientServerConnector connector;
    private String sendingMessage;

    public SendingMessage( ClientServerConnector connector) {
        this.connector = connector;
    }



    public void call() throws Exception {
        mainFunctionOutputMessage();

    }

    private void mainFunctionOutputMessage() {
        if (connector.getServer().isClosed()) {
            DataOutputStream out = new DataOutputStream(connector.getClient().getOutputClientStream());
            outputAndUpdateMess(out, "Client");
        } else {
            DataOutputStream out = new DataOutputStream(connector.getServer().getOutputServerStream());
            outputAndUpdateMess(out, "Server");
        }
    }

    private void outputAndUpdateMess(DataOutputStream out, String whoClientOrServer) {
        try {
            String message = String.format("%s (%s):%s", whoClientOrServer, sendingMessage);
            //updateMessage(message);
            out.writeUTF(message);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
