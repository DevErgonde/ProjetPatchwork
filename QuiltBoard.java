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
	
	public int placeAtCoord(Coord coord) {
		if(grid[coord.x()][coord.y()] == false) {
			grid[coord.x()][coord.y()] = true;
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int placePiece(Piece piece, Coord origin) {
		var coordlist = piece.pieceCoordinates(origin);
		for( var c : coordlist) {
			if(grid[c.x()][c.y()] == false) {
				placeAtCoord(c);
			}
			else {
				return 1;
			}
		}
		return 0;
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
