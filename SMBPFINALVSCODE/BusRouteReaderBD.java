import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;

public class BusRouteReaderBD implements BusRouteReaderInterface{

	@Override
	public List<BusStopInterface> readRouteFromFile(String filename)
			throws FileNotFoundException, InvalidPropertiesFormatException {
		// TODO Auto-generated method stub
		List<BusStopInterface> list = new ArrayList<BusStopInterface>();
		LinkedList<BusPathInterface> outgoing = new LinkedList<BusPathInterface>();
		LinkedList<BusPathInterface> incoming = new LinkedList<BusPathInterface>();
		BusStopBD university = new BusStopBD("University", outgoing , incoming);
		BusStopBD langdon = new BusStopBD("Langdon", outgoing , incoming);
		BusStopBD regent = new BusStopBD("Regent", outgoing , incoming);
		
		
		
		list.add(university);
		list.add(regent);
		list.add(langdon);
		
		return list;

	}
	
}