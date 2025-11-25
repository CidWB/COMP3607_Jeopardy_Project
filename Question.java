import java.util.ArrayList;
import java.util.List;

public class Question {

    private String category;
    private int value;
    private String content;
    private ArrayList<String> options;
    private String correctAnswer;
    private ArrayList<String> answers;
    private boolean isAnswered;

    //Accessors and Mutators
    public String getCategory() {
        return category;
    }   
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getContent() {
        return content;
    }
    public ArrayList<String> getOptions() {
        return options;
    }
    public String getRightAnswer() {
        return correctAnswer;
    }
    public ArrayList<String> getAnswers() {
        return answers;
    }
    public boolean isAnswered() {
        return isAnswered;
    }
    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
    public void addOption(String option) {
        options.add(option);
    }

    public Question(String category, int value, String content, List<String> options, String rightAnswer) {
        this.category = category;
        this.value = value;
        this.content = content;
        this.options = new ArrayList<>();
        this.correctAnswer = rightAnswer;
        this.answers = new ArrayList<>();
        this.isAnswered = false;
    }


    //Display question details
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Category: ").append(category).append("\n");
        sb.append("Value: ").append(value).append("\n");
        sb.append("Question: ").append(content).append("\n");
        if(options != null && !options.isEmpty())
            sb.append("Options: \n");
            for(int i = 0; i < options.size(); i++ ) {
                sb.append((i + 1)).append(". ").append(options.get(i)).append("\n");    
            }   

        return sb.toString();
    }  

    
    public void addAnswer(String answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
        }
    }
    
    
}
