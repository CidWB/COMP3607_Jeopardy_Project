import java.time.Instant;

public class AnswerQuestionAction implements Action {
    private Player player;
    private Question question;
    private String answer;

    public AnswerQuestionAction(Player player, Question question, String answer) {
        this.player = player;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public void execute() {

        if(question == null) {
            System.out.println("No question selected.");
            return;
        }       

        question.addAnswer(answer);


        // Create log
        GameEventLog log = new GameEventLog(Game.getInstance().getCaseId(),Instant.now().toString(),"answerQuestion");
            log.addPlayerInfo(player.getPlayerId());
            log.selectCategory(question.getCategory());
            log.selectValue(question.getValue());
            log.setContent(question.getContent());
            log.addAnswer(answer);

        player.getPublisher().setEventLog(log);

    }


    @Override
    public String toString() {
        return "answerQuestion";
    }
    
}
