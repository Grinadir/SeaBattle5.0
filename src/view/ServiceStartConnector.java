package view;

import javafx.concurrent.Task;

/**
 * Created by User on 18.06.2015.
 */
public class ServiceStartConnector extends Task {
    Object object;

    public ServiceStartConnector() {

    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    protected Object call() throws Exception {

        return object;
    }
}
