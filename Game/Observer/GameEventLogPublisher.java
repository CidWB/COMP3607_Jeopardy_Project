package Observer;
import java.util.ArrayList;

public class GameEventLogPublisher {
    private ArrayList<GameObserver> observers = new ArrayList<>();
    private GameEventLog log;

    public void addObserver(GameObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(GameObserver observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for (GameObserver gameObserver : observers) {
            gameObserver.update(this.log);
        }
    }

    public void setLog(GameEventLog log){
        this.log = log;
    }
}
