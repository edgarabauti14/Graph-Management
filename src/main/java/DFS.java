import java.util.*;

public class DFS extends SearchAlgorithmTemplate {
    private Stack<String> stack = new Stack<String>();
    private Set<String> visited = new HashSet<>();
    Map<String, String> pathTraversed = new HashMap<>();

    public DFS(Map<String, List<String>> graph) {
        super(graph);
    }

    @Override
    void addNode(String src, String dst) {
        stack.push(src);
        visited.add(src);
        pathTraversed.put(src, dst);
    }

    @Override
    Collection<String> getElementStructure() {
        return stack;
    }

    @Override
    String getCurrentNode() {
        return stack.pop();
    }

    @Override
    void checkAdjacentNodesOf(String current, Map<String, List<String>> graph) {
        /*
        if(!visited.contains(current)) {
           visited.add(current);

           if(visited.contains(dst)){ return; }

           List<String> nodes = graph.get(current);
           if(nodes == null) {
               return;
           }
           for(String node: nodes) {
               if(node.equals(dst)) {
                   pathTraversed.put(current, dst);
                   return;
               }
               if(!visited.contains(node)) {
                   addNode(node);
                   pathTraversed.put(current, node);
               }
           }
        }*/

        for(String neighbor : graph.getOrDefault(current, new ArrayList<>())){
            if(!visited.contains(neighbor)){
                addNode(neighbor, current);
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
        System.out.println("DFS Printing path traversed:");
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
    // procedure DFS_iterative(G, v) is
    //    let S be a stack

    //    S.push(v)

    //    while S is not empty do
    //        v = S.pop()
    //        if v is not labeled as discovered then
    //            label v as discovered
    //            for all edges from v to w in G.adjacentEdges(v) do
    //                if w is not labeled as discovered then
    //                    S.push(w) */