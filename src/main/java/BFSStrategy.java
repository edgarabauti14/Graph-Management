import java.util.List;
import java.util.Map;

public class BFSStrategy implements SortStrategy {
    @Override
    public GraphPath sort(Map<String, List<String>> graph, String src, String dest) {
        SearchAlgorithmTemplate bfs = new BFS(graph);
        return bfs.runAlgorithm(src, dest);
    }
}
