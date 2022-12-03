import java.util.Objects;

public record Display() {
  
  public static void displayChronoBoard(Game game) {
    Objects.requireNonNull(game);
    if (game.mode()) {
      
    }
    else {
      for(var i = 0; i <= 53; i++) {
        if (game.chronoBoard().buttons().contains(i)) {
          System.out.print("o ");
        }
        else if (game.chronoBoard().leatherPieces().contains(i)) {
            System.out.print("L ");
        }
        else if (game.p1().position() == i && game.p2().position() == i) {
              System.out.print("12 ");
        }
        else if (game.p1().position() == i) {
                    System.out.print("1 ");
        }
        else if (game.p2().position() == i) {
                      System.out.print("2 ");
        }
        else {
        	System.out.print("x ");
        }
      }
    }
  }
}
