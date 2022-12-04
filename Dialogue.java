import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Class util to dialogue with humans
 * Uses a Scanner to read the input
 * 
 */
public record Dialogue(Scanner scan) {
  
  /**
   * Retrieve the next int on the scan input, write an error message and retry the scan otherwise
   * 
   * @return int writed on the input
   */
  public int scanInt() {
    try {
      return scan.nextInt();
    }catch(InputMismatchException e) {
      e.getMessage();
      System.out.println("Veuillez entrer un entier.");
      return scanInt();
    }
  }
  
  /**
   * Retrieve the boolean on the scan input, write an error message and retry the scan otherwise
   * 
   * @return boolean writed on the input
   */
  public boolean scanBoolean() {
    try {
      return scan.nextBoolean();
    }catch(InputMismatchException e) {
      e.getMessage();
      System.out.println("Veuillez entrer un booléen.");
      return scanBoolean();
    }
  }
  
  /**
   * Ask for a choice to the players
   * Retrieve the choice given on the scan input. The choice is an int of range 1 to 6 included
   * 
   * @return int of range 1 to 6 included
   */
  public int askActionChoices() {
    int choice;
    do {
      System.out.println("Veuillez entrer un entier valable (entre 1 et 6).");
      choice = scanInt();
    }
    while (choice < 1 || choice > 6);
    return choice;
  }
  
  /**
   * Ask a piece to the players
   * Retrieve the piece given on the scan input. The choice is an int of range 1 to 3 included
   * 
   * @return int of range 1 to 3 included
   */
  public int askPieceChoice() {
    int choice;
    do {
      System.out.println("Veuillez choisir une pièce de la liste en fonction de son numéro.");
      System.out.println("Vous ne pouvez que choisir parmis les 3 premières pièces\n, donc votre choix être compris entre 1 et 3.");
      choice = scanInt();
    }while(choice < 1 || choice > 3);
    return choice;
  }
  
  /**
   * Ask the piece orientation to the players
   * Retrieve the piece orientation given on the scan input. The choice is an int of range 0 to 3 included
   * 
   * @return int of range 0 to 3 included
   */
  public int askPieceOrientation() {
    int choice;
    do {
      System.out.println("Si vous voulez faire tourner la pièce (dans le sens des aiguilles d'une montre)\n"
          + ", entrez le nombre de rotations voulu. (0 à 3).");
      choice = scanInt();
    }while(choice < 0 || choice > 3);
    return choice;
  }
  
  /**
   * Ask the player if they want to reverse the piece
   * Retrieve the choice of a player by a boolean
   * 
   * @return boolean - answer of the player
   */
  public boolean askPieceReverse() {
    System.out.println("Si vous voulez effectuer un effet miroir sur la pièce, entrez true. Sinon entrez false.");
    return scanBoolean();
  }
