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
  
  public String scanString() {
    return scan.next();
  }
  
  public int askActionChoices() {
    int choice;
    do {
      System.out.println("Veuillez entrer un entier valable (entre 1 et 6).");
      choice = scanInt();
    }
    while (choice < 1 || choice > 6);
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
  
  public boolean exitAction() {
    System.out.println("Entrez 'q' pour quitter et revenir au menu des actions.");
    return (scan.next() == "q");
  }
  
  public void closeDialogue() {
    scan.close();
  }
}