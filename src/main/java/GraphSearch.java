import java.util.*;


public class GraphSearch {

    // Enum to define what algorithm is needed
    public enum Search {
        DFS, BFS, RWS
    }

    // Stores the map to perform bfs and dfs on
    private Map<String, List<String>> graph = new HashMap<>();
    private final Set<String> nodes;

    // Sets the map to perform bfs on
    public GraphSearch(Map<String, List<String>> graph, Set<String> nodes) {
        this.graph = graph;
        this.nodes = nodes;
    }

    // Returns a GraphPath from source to destination
    //  based on the chosen algorithm
    public GraphPath graphSearch(String src, String dest, Search algorithm) {
        if(algorithm == Search.DFS) {
            return DFS(src, dest);
        }else if (algorithm == Search.BFS) {
            return BFS(src, dest);
        }else if(algorithm == Search.RWS) {
            return RWS(src, dest);
        }
        return null;
    }


    // Performs the search using pseudocode in the provided Wikipedia link:
    // https://en.wikipedia.org/wiki/Depth-first_search

    private GraphPath DFS(String src, String dest){
       SortContext dfs = new SortContext(new DFSStrategy());
       return dfs.performSort(graph, src, dest);
    }

    // Performs the search using pseudocode in the provided Wikipedia link:
    // https://en.wikipedia.org/wiki/Breadth-first_search

    private GraphPath BFS(String src, String dest){
        SortContext bfs = new SortContext(new BFSStrategy());
        return bfs.performSort(graph, src, dest);
    }

    // Goes through a random path by performing
    //  random walk search
    private GraphPath RWS(String src, String dest){
        SortContext rws = new SortContext(new RWSStrategy());
        return rws.performSort(graph, src, dest);
    }
}
