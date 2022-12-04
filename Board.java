import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Element {
  PAWN1, PAWN2, PAWNS, LEATHER, BUTTON
}

public class Board{
  
  private final Map<Integer, Element> boardElements;
  private int Pawn1position;
  private int Pawn2position;
  
  //
  // Constructeurs
  //
  
  public Board(int phase){
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    
    boardElements = new HashMap<Integer, Element>();
    for (var i : List.of(26, 32, 38, 44, 50)) {
      boardElements.put(i, Element.LEATHER);
    }
    
    for (var i : List.of(5, 11, 17, 23, 29, 35, 41, 47, 53)) {
      boardElements.put(i, Element.BUTTON);
    }
    
    boardElements.put(0, Element.PAWNS);
    
    Pawn1position = 0;
    Pawn2position = 0;
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  //
  // Méthodes
  //
  
  public void movePawn(int distance, Element pawn) {
	  if(pawn != Element.PAWN1 && pawn != Element.PAWN2) {
		  throw new IllegalArgumentException("Only a pawn can be moved");
	  }
	  switch(pawn) {
	  	case PAWN1:
	  		boardElements.remove(Pawn1position);
	  		if(Pawn1position == Pawn2position) {
	  			boardElements.put(Pawn1position,Element.PAWN2);
	  		}
	  		Pawn1position += distance;
	  		if(Pawn1position == Pawn2position) {
	  			boardElements.remove(Pawn1position);
	  			boardElements.put(Pawn1position, Element.PAWNS);
	  		}
	  		else {
	  			boardElements.put(Pawn1position, pawn);
	  		}
	  		break;
	  		
	  	case PAWN2:
	  		boardElements.remove(Pawn2position);
	  		if(Pawn1position == Pawn2position) {
	  			boardElements.put(Pawn1position,Element.PAWN1);
	  		}
	  		Pawn2position += distance;
	  		if(Pawn1position == Pawn2position) {
	  			boardElements.remove(Pawn1position);
	  			boardElements.put(Pawn1position, Element.PAWNS);
	  		}
	  		else {
	  			boardElements.put(Pawn2position, pawn);
	  		}
	  		break;
	  	default:
	  		break;
	  }
  }
  
  //
  // Accesseurs
  //
  
  public Map<Integer, Element> boardElements(){
    return boardElements;
  }
}
