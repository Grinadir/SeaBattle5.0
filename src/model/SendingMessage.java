package model;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import java.io.DataOutputStream;
import java.util.Date;

public class SendingMessage {


    private Date currentDate = new Date();
    private ClientServerConnector connector;
    private String sendingMessage;

    public SendingMessage(ClientServerConnector connector, String sendingMessage) {
        this.connector = connector;
        this.sendingMessage = sendingMessage;
    }


    public void call() throws Exception {
        System.out.print("call");
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
            //FIXME String message = String.format("%s (%s):%s", whoClientOrServer, sendingMessage);
            //FIXME updateMessage(message);
            out.writeUTF(sendingMessage);
            System.out.println("After outputAndUpdateMess " + sendingMessage);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
