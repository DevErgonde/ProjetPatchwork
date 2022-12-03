public class QuiltBoard {
	public final int player;
	public final boolean[][] grid;

	public QuiltBoard(int player) {
		if(player < 1 || player > 2) {
			throw new IllegalArgumentException("player number invalid");
		}
		this.player = player;
		this.grid = new boolean[9][9];
	}
	
	public void placeAtCoord(Coord coord) {
		grid[coord.x()][coord.y()] = true;
	}
	
	public void placePiece(Piece piece, Coord origin) {
		var coordlist = piece.pieceCoordinates(origin);
		for( var c : coordlist) {
			placeAtCoord(c);
		}
	}
	
	public int BlankSpaces() {
		var count = 0;
		for(var tab : grid) {
			for(var c : tab) {
				if(c == false) {count++;}
			}
		}
		return count;
	}
}
