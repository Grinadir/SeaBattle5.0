package runner;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by User on 20.06.2015.
 */
public class Runner extends Application {

    public static void main(String[] args) {
        Controller controller = new Controller();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
