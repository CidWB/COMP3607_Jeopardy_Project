import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFormatStrategy implements FileFormatStrategy {
    
    @Override
    public ArrayList<Question> loadQuestions(String filePath) {
        ArrayList<Question> questions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Handle quoted commas
                
                if (parts.length >= 4) {
                    String category = parts[0].trim().replace("\"", "");
                    int value = Integer.parseInt(parts[1].trim());
                    String content = parts[2].trim().replace("\"", "");
                    String correctAnswer = parts[3].trim().replace("\"", "");
                    
                    Question question = new Question(category, value, content, null, correctAnswer);
                    
                    // Add options if present
                    for (int i = 4; i < parts.length; i++) {
                        String option = parts[i].trim().replace("\"", "");
                        if (!option.isEmpty()) {
                            question.addOption(option);
                        }
                    }
                    
                    questions.add(question);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading CSV file: " + e.getMessage());
        }
        
        return questions;
    }
}