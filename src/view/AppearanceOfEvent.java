package view;

/**
 * Created by User on 16.06.2015.
 */
public interface AppearanceOfEvent {

    void onAppearance(EventGuiOn event);

    void drawIt(boolean[][] state);

    void startListen();

    void startConnect();
}
