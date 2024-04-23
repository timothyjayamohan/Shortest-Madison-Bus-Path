import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class SMBPAlgorithmBD extends DijkstraGraph implements SMBPAlgorithmInterface{

	@Override
	public List shortestMultiPathData(List nodes) {
		// TODO Auto-generated method stub
		LinkedList<BusPathInterface> outgoing = new LinkedList<BusPathInterface>();
		LinkedList<BusPathInterface> incoming = new LinkedList<BusPathInterface>();
		BusStopBD university = new BusStopBD("University", outgoing , incoming);
		BusStopBD langdon = new BusStopBD("Langdon", outgoing , incoming);
		BusStopBD regent = new BusStopBD("Regent", outgoing , incoming);
		BusStopBD idk = new BusStopBD("idk", outgoing , incoming);
		
		
		List<BusStopBD> list = new ArrayList<BusStopBD>();
		list.add(university);
		list.add(langdon);
		list.add(regent);
		list.add(idk);
		
		return list;
		
	}

	@Override
	public double shortestMultiPathCost(List nodes) {
		// TODO Auto-generated method stub
		return 17;
	}

	

	

	
	
}