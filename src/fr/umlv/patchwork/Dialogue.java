package src.fr.umlv.patchwork;
import java.util.InputMismatchException;
import java.util.Scanner;

public record Dialogue(Scanner scan) {
  
  public int scanInt() {
    try {
      return scan.nextInt();
    }catch(InputMismatchException e) {
      e.getMessage();
      System.out.println("Veuillez entrer un entier.");
      return scanInt();
    }
  }
  
  public boolean scanBoolean() {
    try {
      return scan.nextBoolean();
    }catch(InputMismatchException e) {
      e.getMessage();
      System.out.println("Veuillez entrer un booléen.");
      return scanBoolean();
    }
  }
  
  public int askActionChoices() {
    int choice;
    do {
      System.out.println("Veuillez entrer un entier valable (entre 1 et 6).");
      choice = scanInt();
    }
    while (choice < 1 || choice > 7);
    return choice;
  }
  
  public int askPieceChoice() {
    int choice;
    do {
      System.out.println("Veuillez choisir une pièce de la liste en fonction de son numéro.");
      System.out.println("Vous ne pouvez que choisir parmis les 3 premières pièces\n, donc votre choix être compris entre 1 et 3.");
      choice = scanInt();
    }while(choice < 1 || choice > 3);
    return choice;
  }
  
  public Coord askPieceCoord() { 
    System.out.println("Veuillez entrer les coordonnées en haut à gauche d'où vous voulez placer la pièce.");
    System.out.println("Sous cette forme : x y");
    return new Coord(scanInt(), scanInt());
  }
  
  public int askPieceOrientation() {
    int choice;
    do {
      System.out.println("Si vous voulez faire tourner la pièce (dans le sens des aiguilles d'une montre)\n"
          + ", entrez le nombre de rotations voulu. (0 à 3).");
      choice = scanInt();
    }while(choice < 0 || choice > 3);
    return choice;
  }
  
  public boolean askPieceReverse() {
    System.out.println("Si vous voulez effectuer un effet miroir sur la pièce, entrez true. Sinon entrez false.");
    return scanBoolean();
  }
  
  public void closeDialogue() {
    scan.close();
  }
  
  public boolean exitAction() {
    System.out.println("Entrez 'q' pour quitter et revenir au menu des actions.");
    return (!(scan.next().equals("q")));
  }
  
  public void exitDisplay() {
    String choice;
    System.out.println("\nEntrez 'q' pour quitter et revenir au menu des actions.");
    do{
      choice = scan.next();
    }while (!(choice.equals("q")));
  }
}
