import java.util.Objects;

public class Player{
	
  private final String name;
  private final int buttons;
  
  public Player(String name) {
    Objects.requireNonNull(name);
    this.name = name;
    this.buttons = 0;
  }
}

