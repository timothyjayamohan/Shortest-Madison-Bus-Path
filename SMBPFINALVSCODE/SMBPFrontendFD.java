import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SMBPFrontendFD implements SMBPFrontendInterface {
  private Scanner input;
  private SMBPBackendInterface backend;

  public SMBPFrontendFD(Scanner input, SMBPBackendInterface backend) {
    this.input = input;
    this.backend = backend;
  }

  /**
   * Command loop to check key pressed
   */
  public void runCommandLoop() {
    System.out.println("Welcome to the Shortest Madison Bus Path Calculator");

    char command = '\0';

    while (command != 'Q') {
      command = this.mainMenuPrompt();

      switch (command) {
        case 'L':
          loadDataCommand();
          break;
        case 'N':
          newStop();
          break;
        case 'D':
          deleteStop();
          break;
        case 'S':
          newStreet();
          break;
        case 'T':
          deleteStreet();
          break;
        case 'U':
          numStops();
          break;
        case 'M':
          numStreets();
          break;
        case 'P':
          shortestPath();
          break;
        case 'C':
          shortestCost();
          break;
        case 'I':
          shortestPathMultiple();
          break;
        case 'E':
          shortestCostMultiple();
        default:
          System.out.println("Did not recognize command; type one of the letters in []");
          break;
      }
    }
  }

  /**
   * Menu prompt that displays the options for the user
   */
  public char mainMenuPrompt() {
    System.out.println("Choose a command from the list below: ");
    System.out.println("[L]oad data from file");
    System.out.println("Add a [N]ew Stop");
    System.out.println("[D]elete a Stop");
    System.out.println("Add a new [S]treet");
    System.out.println("Delete a S[T]reet");
    System.out.println("Get N[U]mber of Stops");
    System.out.println("Get Nu[M]ber of Streets");
    System.out.println("Get Shortest [P]ath");
    System.out.println("Get Shortest [C]ost");
    System.out.println("Get Shortest Path for Mult[I]ple Paths");
    System.out.println("Get Shortest Cost for Multipl[E] Costs");

    System.out.println("Input Command Key: ");

    String commandInput = this.input.nextLine().trim();

    if (commandInput.length() != 1)
      return 0;

    return Character.toUpperCase(commandInput.charAt(0));
  }

  /**
   * Load the data from a file
   */
  public void loadDataCommand() {
    System.out.println("Enter the name of the file: ");

    String filename = input.nextLine().trim(); // format filename
    try {
      try {
        backend.loadData(filename);
      } catch (InvalidPropertiesFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
      return; // fail
    }
    System.out.println("Data Loaded Successfully.");
  }

  /**
   * Add or edit a stop
   */
  public void newStop() {
    String inputNameString;
    try {
      System.out.println("Enter a new stop: ");
      inputNameString = input.nextLine().trim();
      backend.insertStop(new BusStopDW(inputNameString));
    } catch (Exception e) {
      System.out.println("Stop already exists!");
      return;
    }

    System.out.println("Stop \"" + inputNameString + "\" successfuly added");
  }

  /**
   * Delete an existing stop
   */
  public void deleteStop() {
    String inputNameString;
    try {
      System.out.println("Enter a stop to delete: ");
      inputNameString = input.nextLine().trim();
      backend.removeStop(new BusStopDW(inputNameString));
    } catch (Exception e) {
      System.out.println("Stop does not exist!");
      return;
    }

    System.out.println("Stop \"" + inputNameString + "\" successfuly removed");
  }

  /**
   * Add or edit an existing street
   */
  public void newStreet() {
    String inputNameString1;
    String inputNameString2;
    int time;
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
      System.out.println("Enter the time: ");
      time = Integer.parseInt(input.nextLine().trim());
    } catch (Exception e) {
      System.out.println("Invalid Input!");
      return;
    }
    try {
      backend.insertStreet(new BusStopDW(inputNameString1),
          new BusStopDW(inputNameString2), time);
    } catch (IllegalArgumentException e) {
      System.out.println("Street already exists!");
      return;
    } catch (Exception e) {
      System.out.println("Another Exception");
      System.out.println(e.getMessage());
      return;
    }

    System.out.println("Street successfully added");
  }

  /**
   * Delete an existing street
   */
  public void deleteStreet() {
    String inputNameString1;
    String inputNameString2;
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
    } catch (Exception e) {
      System.out.println("Invalid Input!");
      return;
    }
    try {
      backend.removeStreet(new BusStopDW(inputNameString1),
          new BusStopDW(inputNameString2));
    } catch (Exception e) {
      System.out.println("No street exists!");
      return;
    }

    System.out.println("Street successfully removed");
  }

  /**
   * Get number of stops
   */
  public void numStops() {
    System.out.println("Number of Stops: " + backend.getNumStops());
  }

  /**
   * Get number of streets
   */
  public void numStreets() {
    System.out.println("Number of Streets: " + backend.getNumStreets());
  }

  /**
   * Find shortest path between two stops
   */
  public void shortestPath() {
    String inputNameString1;
    String inputNameString2;
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
      backend.shortestBusPath(new BusStopDW(inputNameString1),
          new BusStopDW(inputNameString2));
    } catch (Exception e) {
      System.out.println("Invalid stops!");
      return;
    }

    System.out.println("Shortest Path: " + backend.shortestBusPath(new BusStopDW(inputNameString1),
        new BusStopDW(inputNameString2)).toString());

  }

  /**
   * Get shortest cost between two stops (shortest time)
   */
  public void shortestCost() {
    String inputNameString1;
    String inputNameString2;
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
      backend.shortestBusPathCost(new BusStopDW(inputNameString1),
          new BusStopDW(inputNameString2));
    } catch (Exception e) {
      System.out.println("Invalid stops!");
      System.out.println(e.getMessage());
      return;
    }

    System.out.println("Cost: " + backend.shortestBusPathCost(new BusStopDW(inputNameString1),
        new BusStopDW(inputNameString2)));
  }

  /**
   * Find shortest path between multiple stops (3)
   */
  public void shortestPathMultiple() {
    String inputNameString1;
    String inputNameString2;
    String inputNameString3;
    List<BusStopInterface> list = new ArrayList<BusStopInterface>();
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
      System.out.println("Enter the third stop: ");
      inputNameString3 = input.nextLine().trim();
      list.add(new BusStopDW(inputNameString1));
      list.add(new BusStopDW(inputNameString2));
      list.add(new BusStopDW(inputNameString3));
      backend.shortestBusPathMultipleStops(list);
    } catch (NoSuchElementException e) {
      System.out.println("Invalid stops!");
      return;
    }
    catch (Exception e){
      System.out.println("Another Problem");
      e.getMessage();
      e.printStackTrace();
      return;
    }

    System.out.println("Shortest Path: " + list.toString());
  }

  /**
   * Get shortest cost between multiple stops (3)
   */
  public void shortestCostMultiple() {
    String inputNameString1;
    String inputNameString2;
    String inputNameString3;
    List<BusStopInterface> list = new ArrayList<BusStopInterface>();
    try {
      System.out.println("Enter the first stop: ");
      inputNameString1 = input.nextLine().trim();
      System.out.println("Enter the second stop: ");
      inputNameString2 = input.nextLine().trim();
      System.out.println("Enter the third stop: ");
      inputNameString3 = input.nextLine().trim();
      list.add(new BusStopDW(inputNameString1));
      list.add(new BusStopDW(inputNameString2));
      list.add(new BusStopDW(inputNameString3));
      backend.shortestBusPathCostMultipleStops(list);
    } catch (Exception e) {
      System.out.println("Invalid stops!");
      return;
    }

    System.out.println("Cost: " + backend.shortestBusPathCostMultipleStops(list));
  }

  public static boolean test() {

    return true;

  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    BusRouteReaderInterface reader = new BusRouteReaderDW();
    SMBPBackendInterface backend = new SMBPBackendBD(reader);
    SMBPFrontendInterface frontend = new SMBPFrontendFD(scanner, backend);

    frontend.runCommandLoop();

    // System.out.println(test());
  }
}
