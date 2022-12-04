import java.util.Objects;

/**
 * Class for the gestion of what the terminal displays
 */
public record Display() {
  
  /**
   * Clears the terminal
   */
  public static void clearScreen() {
    System.out.print("\u001b[2J");
    System.out.flush();
  }
  
  /**
   * Displays the actions menu
   */
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
  
  /**
   * Displays the current Chronoboard
   * @param game : Current game
   */
  public static void displayChronoBoard(Game game) {
    Objects.requireNonNull(game);
    clearScreen();
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
  
  /**
   * Displays player p's quilt board
   * @param p : Player associated with the quilt board
   */
  public static void displayGrid(Player p) {
    Objects.requireNonNull(p);
    clearScreen();
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
  
  /**
   * Displays the current list of pieces available
   * @param game : Current game
   */
  public static void displayPieces(Game game) {
    Objects.requireNonNull(game, "The Game object is null in displayPieces !");
    clearScreen();
    for (var i = 0; i < game.pieces().size(); i++) {
      System.out.println(Integer.toString(i + 1) + ". " + game.pieces().get(i).toString()); 
    } 
  }
  
  public static void displayPlayablePieces(Game game) {
    Objects.requireNonNull(game, "The Game object is null in displayPlayablePieces !");
    clearScreen();
    for (var i = 0; i < 3; i++) {
      System.out.println(Integer.toString(i + 1) + ". " + game.pieces().get(i).toString()); 
    } 
  }
}
