import java.util.List;
import java.util.Map;

public class SortContext {
    private SortStrategy sortStrategy;

    public SortContext(SortStrategy sortStrategy){
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(SortStrategy sortStrategy){
        this.sortStrategy = sortStrategy;
    }

    public GraphPath performSort(Map<String, List<String>> graph, String src, String dst){
        return sortStrategy.sort(graph, src, dst);
    }
}

