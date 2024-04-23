import java.util.LinkedList;
import java.util.List;

/**
 * The Data Wrangler's version of BusStop class
 * This class represents the bus stop of the Shortest Madison Bus Path Program
 * 
 * @author DK Kim
 */
public class BusStopDW implements BusStopInterface {
  String name; // The name of the bus stop
  LinkedList<BusPathInterface> outgoingEdges; // Linked List of outgoing paths to other stops
  LinkedList<BusPathInterface> incomingEdges; // Linked List of incoming paths from other stops

  /**
   * The constructor that initializes the name, outgoing edges, incoming edges,
   * and visited
   * 
   * @param name name of the bus stop
   */
  public BusStopDW(String name) {
    this.name = name;
    this.outgoingEdges = new LinkedList<BusPathInterface>();
    this.incomingEdges = new LinkedList<BusPathInterface>();
  }

  /**
   * The constructor that initializes the name, outgoing edges, incoming edges,
   * and visited
   * 
   * @param name          name of the bus stop
   * @param outgoingEdges linked list of outgoing paths
   * @param incomingEdges linked list of incoming paths
   */
  public BusStopDW(String name, LinkedList<BusPathInterface> outgoingEdges,
      LinkedList<BusPathInterface> incomingEdges) {
    this.name = name;
    this.outgoingEdges = outgoingEdges;
    this.incomingEdges = incomingEdges;
  }

  /**
   * Getter method of the name instance
   * 
   * @return the name of the bus stop
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Getter method of the outgoingEdges instance
   * 
   * @return a linked list of the outgoing edges
   */
  @Override
  public List<BusPathInterface> getOutgoingEdges() {
    return this.outgoingEdges;
  }

  /**
   * Getter method of the incomingEdges instance
   * 
   * @return a linked list of the incoming edges
   */
  @Override
  public List<BusPathInterface> getIncomingEdges() {
    return this.incomingEdges;
  }

  /**
   * toString of BusStopDW
   * 
   * @return a String representation of BusStopDW
   */
  @Override
  public String toString() {
    return this.name + "\n Size of outgoingEdges : " + String.format("%d", outgoingEdges.size())
        + "\n Size of incomingEdges : " + String.format("%d", incomingEdges.size());
  }

}
