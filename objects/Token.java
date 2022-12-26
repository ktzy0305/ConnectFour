package objects;

public class Token {
  private Color color;

  public Token(Color color) {
    this.color = color;  
  }

  @Override
  public String toString() {
    return color.name();
  }
}
