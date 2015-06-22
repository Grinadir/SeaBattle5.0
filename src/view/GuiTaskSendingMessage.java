package view;

import javafx.concurrent.Task;

/**
 * Created by User on 22.06.2015.
 */
public class GuiTaskSendingMessage extends Task {


    private String message;
    public GuiTaskSendingMessage(String message){
        this.message=message;
    }

    @Override
    protected Object call() throws Exception {
        return null;
    }

    public String takeMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
