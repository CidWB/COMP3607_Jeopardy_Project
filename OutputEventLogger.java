public class OutputEventLogger implements GameObserver {

    private GameEventLog log;


    @Override
    public void update(GameEventLog eventLog) {
        this.log = eventLog;
        displayLog();
    }

    public void displayLog() {

        if(log == null) {
            System.out.println("No event log to display.");
            return;
        }   

        System.out.println("----- Game Event Log -----");
        System.out.println("Case ID: " + log.getCaseId());
        System.out.println("Timestamp: " + log.getTimestamp());
        System.out.println("Activity: " + log.getActivity());

        if (log.getCategory() != null) {
            System.out.println("Category: " + log.getCategory());
        }
        
        if (log.getQuestionValue() > 0) {
            System.out.println("Value: " + log.getQuestionValue());
        }
        
        if (log.getContent() != null) {
            System.out.println("Question: " + log.getContent());
        }
        
        if (log.getAnswer() != null) {
            System.out.println("Answer: " + log.getAnswer());
            System.out.println("Correct: " + log.isCorrect());
        }
        
        if (log.getNewScore() >= 0) {
            System.out.println("Score After: " + log.getNewScore());
        }

        System.out.println("--------------------------");

    }



}

