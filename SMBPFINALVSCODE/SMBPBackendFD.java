import java.io.FileNotFoundException;
import java.util.List;

public class SMBPBackendFD implements SMBPBackendInterface
{
	public SMBPBackendFD(BusRouteReaderInterface reader) {}
	public SMBPBackendFD() {}
	
	public void loadData(String filename) throws FileNotFoundException
	{
		
	}
  public void insertStop(BusStopInterface stop) throws IllegalArgumentException
  {
  	
  }
  public void removeStop(BusStopInterface stop) throws IllegalArgumentException
  {
  	
  }
  public int getNumStops()
  {
  	return 4;
  }
  public void insertStreet(BusStopInterface start, BusStopInterface end, int weight) throws IllegalArgumentException
  {
  	
  }
  public void removeStreet(BusStopInterface start, BusStopInterface end) throws IllegalArgumentException
  {
  	
  }
  public int getNumStreets()
  {
  	return 8;
  }
  public List<BusStopInterface> shortestBusPath(BusStopInterface start, BusStopInterface end)
  {
  	return null;
  }
  public double shortestBusPathCost(BusStopInterface start, BusStopInterface end)
  {
  	return 0;
  }
   
   
  public List<BusStopInterface> shortestBusPathMultipleStops(List<BusStopInterface> stops)
  {
  	return null;
  }
  public double shortestBusPathCostMultipleStops(List<BusStopInterface> stops)
  {
  	return 0;
  }
}
