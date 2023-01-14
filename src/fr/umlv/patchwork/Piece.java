package src.fr.umlv.patchwork;
import java.util.ArrayList;
import java.util.Objects;

public record Piece(int price, int time, int buttons, boolean[][] shape){
  
  public Piece{
    if (buttons < 0) {
      throw new IllegalArgumentException("The buttons number of the template can't be negativ.");
    }
    if (time < 0) {
      throw new IllegalArgumentException("The time number of the template can't be negativ.");
    }
    if (price < 0) {
        throw new IllegalArgumentException("The button price of the template can't be negativ.");
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
  
  @Override
  public String toString() {
    var stringbuilder = new StringBuilder();
    for (var i = 0; i < shape.length; i++) {
      for(var j = 0; j < shape[0].length; j++) {
        if(shape[i][j] == true) 
          stringbuilder.append("O");
        else
          stringbuilder.append(" ");
      }
      stringbuilder.append("\n");
    }
    return buttons + " buttons, " + time + " time\n" + stringbuilder.toString() + "------\n";
  }
}
