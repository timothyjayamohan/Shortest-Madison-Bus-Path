// --== CS400 File Header Information ==--
// Name: Timothy Jayamohan
// Email: jayamohan@wisc.edu
// Group and Team: DE blue
// Group TA: Callie Kim
// Lecturer: Florian Heimerl
// Notes to Grader: NA

import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode
   * contains data about one specific path between the start node and another
   * node in the graph. The final node in this path is stored in it's node
   * field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor
   * field (this field is null within the SearchNode containing the starting
   * node in it's node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost
   * SearchNode has the highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * This helper method creates a network of SearchNodes while computing the
   * shortest path between the provided start and end locations. The
   * SearchNode that is returned by this method is represents the end of the
   * shortest path that is found: it's cost is the cost of that shortest path,
   * and the nodes linked together through predecessor references represent
   * all of the nodes along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found
   *                                or when either start or end data do not
   *                                correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) {

    // checking if start and end are in the graph
    if (!nodes.containsKey(start) || !nodes.containsKey(end)) {
      throw new NoSuchElementException("nodes not in graph");
    }

    // creating a hashtable for visited and search nodes
    Hashtable<NodeType, SearchNode> visited = new Hashtable<>();
    Hashtable<NodeType, SearchNode> searchNodes = new Hashtable<>();

    // creating priority queue
    PriorityQueue<SearchNode> pq = new PriorityQueue<>();

    // making a SearchNode for all data with max cost
    nodes.forEach((data, node) -> {
      searchNodes.put(data, new SearchNode(node, Integer.MAX_VALUE, null));
    });

    // making startNode and setting cost to 0
    SearchNode startNode = searchNodes.get(start);
    startNode.cost = 0;

    // adding startNode to priority queue
    pq.add(startNode);

    // while the end node is not visited
    while (!visited.containsKey(end)) {

      // if priority queue is empty then there is no path
      if (pq.isEmpty()) {
        throw new NoSuchElementException("No path from start to end");
      }

      // current node
      SearchNode current = pq.poll();

      // current node is end node
      if (current.node.data.equals(end)) {
        return current;
      }

      // checking if the current node has been visited already
      if (!visited.containsKey(current.node.data)) {

        // putting it in visited
        visited.put(current.node.data, current);

        // making SearchNodes for every successor node and adding that to priority queue
        // if cost is greater than current + edge cost
        for (Edge edge : current.node.edgesLeaving) {
          SearchNode visitingNode = searchNodes.get(edge.successor.data);

          if (!visited.contains(visitingNode.node.data)) {

            if (visitingNode.cost > (current.cost + edge.data.doubleValue())) {
              visitingNode.cost = current.cost + edge.data.doubleValue();
              visitingNode.predecessor = current;
              pq.add(visitingNode);
            }

          }

        }
      }

    }

    // returning the end SearchNode
    return searchNodes.get(end);

  }

  /**
   * Returns the list of data values from nodes along the shortest path
   * from the node with the provided start value through the node with the
   * provided end value. This list of data values starts with the start
   * value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This
   * method uses Dijkstra's shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    // TODO: implement in step 7

    // getting SearchNode
    SearchNode node = computeShortestPath(start, end);

    // Adding Predecessor to path until nothing left to make full path
    LinkedList<NodeType> path = new LinkedList<>();
    while (node != null) {
      path.addFirst(node.node.data);
      node = node.predecessor;
    }

    return path;

  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest
   * path freom the node containing the start data to the node containing the
   * end data. This method uses Dijkstra's shortest path algorithm to find
   * this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    // getting SearchNode and returning cost of it
    SearchNode shortestPath = computeShortestPath(start, end);
    return shortestPath.cost;
  }

}