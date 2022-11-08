public record Template(int buttons, int time, int[][] shape){
  
  public Template{
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
}