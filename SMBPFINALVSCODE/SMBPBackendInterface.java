import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface SMBPBackendInterface {

  // public SMBPBackendBD(BusRouteReaderInterface x);

  public void loadData(String filename) throws FileNotFoundException, InvalidPropertiesFormatException;

  public void insertStop(BusStopInterface stop) throws IllegalArgumentException;

  public void removeStop(BusStopInterface stop) throws IllegalArgumentException;

  public int getNumStops();

  public void insertStreet(BusStopInterface start, BusStopInterface end, int weight) throws IllegalArgumentException;

  public void removeStreet(BusStopInterface start, BusStopInterface end) throws IllegalArgumentException;

  public int getNumStreets();

  public List<BusStopInterface> shortestBusPath(BusStopInterface start, BusStopInterface end);

  public double shortestBusPathCost(BusStopInterface start, BusStopInterface end);

  public List<BusStopInterface> shortestBusPathMultipleStops(List<BusStopInterface> stops);

  public double shortestBusPathCostMultipleStops(List<BusStopInterface> stops);

}
