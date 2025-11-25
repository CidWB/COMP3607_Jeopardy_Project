import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList; 

public class JsonFormatStrategy implements FileFormatStrategy {

    @Override
    public ArrayList<Question> loadQuestions(String filePath) {
        ArrayList<Question> questions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            
            // Simple JSON parsing (assuming a specific format)
            String jsonString = jsonContent.toString();
            jsonString = jsonString.replaceAll("[\\[\\]{}\"]", ""); // Remove brackets and quotes
            String[] questionEntries = jsonString.split("},");
            
            for (String entry : questionEntries) {
                String[] parts = entry.split(",");
                String category = "";
                int value = 0;
                String content = "";
                String correctAnswer = "";
                ArrayList<String> options = new ArrayList<>();
                
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    if (keyValue.length < 2) continue;
                    String key = keyValue[0].trim();
                    String valueStr = keyValue[1].trim();
                    
                    switch (key) {
                        case "category":
                            category = valueStr;
                            break;
                        case "value":
                            value = Integer.parseInt(valueStr);
                            break;
                        case "content":
                            content = valueStr;
                            break;
                        case "correctAnswer":
                            correctAnswer = valueStr;
                            break;
                        case "options":
                            String[] opts = valueStr.split(";");
                            for (String opt : opts) {
                                options.add(opt.trim());
                            }
                            break;
                    }
                }
                
                Question question = new Question(category, value, content, options, correctAnswer);
                questions.add(question);
            }
            
        } catch (IOException e) {
            System.err.println("Error loading JSON file: " + e.getMessage());
        }
        
        return questions;
    }
    
}
