import java.util.Objects;

public record Display() {
  
  public static void displayChronoBoard(Game game) {
    Objects.requireNonNull(game);
    if (game.mode()) {
      
    }
    else {
      for(var i = 0; i <= 53; i++) {
        switch(i) {
          case game.p1().position():
            System.out.print("1");
            break;
          case game.p2().position():
            System.out.print("2");
            break;
          default:
            if (game.chronoBoard().leatherPieces().contains(i)) {
              System.out.print("L ");
            }
            else {
              if (game.chronoBoard().buttons().contains(i)) {
                System.out.print("o ");
              }
            }
        }
      }
    }
  }
}