import java.util.ArrayList;

public class GameEventLogPublisher {
    
    private ArrayList<GameObserver> observers;
    private GameEventLog eventLog;

    public GameEventLogPublisher() {
        this.observers = new ArrayList<>();
    }   

    public void addObserver(OutputEventLogger outputLogger) {
        observers.add(outputLogger);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(eventLog);
        }
    }

    public void setEventLog(GameEventLog eventLog) {
        this.eventLog = eventLog;
        notifyObservers();
    }

    public GameEventLog getEventLog() {
        return eventLog;
    }   


}
