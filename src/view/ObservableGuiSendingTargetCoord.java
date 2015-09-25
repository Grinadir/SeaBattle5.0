package view;

/**
 * Created by User on 22.09.2015.
 */
public interface ObservableGuiSendingTargetCoord {

    void registerObserver(ObserverOfGuiSendingTargetCoord o);

    void removeObserver(ObserverOfGuiSendingTargetCoord o);

    void notifySendingTarget(String message);

}
