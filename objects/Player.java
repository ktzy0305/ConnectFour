package objects;

public class Player {
  private String name;
  private int playerNumber;
  private Color color;

  public Player(String name, int playerNumber, Color color) {
    this.name = name;
    this.playerNumber = playerNumber;
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public Token generateToken() {
    return new Token(this.color);
  }

  @Override
  public String toString() {
    return String.format("Player %d: %s (Color: %s)", 
        this.playerNumber, 
        this.name, 
        this.color);
  }
}
