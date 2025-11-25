import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class PlayerList {

    private Queue<Player> players;
    private Player currentPlayer;

    public PlayerList(){
        this.players = new LinkedList<>();    
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void startTurn(QuestionList questions, Scanner scanner){

        if(players.isEmpty()){
            System.out.println("No players in the game.");
            return;
        }   
        currentPlayer = players.poll();
        System.out.println("It's " + currentPlayer.getPlayerId() + "'s turn.");
        System.out.println("Current Score: " + currentPlayer.getScore());

        System.out.println("Enter category:");
        String category = scanner.nextLine();
        System.out.println("Enter value:");
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ChooseQuestionAction chooseAction = new ChooseQuestionAction(currentPlayer, category, value, questions);

        currentPlayer.setAction(chooseAction);
        currentPlayer.doAction();


        Question selectedQuestion = chooseAction.getQuestion();
        if(selectedQuestion == null || selectedQuestion.isAnswered()) {
            players.add(currentPlayer); // Add the player back to the end of the queue
            return;
        }

        System.out.println("Question: " + selectedQuestion.getContent());

        System.out.println("Enter your answer:");
        String answer = scanner.nextLine();

        AnswerQuestionAction answerAction = new AnswerQuestionAction(currentPlayer, selectedQuestion, answer);
        currentPlayer.setAction(answerAction);
        currentPlayer.doAction();

    }

    public void endTurn(){
        if(currentPlayer != null){
            players.add(currentPlayer);
            currentPlayer = null;
        }
    }

    public Queue<Player> getPlayers() {
        return players;
    }   

    public Player getCurrentPlayer() {
        return currentPlayer;
    }






}
