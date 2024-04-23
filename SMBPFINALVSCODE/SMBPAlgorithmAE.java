import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class is the algorithm that this app uses. This adds the functionality of the Dijsktra's Algorithm and higher functionality
 * to the app, being able to calculate the shortest path and cost of multiple nodes at once.
 * @extends DijkstraGraph
 * @implements SMBPAlgorithmInterface
 */
public class SMBPAlgorithmAE<NodeType, EdgeType extends Number> extends DijkstraGraph<NodeType, EdgeType> implements SMBPAlgorithmInterface<NodeType, EdgeType>{

    /**
     * Finds the shortest path of a list of node checkpoints *WITH NO REPEATED NODES*
     * This method adds the list of paths minus their individual ending nodes plus the last node at the end of the list
     * 
     * EX) A to A is counted as A
     *      This is counted as A to A without A, adding A
     *      so the path is A
     * EX) A to B to B is counted as the path A to B
     *      This is counted as the path A to B without B and path B to B without B (empty), adding B
     *      so the path A to B
     * EX) A to B to B to C is counted as the path A to B to C
     *      This is counted as the path A to B without B, path B to B without B (empty), and path B to C without C adding C
     *      so the path is A to B to C
     * 
     * @return shortest path in a List
     * @throws NoSuchElementException when there are not 2 or more checkpoint nodes
     */
    public List<NodeType> shortestMultiPathData(List<NodeType> nodes) throws NoSuchElementException{
        if (nodes.size() < 2) {
            throw new NoSuchElementException("There must be two or more nodes");
        }
        
        List<NodeType> result = new ArrayList<>();
        
        //add all the independent paths between the node checkpoints together
        for (int i = 0; i < nodes.size() - 1; i++) {
            //add the shortest path between checkpoint i and checkpoint i + 1 to the result
            List<NodeType> toAdd = super.shortestPathData(nodes.get(i), nodes.get(i + 1));

            result.addAll(toAdd);
            
            //remove the last node to avoid repeating checkpoint nodes
            result.remove(result.size() - 1); 
        }

        //if it is not a repeated node or the result is empty (meaning the calculation was X to X), 
        //include the ending checkpoint node because it was not added in the loop 
        if (result.size() == 0 || !result.get(result.size() - 1).equals(nodes.get(nodes.size() - 1))) {
            result.add(nodes.get(nodes.size() - 1));
        }
        
        return result;
    }

    /**
     * Finds the cost of the shortest path of a list of node checkpoints
     * @return cost of the shortest path
     * @throws NoSuchElementException when there are not 2 or more checkpoint nodes
     */
    public double shortestMultiPathCost(List<NodeType> nodes) throws NoSuchElementException{
        if (nodes.size() < 2) {
            throw new NoSuchElementException("There must be two or more nodes");
        }
        
        double result = 0;
        //add the costs of the independent paths together
        for (int i = 0; i < nodes.size() - 1; i++) {
            //add the cost from node i to i + 1 to the result
            result += super.shortestPathCost(nodes.get(i), nodes.get(i + 1));
        }

        return result;
    }
}
