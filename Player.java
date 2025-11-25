public class Player {

    private String playerId;
    private int score;
    private Action action;
    private GameEventLogPublisher publisher;

    //Accessors and Mutators
    public String getPlayerId() {
        return playerId;
    }       
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public GameEventLogPublisher getPublisher() {
        return publisher;
    }

    
    
    public void doAction() {
        if(action != null) {
            action.execute();
        }
    }   

    public Player(String playerId, GameEventLogPublisher publisher) {
        this.playerId = playerId;
        this.score = 0;
        this.publisher = publisher;
    }


    
}
