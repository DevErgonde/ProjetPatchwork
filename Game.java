public class Game{
  
  private final Player p1;
  private final Player p2;
  
  public Game(String name1, String name2) {
    p1 = new Player(name1);
    p2 = new Player(name2);
  }
  
}