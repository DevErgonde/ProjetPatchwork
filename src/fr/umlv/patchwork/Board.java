package src.fr.umlv.patchwork;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Board{
  
  private final List<Element> boardElements;
  
  //
  // Constructeurs
  //
  
  public Board(int phase){
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    boardElements = generateBoardElements();
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  private static List<Element> generateBoardElements(){
    var boardElements = new LinkedList<Element>();
    for (var i = 0; i <= 53; i++) {
    	if (i >= 26 && (i-26)%6 == 0) {
    		boardElements.add(Element.LEATHER);
    	}
    	
    	if (i >=5 && (i-5)%6 == 0) {
    		boardElements.add(Element.BUTTON);
    	}
    	
    	if (boardElements.size() == i) {
    		boardElements.add(Element.VOID);
    	}
    }
    return boardElements;
  }
  
  //
  // Méthodes
  //
  
  //
  // Accesseurs
  //
  
  public List<Element> boardElements(){
    return boardElements;
  }
}
