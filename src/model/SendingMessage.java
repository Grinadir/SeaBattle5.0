package model;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import java.io.DataOutputStream;

public class SendingMessage {


    private ClientServerConnector connector;
    private String sendingMessage;

    public SendingMessage(ClientServerConnector connector, String sendingMessage) {
        this.connector = connector;
        this.sendingMessage = sendingMessage;
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
            System.out.println("outputAndUpdateMess " + sendingMessage);
            String message = String.format(sendingMessage);
            out.writeUTF(message);
            System.out.println("After outputAndUpdateMess " + message);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
