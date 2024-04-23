/**
 * The Data Wrangler's version of BusPath class
 * This class represents the edge or the path between two bus stops.
 * 
 * @author DK Kim
 */
public class BusPathDW extends Number implements BusPathInterface {

  BusStopInterface origin; // the origin stop of this bus path
  BusStopInterface destination; // the destination stop of this bus path
  int time; // the weight of the edge, the time in minutes it takes from the origin stop to
            // the destination stop

  /**
   * The constructor of this class, initializes the origin, destination, and time
   * of the bus path
   * 
   * @param origin      origin stop of the path
   * @param destination destination stop of the path
   * @param time        the weight of the edge
   * 
   */
  public BusPathDW(BusStopInterface origin, BusStopInterface destination, int time) {
    this.origin = origin;
    this.destination = destination;
    this.time = time;
  }

  /**
   * The getter of time
   * 
   * @return the time it takes from origin to destination
   */
  @Override
  public int getTime() {
    return this.time;
  }

  /**
   * The getter of origin stop
   * 
   * @return the origin BusStop object of this path
   */
  @Override
  public BusStopInterface getOrigin() {
    return this.origin;
  }

  /**
   * The getter of destination stop
   * 
   * @return the destination BusStop object of this path
   */
  @Override
  public BusStopInterface getDestination() {
    return this.destination;
  }

  /**
   * Override intValue method from Number.
   * 
   * @return the int value of the weight of this edge
   */
  @Override
  public int intValue() {
    return this.time;
  }

  /**
   * Override longValue method from Number.
   * 
   * @return the double value of the weight of this edge
   */
  @Override
  public long longValue() {
    return (long) this.time;
  }

  /**
   * Override floatValue method from Number.
   * 
   * @return the float value of the weight of this edge
   */
  @Override
  public float floatValue() {
    return (float) this.time;
  }

  /**
   * Override doubleValue method from Number.
   * 
   * @return the double value of the weight of this edge
   */
  @Override
  public double doubleValue() {
    return (double) this.time;
  }

  /**
   * toString of BusPathDW
   * 
   * @return a String representation of BusPathDW
   */
  @Override
  public String toString() {
    // ex) WestTransferPoint -> Sheboygan_EauClaire Time (minutes) : 6

    String toStringRepresentation = this.origin.getName() + " -> " + this.destination.getName() + " & Time (minutes) : "
        + String.valueOf(this.time);
    return toStringRepresentation;
  }

}
