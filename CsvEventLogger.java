import java.io.*;


public class CsvEventLogger {

    private GameEventLog log;
    private String filePath;

    public CsvEventLogger(String filepath){
        this.filePath = filepath;
        initializeCsvFile();
    }

    private void initializeCsvFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("case_id,player_id,activity,timestamp,category,question_value,content,answer,result,score_after_play");
            } catch (IOException e) {
                System.err.println("Error creating CSV file: " + e.getMessage());
            }
        }
    }

    public void updateLog(GameEventLog log){
        this.log = log;
        updateCsv();
    }

 

    private void updateCsv(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(log.getCaseId()).append(",");
            sb.append(log.getTimestamp()).append(",");
            sb.append(log.getActivity()).append(",");
            sb.append(log.getPlayerId() != null ? log.getPlayerId() : "").append(",");
            sb.append(log.getCategory() != null ? log.getCategory() : "").append(",");
            sb.append(log.getQuestionValue() > 0 ? log.getQuestionValue() : "").append(",");
            sb.append(log.getContent() != null ? "\"" + log.getContent().replace("\"", "\"\"") + "\"" : "").append(",");
            sb.append(log.getAnswer() != null ? "\"" + log.getAnswer().replace("\"", "\"\"") + "\"" : "");
            writer.println(sb.toString());
        } catch (IOException e) {
            System.err.println("Error updating CSV file: " + e.getMessage());
        }
    }



    
}
