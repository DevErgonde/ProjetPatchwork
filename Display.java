import java.io.IOException;
import java.util.Objects;

public record Display() {
  
  public static void clearScreen() {
    System.out.print("\u001b[2J");
    System.out.flush();
  }
  
  public static void displayActionsChoices() {
    clearScreen();
    System.out.println("********************************************");
    System.out.println("*             MENU DES ACTIONS             *");
    System.out.println("********************************************");
    System.out.println("* 1. Prends et place une pièce de tissu.   *");
    System.out.println("* 2. Avance et reçois des boutons.         *");
    System.out.println("* 3. Affiche la liste des pièces de tissu. *");
    System.out.println("* 4. Affiche le plateau de jeu principal.  *");
    System.out.println("* 5. Affiche le Patchwork du joueur 1.     *");
    System.out.println("* 6. Affiche le Patchwork du joueur 2.     *");
    System.out.println("********************************************");
  }
  
  public static void displayChronoBoard(Game game) {
    Objects.requireNonNull(game);
    for(var i = 0; i <= 53; i++) {
      
      var element = game.chronoBoard().boardElements().get(i);
      
      if (i % 10 == 0 && i != 0) System.out.println("");
      
      if (element == null) System.out.print("..");
      
      else {
        switch(element) {
          case LEATHER:
            System.out.print("L "); break;           
          
          case BUTTON:
            System.out.print("B "); break;
          
          case PAWNS:
            System.out.print("12"); break;
            
          case PAWN1:
            System.out.print("1 "); break;
            
          case PAWN2:
            System.out.print("2 "); break;
        }
      }
    }
  }
}
