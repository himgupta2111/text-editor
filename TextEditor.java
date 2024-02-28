import java.util.Scanner;

class Node {
  char data;
  Node prev;
  Node next;

  Node(char data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

public class TextEditor {
  private Node head; // Head of the text
  private Node cursor; // Cursor position

  public TextEditor() {
      head = null;
      cursor = new Node('|');
      head = cursor;
  }

  // Insert text after cursor
  public void insert(String text) {
    for (char c : text.toCharArray()) {
      Node newNode = new Node(c);
      newNode.prev = cursor.prev;
      newNode.next = cursor;
      if (newNode.prev != null) {
        newNode.prev.next = newNode;
      } else {
        head = newNode;
      }
      cursor.prev = newNode;
    }
    displayText();
  }

  // Delete character before cursor
  public void delete() {
    if (cursor.prev != null) {
      cursor.prev = cursor.prev.prev;
      if (cursor.prev != null) {
        cursor.prev.next = cursor;
      } else { 
        head = cursor;
      }
    }
    displayText();
  }

  // Move cursor left
  public void moveLeft() {
    if (cursor.prev != null) {
      cursor.data = cursor.prev.data;  
      cursor = cursor.prev;
      cursor.data = '|';
    }
    displayText();
  }

  // Move cursor right
  public void moveRight() {
    if (cursor.next != null) {
      cursor.data = cursor.next.data;  
      cursor = cursor.next;
      cursor.data = '|';
    }
    displayText();
  }

  // Display current text
  public void displayText() {
    Node current = head;
    while (current != null) {
        System.out.print(current.data);
        current = current.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    Scanner scanner = new Scanner(System.in);
    char command;
    
    while (true) {
      System.out.println("Enter command (I: Insert, D: Delete, L: Left, R: Right): ");
      command = scanner.nextLine().charAt(0);
      
      switch (command) {
          case 'I':
              System.out.println("Enter text to insert: ");
              String text = scanner.nextLine();
              editor.insert(text);
              break;
          case 'D':
              editor.delete();
              break;
          case 'L':
              editor.moveLeft();
              break;
          case 'R':
              editor.moveRight();
              break;
          default:
              System.out.println("Invalid command!");
      }
    }
  }
}
