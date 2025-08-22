import java.util.List;
import java.util.Map;

public interface SortStrategy {
    GraphPath sort(Map<String, List<String>> graph, String src, String dest);
}
