package view;

/**
 * Created by User on 16.06.2015.
 */
public interface View {

    void onClick(AppearanceOfEvent click);

    void drawIt(boolean[][] state);

    void startListen();
}
