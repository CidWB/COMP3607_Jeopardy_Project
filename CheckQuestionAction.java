import java.time.Instant;   

public class CheckQuestionAction implements Action {
    
    private Player player;
    private Question question;
    private String answer;

    public CheckQuestionAction(Player player, Question question, String answer) {
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

        boolean isCorrect = answer.equalsIgnoreCase(question.getRightAnswer());

        if(isCorrect){
            player.setScore(player.getScore() + question.getValue());
            question.setAnswered(true);
            question.setValue(0);
        }

        // Create log
        GameEventLog log = new GameEventLog(Game.getInstance().getCaseId(),Instant.now().toString(),"checkAnswer");
            log.addPlayerInfo(player.getPlayerId());
            log.selectCategory(question.getCategory());
            log.selectValue(question.getValue());
            log.setContent(question.getContent());
            log.addAnswer(answer);
            log.setCorrect(isCorrect);
            log.updateScore(player.getScore());


        player.getPublisher().setEventLog(log);



        if(isCorrect) {
            System.out.println("Correct answer! " + player.getPlayerId() + "'s new score is: " + player.getScore());
        } else {
            System.out.println("Incorrect answer. The correct answer was: " + question.getRightAnswer() + ". " + player.getPlayerId() + "'s score remains: " + player.getScore());
        }

        System.out.println("Current Score: " + player.getScore());

    }

    @Override
    public String toString() {
        return "checkAnswer";
    }



}
