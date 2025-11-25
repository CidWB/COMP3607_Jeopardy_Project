import java.util.ArrayList;
import java.util.Scanner;   


public class GameConfig {

    private FileFormatStrategy fileFormatStrategy;
    private QuestionList questions;
    private PlayerList players;


    public QuestionList getQuestions() {
        return questions;
    }
    public PlayerList getPlayers() {
        return players;
    }   



    public GameConfig(){
        this.players = new PlayerList();
        this.questions = new QuestionList();
    }

    public void getFile(String fileFormat, String filePath) {
        switch (fileFormat.toLowerCase()) {
            case "csv":
                fileFormatStrategy = new CsvFormatStrategy();
                break;
            case "json":
                fileFormatStrategy = new JsonFormatStrategy();
                break;
            case "xml":
                fileFormatStrategy = new XmlFormatStrategy();
                break;
            default:
                System.out.println("Unsupported file format!");
                return;
        }
        
        loadQuestions(filePath);
    }

    public void loadQuestions(String filePath){

        if(fileFormatStrategy == null){
            System.out.println("File format strategy not set.");
            return;
        }

        ArrayList<Question> loadedQuestions = fileFormatStrategy.loadQuestions(filePath);

        for(Question q : loadedQuestions){
            questions.addQuestion(q);
        }

        System.out.println("Loaded " + loadedQuestions.size() + " questions from " + filePath);

    }

    public void addPlayers(int numPlayers, GameEventLogPublisher publisher){
        Scanner scanner = new Scanner(System.in);
        for(int i = 1; i <= numPlayers; i++){

            System.out.println("Enter name for Player " + i + ":");
            String playerName = scanner.nextLine();

            Player player = new Player(playerName, publisher);
            players.addPlayer(player);
        }

        System.out.println("Added " + numPlayers + " players to the game.");

    }

    
}
