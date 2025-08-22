import java.util.List;
import java.util.Map;

public class DFSStrategy implements SortStrategy {
    @Override
    public GraphPath sort(Map<String, List<String>> graph, String src, String dest) {
        SearchAlgorithmTemplate dfs = new DFS(graph);
        return dfs.runAlgorithm(src, dest);
    }
}
