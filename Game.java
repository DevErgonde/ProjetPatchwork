import java.util.ArrayList;
import java.util.Objects;

public class Game{
  
  private final Player p1;
  private final Player p2;
  private final ArrayList<Template> templates;
  
  public Game(String name1, String name2) {
    Objects.requireNonNull(name1);
    Objects.requireNonNull(name2);
    p1 = new Player(name1);
    p2 = new Player(name2);
    templates = new ArrayList<Template>();
  }
  
}