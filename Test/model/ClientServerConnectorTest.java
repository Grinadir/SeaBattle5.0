package model;

import controller.TaskClientServerConnector;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.ClientServerConnector;
import net.SendingMessage;
import net.SystemOfIncomingMessage;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 03.10.2015.
 */
public class ClientServerConnectorTest {
    @Test
    public void testConnector() throws Exception {
        ClientServerConnector c1 = new ClientServerConnector();
        ClientServerConnector c2 = new ClientServerConnector();

        //SystemOfIncomingMessage sm2 = new SystemOfIncomingMessage(c2);


        TaskClientServerConnector taskC1 = new TaskClientServerConnector(c1);
        TaskClientServerConnector taskC2 = new TaskClientServerConnector(c2);

        Service service1 = new Service<Void>()  {
            @Override
            protected Task<Void> createTask() {
                return taskC1;
            }

        };
        try {
            service1.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(1);

        Service service2 = new Service<Void>() {


            @Override
            protected Task<Void> createTask() {
                return taskC2;
            }

        };
        try {
            service2.start();
        }catch (Exception e){
            e.printStackTrace();
        }


        new SendingMessage(c1, "TEST");
        assertEquals(c2.getInputMessage().getStrForTest(), "TEST");


    }

}
