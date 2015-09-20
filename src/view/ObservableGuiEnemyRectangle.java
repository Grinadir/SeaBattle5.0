package view;

/**
 * Created by User on 01.07.2015.
 */
public interface ObservableGuiEnemyRectangle {

    void registerObserver(ObserverOfGuiEnemyRectangle o);

    void removeObserver(ObserverOfGuiEnemyRectangle o);

    void notifyCoordinate(int x, int y);
}
