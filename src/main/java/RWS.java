import java.util.*;

public class RWS extends SearchAlgorithmTemplate {
    private Stack<String> stack = new Stack<String>();
    private Set<String> visited = new HashSet<>();
    private Map<String, String> pathTraversed = new HashMap<>();
    private List<String> fullPath = new ArrayList<>();


    public RWS(Map<String, List<String>> graph) {
        super(graph);
    }

    @Override
    void addNode(String src, String dst) {
        stack.push(src);
        visited.add(src);
        pathTraversed.put(src, dst);
        fullPath.add(src);
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
        addNode(current);

        List<String> nodes = graph.get(current);
        if (nodes == null) {
            return;
        }

        Random nodeRand = new Random();
        String randomNode = nodes.get(nodeRand.nextInt(nodes.size()));

        if(!visited.contains(randomNode)) {
            addNode(randomNode);
            pathTraversed.put(current, randomNode);
        }*/

        List<String> neighbors = graph.getOrDefault(current, new ArrayList<>());

        if(neighbors.isEmpty()){
            return;
        }

        /*
        System.out.println("curr: " + current);
        for(String neighbor : neighbors) {
            System.out.print(" || neighbor: " + neighbor + "\n");
        }*/

        Random rand = new Random();
        int randomNum;
        if(neighbors.isEmpty()){
            randomNum = 0;
        }else {
            randomNum = rand.nextInt(neighbors.size());
        }

        String neighbor = neighbors.get(randomNum);
        System.out.println(randomNum + " " + neighbor);


        for (String neighborss : neighbors) {
            if (!visited.contains(neighbor)) {
                addNode(neighbor, current);
            }
        }
        //printOutPathTraversed();
        //System.out.println("-----------------");
    }

    @Override
    GraphPath getPath(String curr) {
        Collections.reverse(fullPath);
        GraphPath graphPath = new GraphPath();
        for(String node : fullPath) {
            graphPath.addNode(node);
        }
        return graphPath;
    }

    void printOutPathTraversed() {
        System.out.println("RWS Printing path traversed:");
        for(String node: pathTraversed.keySet()) {
            System.out.println(node + " -> " + pathTraversed.get(node));
        }
    }

    @Override
    public Set<String> getVisited() {
        return visited;
    }
}
