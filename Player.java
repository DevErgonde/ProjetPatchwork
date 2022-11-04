import java.util.Objects;

public class Player{
	
  private final String name;
  
  public Player(String name) {
    Objects.requireNonNull(name);
    this.name = name;
  }
}

