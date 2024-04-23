import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SMBPBackendBD implements SMBPBackendInterface {
  // instance variables
  private BusRouteReaderInterface reader;
  private SMBPAlgorithmAE<BusStopInterface, BusPathDW> graph;
  List<BusStopInterface> stops;
  private int numStops;
  private int numStreets;

  // constructors
  public SMBPBackendBD(BusRouteReaderInterface x) {
    this.reader = x;
    this.graph = new SMBPAlgorithmAE<BusStopInterface, BusPathDW>();
    this.stops = new ArrayList<BusStopInterface>();
    this.numStops = 0;
    this.numStreets = 0;

  }

  // loads data into the graph
  @Override
  public void loadData(String filename) throws FileNotFoundException, InvalidPropertiesFormatException {
    // using try-catch in case file is not found or format is not correct
    try {
      List<BusStopInterface> test = new ArrayList<BusStopInterface>();
      test = this.reader.readRouteFromFile(filename);
      // inserting into graph from file
      for (BusStopInterface stop : test) {
        this.insertStop(stop);
      }

      for (BusStopInterface stop : stops) {
        for (BusPathInterface path : stop.getOutgoingEdges()) {
          BusPathDW holder = new BusPathDW(path.getOrigin(), path.getDestination(), path.getTime());
          this.graph.insertEdge(path.getOrigin(), path.getDestination(), holder);
        }
      }

      this.numStreets = this.graph.getEdgeCount();

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("The file does not exist");

    } catch (InvalidPropertiesFormatException e) {
      throw new InvalidPropertiesFormatException("Formatting Wrong");
    }

  }

  // inserts stop into graph and updates numStops

  @Override
  public void insertStop(BusStopInterface stop) throws IllegalArgumentException {
    // adding only if not in graph already
    if (!this.graph.insertNode(stop)) {
      throw new IllegalArgumentException("Stop already exists");
    }
    // adding it to list of stops and incrementing number of stops
    this.stops.add(stop);
    this.numStops++;
  }

  // removing stop from graph and updating numStops
  @Override
  public void removeStop(BusStopInterface stop) throws IllegalArgumentException {
    // using parameter's name to find actual instance of object
    String stopName = stop.getName();

    BusStopInterface stopHolder = null;

    // finding stop
    for (BusStopInterface stopInStops : stops) {
      if (stopInStops.getName().equals(stopName)) {
        stopHolder = stop;
      }
    }
    // stop not found
    if (stopHolder == null) {
      throw new NoSuchElementException("No");
    }

    // removing from graph, stops, and decrementing stops
    this.graph.removeNode(stopHolder);
    this.stops.remove(stopHolder);
    this.numStops--;
  }

  // returns number of Stops on graph
  @Override
  public int getNumStops() {
    return numStops;
  }

  // inserts Street into graph and updates numStreets
  @Override
  public void insertStreet(BusStopInterface start, BusStopInterface end, int weight) throws IllegalArgumentException {
    // inserting only if not in graph already
    if (!this.graph.insertEdge(start, end, new BusPathDW(start, end, weight))) {
      throw new IllegalArgumentException("Street already exists");
    }
    // incrementing number of Streets
    this.numStreets++;

  }

  // removing street from graph and updating numStreets
  @Override
  public void removeStreet(BusStopInterface start, BusStopInterface end) throws IllegalArgumentException {
    // checking if street is in graph using start and end names to match to actual
    // stop in graph
    String startName = start.getName();
    String destinationName = end.getName();
    BusStopInterface startHolder = null;
    BusStopInterface endHolder = null;

    // finding stop
    for (BusStopInterface stop : stops) {
      if (stop.getName().equals(startName)) {
        startHolder = stop;
      }
      if (stop.getName().equals(destinationName)) {
        endHolder = stop;
      }
    }
    // either start or end aren't real stops
    if (startHolder == null || endHolder == null) {
      throw new NoSuchElementException("No");
    }

    // removing edge from those stops and decrementing number of streets
    this.graph.removeEdge(startHolder, endHolder);
    this.numStreets--;

  }

  // returning number of streets
  @Override
  public int getNumStreets() {
    return this.numStreets;
  }

  // gets shortest path from one stop to another
  @Override
  public List<BusStopInterface> shortestBusPath(BusStopInterface start, BusStopInterface end) {
    // getting actual instances of stops using names from parameters
    String startName = start.getName();
    String destinationName = end.getName();
    BusStopInterface startHolder = null;
    BusStopInterface endHolder = null;
    for (BusStopInterface stop : stops) {
      if (stop.getName().equals(startName)) {
        startHolder = stop;
      }
      if (stop.getName().equals(destinationName)) {
        endHolder = stop;
      }
    }
    // either start or end not found
    if (startHolder == null || endHolder == null) {
      throw new NoSuchElementException("No");
    }

    // returning shortest path using actual stops
    return this.graph.shortestPathData(startHolder, endHolder);
  }

  // gets cost of shortest path from one stop to another
  @Override
  public double shortestBusPathCost(BusStopInterface start, BusStopInterface end) {
    // getting actual instances of stops using names from parameters
    String startName = start.getName();
    String destinationName = end.getName();
    BusStopInterface startHolder = null;
    BusStopInterface endHolder = null;
    for (BusStopInterface stop : stops) {
      if (stop.getName().equals(startName)) {
        startHolder = stop;
      }
      if (stop.getName().equals(destinationName)) {
        endHolder = stop;
      }
    }
    if (startHolder == null || endHolder == null) {
      throw new NoSuchElementException("No");
    }
    return this.graph.shortestPathCost(startHolder, endHolder);
  }

  // gets shortest path for multiple stops
  @Override
  public List<BusStopInterface> shortestBusPathMultipleStops(List<BusStopInterface> stopsFromFD) {
    List<BusStopInterface> actualStops = new ArrayList<BusStopInterface>();
    String startName = stopsFromFD.get(0).getName();
    String middleName = stopsFromFD.get(1).getName();
    String endName = stopsFromFD.get(2).getName();
    BusStopInterface startHolder = null;
    BusStopInterface middleHolder = null;
    BusStopInterface endHolder = null;
    for (BusStopInterface stop : this.stops) {
      if (stop.getName().equals(startName)) {
        startHolder = stop;
      }
      if (stop.getName().equals(middleName)) {
        middleHolder = stop;
      }
      if (stop.getName().equals(endName)) {
        endHolder = stop;
      }
    }

    if (startHolder == null || middleHolder == null || endHolder == null) {
      System.out.println("hello");
      throw new NoSuchElementException("No");
    }

    actualStops.add(startHolder);
    actualStops.add(middleHolder);
    actualStops.add(endHolder);

    return this.graph.shortestMultiPathData(actualStops);
  }

  // gets shortest path cost for multiple stops
  @Override
  public double shortestBusPathCostMultipleStops(List<BusStopInterface> stopsFromFD) {
    List<BusStopInterface> actualStops = new ArrayList<BusStopInterface>();
    String startName = stopsFromFD.get(0).getName();
    String middleName = stopsFromFD.get(1).getName();
    String endName = stopsFromFD.get(2).getName();
    BusStopInterface startHolder = null;
    BusStopInterface middleHolder = null;
    BusStopInterface endHolder = null;
    for (BusStopInterface stop : this.stops) {
      if (stop.getName().equals(startName)) {
        startHolder = stop;
      }
      if (stop.getName().equals(middleName)) {
        middleHolder = stop;
      }
      if (stop.getName().equals(endName)) {
        endHolder = stop;
      }
    }
    if (startHolder == null || middleHolder == null || endHolder == null) {
      System.out.println("hello");
      System.out.println(startHolder.toString());
      System.out.println(middleHolder.toString());
      System.out.println(endHolder.toString());
      throw new NoSuchElementException("No");
    }

    actualStops.add(startHolder);
    actualStops.add(middleHolder);
    actualStops.add(endHolder);

    return this.graph.shortestMultiPathCost(actualStops);
  }

  // returns stops on graph
  public List<BusStopInterface> getStops() {
    return this.stops;
  }

}