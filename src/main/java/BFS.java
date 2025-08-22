import java.util.*;

public class BFS extends SearchAlgorithmTemplate {
    private Queue<String> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    Map<String, String> pathTraversed = new HashMap<>();

    public BFS(Map<String, List<String>> graph) {
        super(graph);
    }

    @Override
    void addNode(String src, String dst) {
        queue.add(src);
        visited.add(src);
    }

    @Override
    Collection<String> getElementStructure() {
        return queue;
    }

    @Override
    String getCurrentNode() {
        return queue.poll();
    }

    @Override
    void checkAdjacentNodesOf(String current, Map<String, List<String>> graph) {
        /*
        for(String node: graph.get(current)){
            if(!visited.contains(node)){
                if(visited.contains(dst)){ return; }
                addNode(node);
                pathTraversed.put(current, node);
            }
        }*/

        for(String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
            if(!visited.contains(neighbor)) {
                addNode(neighbor, current);
                pathTraversed.put(neighbor, current);
            }
        }
    }

    @Override
    GraphPath getPath(String curr) {
        GraphPath graphPath = new GraphPath();
        for(String at = curr; at != null; at = pathTraversed.get(at)) {
            graphPath.addNode(at);
        }
        return graphPath;
    }

    void printOutPathTraversed() {
        System.out.println("BFS Printing path traversed:");
        for(String node: pathTraversed.keySet()) {
            System.out.println(node + " -> " + pathTraversed.get(node));
        }
    }

    @Override
    public Set<String> getVisited() {
        return visited;
    }
}

    /* [ For Reference ]
    // | 1  procedure BFS(G, root) is
    // | 2      let Q be a queue

    // | 3      label root as explored
    // | 4      Q.enqueue(root)

    // | 5      while Q is not empty do

    // | 6          v := Q.dequeue()

    // | 7          if v is the goal then
    // | 8              return v

    // | 9          for all edges from v to w in G.adjacentEdges(v) do
    // | 10              if w is not labeled as explored then
    // | 11                  label w as explored
    // | 12                  w.parent := v
    // | 13                  Q.enqueue(w) */