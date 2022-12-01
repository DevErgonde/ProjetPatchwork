import java.util.ArrayList;
import java.util.List;

public class Board{
  
  private final List<Integer> leatherPieces;
  private final List<Integer> buttons;
  
  //
  // Constructeurs
  //
  
  public Board(int phase){
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    this.leatherPieces = generateLeatherPieces();
    this.buttons = generateButtons();
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  private static List<Integer> generateLeatherPieces(){
    return new ArrayList<Integer>(List.of(26, 32, 38, 44, 50));
  }
  
  private static List<Integer> generateButtons(){
    return List.of(5, 11, 17, 23, 29, 35, 41, 47, 53);
  }
  
  //
  // Méthodes
  //
  
  //
  // Accesseurs
  //
  
  public List<Integer> leatherPieces(){
    return leatherPieces;
  }
  
  public List<Integer> buttons(){
    return buttons;
  }
}