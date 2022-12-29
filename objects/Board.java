package objects;

import java.util.ArrayList;
import java.util.Stack;

public class Board {
  private int rows;
  private int columns;
  private ArrayList<Stack<Token>> grid;

  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.grid = new ArrayList<Stack<Token>>();
    for (int i = 0; i < columns; i++) {
      grid.add(new Stack<Token>());
    }
  }

  public void placeToken(Player player, int column) {
    if (grid.get(column).size() < rows) {
      Stack<Token> stack = grid.get(column);
      stack.push(player.generateToken());
    }
  }

  public boolean hasConnectFour(Player player) {
    // Check for horizontal, vertical and diagonal 
    // consecutive four same-colored tokens.
    for (int i = 0; i < this.columns; i++) {
      for (int j = 0; j < this.rows; j++) {
        if (this.hasHorizontalFour(j, i, player.getColor()) ||
            this.hasVerticalFour(j, i, player.getColor()) ||
            this.hasDiagonalFour(j, i, player.getColor())) {
          return true;    
        }
      }
    }
    return false;
  }

  private boolean hasHorizontalFour(int row, int column, Color color) {
    int consecutiveCount = 0;
    for (int i = column; i < this.columns; i++) {
      if (row < grid.get(i).size()) {
        if (grid.get(i).get(row).toString().equals(color.toString())) {
          consecutiveCount += 1;
        } else {
          if (consecutiveCount >= 4) {
            return true;
          } else {
            consecutiveCount = 0;
          }
        }
      }
    }
    return consecutiveCount >= 4;
  }
  
  private boolean hasVerticalFour(int row, int column, Color color) {
    Stack<Token> stack = grid.get(column);
    int consecutiveCount = 0;
    for (int i = row; i < this.rows; i++) {
      if (i < stack.size()) {
        if (stack.get(i).toString().equals(color.toString())) {
          consecutiveCount += 1;
        } else {
          consecutiveCount = 0;
        }
      }
    }
    return consecutiveCount >= 4;
  } 

  private boolean hasDiagonalFour(int row, int column, Color color) {
    return hasPositiveDiagonalFour(row, column, color) ||
           hasNegativeDiagonalFour(row, column, color);
  }

  private boolean hasPositiveDiagonalFour(int row, int column, Color color) {
    int consecutiveCount = 0;
    if ((row + 4 < this.rows) && (column + 4 < this.columns)) {
        int i = row;
        int j = column;
        while ((i < row + 4) && (j < column + 4)) {
            if (i < grid.get(j).size()) {
                if (grid.get(j).get(i).toString().equals(color.toString())) {
                    consecutiveCount += 1;
                } else {
                    consecutiveCount = 0;
                }
            }
            i += 1;
            j += 1;
        }
    }
    return consecutiveCount >= 4; 
  }

  private boolean hasNegativeDiagonalFour(int row, int column, Color color) {
    int consecutiveCount = 0;
    if ((row - 3 >= 0) && (column + 4 < this.columns)) {
        int i = row;
        int j = column;
        while ((i >= 0) && (j < column + 4)) {
            if (i < grid.get(j).size()) {
                if (grid.get(j).get(i).toString().equals(color.toString())) {
                    consecutiveCount += 1;
                } else {
                    consecutiveCount = 0;
                }
            }
            i -= 1;
            j += 1;
        }
    }
    return consecutiveCount >= 4;
  }

  private String boardStatus() {
    String output = "";
    if (grid.isEmpty()) {
        return "EMPTY";
    }
    for (int i = rows - 1; i >= 0; i--) {
        String row = "";
        for (int j = 0; j < columns; j++) {
            if (i < grid.get(j).size()) {
                Token t = grid.get(j).get(i);
                row += String.format("%-8s", t.toString());
            } else {
                row += String.format("%-8s", "");
            }
        }
        row += "\n";
        output += row;
    }
    return output;
  }

  private String columnDisplay() {
    String output = "";
    if (grid.isEmpty()) {
      return "";
    }
    for (int i = 0; i < rows; i++) {
      output += String.format("%-8d", i+1);
    }
    return output;
  }

  @Override
  public String toString() {
    return String.format(
      "Game Board:\n%s\n%s",
      this.columnDisplay(),
      this.boardStatus()
    );
  }
}
