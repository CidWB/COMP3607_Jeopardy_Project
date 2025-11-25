import java.util.Scanner;

public class Game {

    private static Game instance;
    private int caseId;
    private GameConfig config;
    private QuestionList questions;
    private PlayerList players;
    private GameEventLogPublisher publisher;
    private Scanner scanner;
    private boolean isRunning;

    private Game() {
        this.caseId = 1;
        this.scanner = new Scanner(System.in);
        this.isRunning = false;
        this.publisher = new GameEventLogPublisher();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void setupGame(GameConfig config) {
        this.config = config;
        this.questions = config.getQuestions();
        this.players = config.getPlayers();
        
        // Setup observers
        OutputEventLogger outputLogger = new OutputEventLogger();
        CsvEventLogger csvLogger = new CsvEventLogger("game_log.csv");
        
        publisher.addObserver(outputLogger);
        //publisher.addObserver(csvLogger);
        
        System.out.println("\nGame setup complete!");
    }

    public void playGame() {
        if (questions == null || players == null) {
            System.out.println("Game not properly configured!");
            return;
        }

        isRunning = true;
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GAME START!");
        System.out.println("Type 'QUIT' at any category prompt to end the game");
        System.out.println("=".repeat(60));

        while (isRunning && questions.checkHasQuestions()) {
            questions.display();
            
            // Check if there's a question to answer
            if (!questions.checkHasQuestions()) {
                System.out.println("\nNo more questions available!");
                break;
            }

            // Player's turn
            System.out.print("\nPress Enter to continue or type QUIT to end game: ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("QUIT")) {
                quitGame();
                break;
            }

            players.startTurn(questions, scanner);
            players.endTurn();
        }

        if (isRunning) {
            quitGame();
        }
    }

    public void quitGame() {
        isRunning = false;
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GAME OVER - FINAL SCORES");
        System.out.println("=".repeat(60));

        // Display final scores
        for (Player player : players.getPlayers()) {
            System.out.println(player.getPlayerId() + ": " + player.getScore() + " points");
        }
        
        if (players.getCurrentPlayer() != null) {
            Player current = players.getCurrentPlayer();
            System.out.println(current.getPlayerId() + ": " + current.getScore() + " points");
        }

        System.out.println("=".repeat(60));
        System.out.println("Game log saved to game_log.csv");
    }

    public int getCaseId() {
        return caseId;
    }

    public void incrementCaseId() {
        this.caseId++;
    }


    
}
