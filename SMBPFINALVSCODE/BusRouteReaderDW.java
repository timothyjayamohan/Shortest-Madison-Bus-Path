import java.io.File;
import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This Data Wrangler's version of the BusRouteReader class
 * used for reading the dot file and formatting it
 * 
 * @author DK Kim
 */
public class BusRouteReaderDW implements BusRouteReaderInterface {

  /**
   * This method reads DOT file and formats it
   * 
   * @param filename is the input file
   * 
   * @return an ArrayList of BusStopInterface implementation format objects that
   *         are formatted using file's data
   * @throws FileNotFoundException            when input file is not found
   * @throws InvalidPropertiesFormatException when scanned String that follows the
   *                                          expected pattern violates the
   *                                          expected format
   */
  @Override
  public List<BusStopInterface> readRouteFromFile(String filename)
      throws FileNotFoundException, InvalidPropertiesFormatException {

    // ArrayList of BusStopInterface to return
    ArrayList<BusStopInterface> busStops = new ArrayList<>();

    // Hashtable of created BusStops
    Hashtable<Integer, BusStopDW> stopNames = new Hashtable<>();

    // set the pattern of input data to look for
    // ex) WestTransferPoint -> Sheboygan_EauClaire [ label = 6 ]
    final Pattern DOTPATTERN = Pattern.compile("\\w+\\s->\\s\\w+\\s\\[\\s\\w+\\s\\=\\s\\d+\\s\\]");

    // input and scan file
    File inputFile = new File(filename);
    Scanner fileScanner = new Scanner(inputFile);

    // store scanned file as string
    String inputString = "";
    while (fileScanner.hasNextLine()) {
      inputString += fileScanner.nextLine() + "\n"; // formating so that each line is divided
    }
    fileScanner.close();

    // initialize matcher with above pattern
    Matcher matcher = DOTPATTERN.matcher(inputString);

    while (matcher.find()) {
      // get the single line of data that contains origin, destination, time
      String oneLine = inputString.substring(matcher.start(), matcher.end() - 1);
      String[] spaceSplitStrings = oneLine.split(" ");
      String originStopName;
      String destinationStopName;
      int time;
      try { // Check for invalid property format
        // set String and int instances using formatted split input string
        originStopName = spaceSplitStrings[0];
        destinationStopName = spaceSplitStrings[2];
        time = Integer.parseInt(spaceSplitStrings[6]);
      } catch (Exception e) { // invalid format
        throw new InvalidPropertiesFormatException("Invalid format");
      }

      if (!stopNames.containsKey(originStopName.hashCode())) {// if origin node does not exist
        BusStopDW newBusStopOrigin = new BusStopDW(originStopName); // create new BusStop at origin

        stopNames.put(originStopName.hashCode(), newBusStopOrigin); // add new bus stop to hashtable
        busStops.add(newBusStopOrigin); // add new stop to ArrayList
      }

      if (!stopNames.containsKey(destinationStopName.hashCode())) {// if destination node does not exist
        BusStopDW newBusStopDestination = new BusStopDW(destinationStopName); // create new BusStop at destination

        stopNames.put(destinationStopName.hashCode(), newBusStopDestination); // add new bus stop to hashtable
        busStops.add(newBusStopDestination); // add new stop to ArrayList
      }

      // create new BusPath from the origin to the destination
      BusPathDW newBusPath = new BusPathDW(stopNames.get(originStopName.hashCode()),
          stopNames.get(destinationStopName.hashCode()),
          time);

      // add new bus path to the outgoing edges of origin bus stop

      stopNames.get(originStopName.hashCode()).getOutgoingEdges().add(newBusPath);

      // add new bus path to the incoming edges of the destination bus stop
      stopNames.get(destinationStopName.hashCode()).getIncomingEdges().add(newBusPath);

    }

    return busStops;
  }

}
