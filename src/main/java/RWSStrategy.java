import java.util.List;
import java.util.Map;

public class RWSStrategy implements SortStrategy {
    @Override
    public GraphPath sort(Map<String, List<String>> graph, String src, String dest) {
        SearchAlgorithmTemplate rws = new RWS(graph);
        return rws.runAlgorithm(src, dest);
    }
}
