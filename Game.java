import java.util.Scanner;
import objects.Board;
import objects.Color;
import objects.Player;

public class Game {    
  private static Board board = new Board(6, 7);
  private static Player player1 = null;
  private static Player player2 = null;
  private static boolean gameIsRunning = true;
  private static int turn = 1;

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to Connect Four!\n");
      System.out.print("Enter Player 1 Name: ");
      player1 = new Player(scanner.nextLine(), 1, Color.RED);
      System.out.print("Enter Player 2 Name: ");
      player2 = new Player(scanner.nextLine(), 2, Color.YELLOW);

      while(gameIsRunning) {
          Player player = (turn % 2 == 1) ? player1 : player2;
          System.out.println(board.toString());
          System.out.println(String.format("%s Turn", player.toString()));
          boolean inputIsValid = false;
          while(!inputIsValid) {
              System.out.print("Please enter the column (1 - 6) to place your token: ");
              int column = scanner.nextInt();
              if (column >= 1 && column <= 6) {
                  inputIsValid = true;
                  board.placeToken(player, column - 1);
              } else {
                  System.out.println("You have entered an invalid input.");
              }
          }
          if (board.hasConnectFour(player)) {
              gameIsRunning = false;
              System.out.println(board.toString());
              System.out.println(String.format("The winner is %s!", player));
              scanner.close();
          } else {
              turn += 1;
          }
      }
  }
}
