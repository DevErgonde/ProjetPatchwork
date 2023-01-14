import java.util.ArrayList;
import java.util.Objects;

public record Piece(int buttons, int time, boolean[][] shape){
  
  public Piece{
    if (buttons < 0) {
      throw new IllegalArgumentException("The buttons number of the template can't be negativ.");
    }
    if (time < 0) {
      throw new IllegalArgumentException("The time number of the template can't be negativ.");
    }
    if (shape.length == 0) {
      throw new IllegalArgumentException("The shape of the template can't be empty");
    }
  }
  
  public ArrayList<Coord> pieceCoordinates(Coord origin){
    Objects.requireNonNull(origin);
	  var coordlist = new ArrayList<Coord>();
	  for(var i = 0; i < shape.length; i++) {
		  for(var j = 0; j < shape[0].length; j++) {
			  if(shape[i][j] == true) {
				  var coord = new Coord(origin.x()+i, origin.y()+j);
				  coordlist.add(coord);
			  }
		  }
	  }
	 return coordlist;
  }
}