import java.util.NoSuchElementException;
import java.util.List;

public interface SMBPAlgorithmInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType>{
    //public SMBPAlgorithmInterface();

    public List<NodeType> shortestMultiPathData(List<NodeType> nodes) throws NoSuchElementException;
    public double shortestMultiPathCost(List<NodeType> nodes) throws NoSuchElementException;
}
