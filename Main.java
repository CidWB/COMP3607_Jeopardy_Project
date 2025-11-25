import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Jeopardy Game!");

        System.out.print("Enter question file format (csv, json, xml): ");
        String fileFormat = scanner.nextLine();

        System.out.print("Enter question file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        GameConfig config = new GameConfig();
        config.getFile(fileFormat, filePath);

        GameEventLogPublisher publisher = new GameEventLogPublisher();
        config.addPlayers(numPlayers, publisher);

        Game game = Game.getInstance();
        game.setupGame(config);

        game.playGame();

        scanner.close();

        System.out.println("Thank you for playing!");
        System.out.println("Goodbye!");
        


    }

    
}
