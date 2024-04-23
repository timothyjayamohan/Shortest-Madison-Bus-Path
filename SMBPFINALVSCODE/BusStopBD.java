import java.util.LinkedList;
import java.util.List;

public class BusStopBD implements BusStopInterface{
	private String name;
	private LinkedList<BusPathInterface> outgoingEdges;
	LinkedList<BusPathInterface> incomingEdges;
	
	public BusStopBD(String name, LinkedList<BusPathInterface> outgoingEdges, LinkedList<BusPathInterface> incomingEdges) {
		this.name = name;
		this.outgoingEdges = outgoingEdges;
		this.incomingEdges = incomingEdges;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusPathInterface> getOutgoingEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusPathInterface> getIncomingEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean visited() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return this.name;
	}
	
}
