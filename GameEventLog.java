public class GameEventLog {

    private int caseId;
    private String playerId;
    private String activity;
    private String timestamp;
    private String category;
    private int questionValue;
    private String content;
    private String answer;
    private boolean isCorrect;
    private int newScore;


    //Accessors
    public int getCaseId() {
        return caseId;
    }   
    public String getPlayerId() {
        return playerId;
    }
    public String getActivity() {
        return activity;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getCategory() {
        return category;
    }
    public int getQuestionValue() {
        return questionValue;
    }
    public String getContent() {
        return content;
    }
    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    public int getNewScore() {
        return newScore;
    }
    


    public GameEventLog(int caseId, String timestamp, String activity) {
        this.caseId = caseId;
        this.timestamp = timestamp;
        this.activity = activity;
    }

    public void addPlayerInfo(String playerId) {
        this.playerId = playerId;
    }   

    public void selectCategory(String category) {
        this.category = category;
    }  

    public void selectValue(int questionValue) {
        this.questionValue = questionValue;
    }

    public void addAnswer(String answer){
        this.answer = answer;
    }

    public void updateScore(int score){
        this.newScore = score;
    }

        public void setContent(String content) {
        this.content = content;
    }

    public void setCorrect(boolean correct) {
        this.isCorrect = correct;
    }


}
