import java.util.*;

abstract class SearchAlgorithmTemplate {
    private final Map<String, List<String>> graph;

    public SearchAlgorithmTemplate(Map<String, List<String>> graph) {
        this.graph = graph;
    }

    public final GraphPath runAlgorithm(String src, String dst) {

        if(src.equals(dst)) {
            GraphPath path = new GraphPath();
            path.addNode(src);
            return path;
        }

        addNode(src, null);

        while(!getElementStructure().isEmpty()){
            String current = getCurrentNode();

            if(current.equals(dst)) {
                return getPath(current);
            }

            checkAdjacentNodesOf(current, graph);
        }

        /*
        addNode(src);
        while(!getElementStructure().isEmpty()){
            String current = getCurrentNode();
            checkAdjacentNodesOf(current, dst, graph);

            if(arrivedAtDestination(dst)){
                break;
            }

        }
        */
        return null;
    }

    abstract void addNode(String src, String dst);
    abstract String getCurrentNode();
    abstract Collection<String> getElementStructure();
    abstract Set<String> getVisited();
    abstract GraphPath getPath(String curr);

    //abstract void checkAdjacentNodesOf(String current, String dst, Map<String, List<String>> graph);
    abstract void checkAdjacentNodesOf(String current, Map<String, List<String>> graph);

    private boolean arrivedAtDestination(String dst){
        return getVisited().contains(dst);
    }
}
