import java.util.LinkedList;
import java.util.List;

public class BusStopFD implements BusStopInterface
{
	private String name;
	private LinkedList<BusPathInterface> outgoingEdges;
	private LinkedList<BusPathInterface> incomingEdges;
	
	public BusStopFD(String name, LinkedList<BusPathInterface> outgoingEdges, LinkedList<BusPathInterface> incomingEdges)
	{
		this.name = name;
		this.outgoingEdges = outgoingEdges;
		this.incomingEdges = incomingEdges;
	}
  public String getName()
  {
  	return null;
  }


  public List<BusPathInterface> getOutgoingEdges()
  {
  	return null;
  }


 public List<BusPathInterface> getIncomingEdges()
 {
	 return null;
 }
}
