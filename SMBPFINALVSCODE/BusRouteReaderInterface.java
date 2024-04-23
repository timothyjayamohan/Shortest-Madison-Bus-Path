import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


public interface BusRouteReaderInterface {
  // public BusRouteReaderInterface();
  public List<BusStopInterface> readRouteFromFile(String filename) throws FileNotFoundException, InvalidPropertiesFormatException;


}
