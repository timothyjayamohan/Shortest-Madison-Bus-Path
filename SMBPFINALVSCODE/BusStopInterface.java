import java.util.List;

public interface BusStopInterface {
  // public BusStopInterface(String name, List<BusPathInterface> outgoingEdges,
  // List<BusPathInterface> incomingEdges);
  public String getName();

  public List<BusPathInterface> getOutgoingEdges();

  public List<BusPathInterface> getIncomingEdges();


  public String toString();

}
