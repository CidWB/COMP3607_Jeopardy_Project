import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class QuestionList {
    private ArrayList<Question> questions;
    private boolean hasQuestions;

    public QuestionList() {
        this.questions = new ArrayList<>();
        this.hasQuestions = false;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        hasQuestions = true;
    }

    public void display() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }

        // Group questions by category
        Map<String, TreeSet<Integer>> categoryMap = new HashMap<>();
        for (Question q : questions) {
            categoryMap.putIfAbsent(q.getCategory(), new TreeSet<>());
            categoryMap.get(q.getCategory()).add(q.getValue());
        }

        // Display header
        System.out.println("\n" + "=".repeat(60));
        System.out.println("QUESTION BOARD");
        System.out.println("=".repeat(60));

        // Display categories and values
        for (Map.Entry<String, TreeSet<Integer>> entry : categoryMap.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            System.out.print("  Values: ");
            for (Integer value : entry.getValue()) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("=".repeat(60) + "\n");
    }

    public Question searchQuestion(String category, int value) {
        for (Question q : questions) {
            if (q.getCategory().equalsIgnoreCase(category) && q.getValue() == value) {
                return q;
            }
        }
        return null;
    }

    public boolean checkHasQuestions() {
        hasQuestions = false;
        for (Question q : questions) {
            if (q.getValue() > 0 && !q.isAnswered()) {
                hasQuestions = true;
                break;
            }
        }
        return hasQuestions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    
}