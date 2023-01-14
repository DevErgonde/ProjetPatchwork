package src.fr.umlv.patchwork;
import java.util.Objects;

public record Display() {
  
  public static void displayActionsChoices() {
    System.out.println("""
        ********************************************
        *             MENU DES ACTIONS             *
        ********************************************
        * 1. Prends et place une pièce de tissu.   *
        * 2. Avance et reçois des boutons.         *
        * 3. Affiche la liste des pièces de tissu. *
        * 4. Affiche le plateau de jeu principal.  *
        * 5. Affiche le Patchwork du joueur 1.     *
        * 6. Affiche le Patchwork du joueur 2.     *
        * 7. Affiche les infos du joueur actuel.   *
        ********************************************
        """);
  }
  
  public static void displayChronoBoard(Game game) {
    Objects.requireNonNull(game);
    for(var i = 0; i <= 53; i++) {
      
      var element = game.chronoBoard().boardElements().get(i);
      
      if (i % 10 == 0 && i != 0) System.out.println("");
      
        switch(element) {
          case LEATHER:
            System.out.print("L "); break;           
          
          case BUTTON:
            System.out.print("B "); break;
            
          case VOID:
            System.out.print(".."); break;
        }
        
        if (game.p1().position == i && game.p2().position == i) {
          System.out.print("12");
        }
        else {
          if (game.p1().position == i)
            System.out.print("1 ");
          if (game.p2().position == i)
            System.out.print("2 ");
        }
    }
  }
  
  public static void displayGrid(Player p) {
    Objects.requireNonNull(p);
    for(var ligne : p.grid()) {
      for (var cases : ligne) {
        if (cases)
          System.out.print("O");
        else
          System.out.print(".");
      }
      System.out.println("");
    }
  }
  
  public static void displayInfoPlayer(Player p) {
    Objects.requireNonNull(p);
    System.out.println(p);
  }
  
  public static void displayPieces(Game game) {
    Objects.requireNonNull(game, "The Game object is null in displayPieces !");
    for (var i = 0; i < game.pieces().size(); i++) {
      System.out.println(Integer.toString(i + 1) + ". " + game.pieces().get(i).toString()); 
    } 
  }
  
  public static void displayPlayablePieces(Game game) {
    Objects.requireNonNull(game, "The Game object is null in displayPlayablePieces !");
    for (var i = 0; i < 3; i++) {
      System.out.println(Integer.toString(i + 1) + ". " + game.pieces().get(i).toString()); 
    } 
  }
}
