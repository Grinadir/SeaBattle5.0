package view;

/**
 * Created by User on 29.06.2015.
 */
public interface ObservableGuiMyRectangle {

    void registerObserver(ObserverOfGuiMyRectangle o);

    void removeObserver(ObserverOfGuiMyRectangle o);

    void notifyCoordinate(int x, int y, String type);


}
