import java.time.Instant;

public class ChooseQuestionAction implements Action {
    
    private Player player;
    private String category;
    private int value;
    private Question question;
    private QuestionList questions;

    public ChooseQuestionAction(Player player, String category, int value, QuestionList questions) {
        this.player = player;
        this.category = category;
        this.value = value;
        this.questions = questions;
        //this.question = questions.searchQuestion(category, value);
    }



    @Override
    public void execute() {

        question = questions.searchQuestion(category, value);

        if(question == null) {
            System.out.println("Question not found for category: " + category + " and value: " + value);
            return;
        }   
        if(question.isAnswered() || question.getValue() == 0) {
            System.out.println("Question already answered or invalid value.");
            return;
        }

        // Create log
        GameEventLog eventLog = new GameEventLog(Game.getInstance().getCaseId(),Instant.now().toString(),"chooseQuestion");
            eventLog.addPlayerInfo(player.getPlayerId());
            eventLog.selectCategory(question.getCategory());
            eventLog.selectValue(question.getValue());
            eventLog.setContent(question.getContent());


        player.getPublisher().setEventLog(eventLog);

        //System.out.println(player.getPlayerId() + " has chosen the question: " + question.toString());
    
    }

    public Question getQuestion() {
        return question;
    }   


    @Override
    public String toString() {
        return "chooseQuestion";
        //return "ChooseQuestionAction by " + player.getPlayerId() + " for question: " + question.toString();
    }
    
}
