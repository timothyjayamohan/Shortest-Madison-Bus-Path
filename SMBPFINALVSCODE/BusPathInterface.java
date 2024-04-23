public interface BusPathInterface { // extends Number
  // public BusPathInterface(BusStopInterface origin, BusStopInterface
  // destination, int time);
  public int getTime();

  public BusStopInterface getOrigin();

  public BusStopInterface getDestination();

  public String toString();
}
