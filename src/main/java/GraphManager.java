import java.util.*;
import java.io.*;

public class GraphManager {
    private final List<AbstractMap.SimpleEntry<String, String>> graphEdges = new ArrayList<>();
    private final Map<String, List<String>> graph = new HashMap<>();
    private final Set<String> uniqueNodes = new HashSet<>();
    private final Set<String> isolatedNodes = new HashSet<>();

    // Adds an edge, taking a target node, from, and destination node to.
    public void addEdge(String from, String to) {
        if((from == null || to == null) || from.isEmpty() || to.isEmpty()) {
            throw new GraphManagerException("Cannot add edge to null nodes. Check that the provided nodes are of type String.");
        }

        if(!hasEdge(from, to)) {
            graphEdges.add(new AbstractMap.SimpleEntry<>(from, to));
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            uniqueNodes.add(from);
            uniqueNodes.add(to);

            isolatedNodes.remove(from);
            isolatedNodes.remove(to);

        } else{
            throw new GraphManagerException("Duplicate edge detected: " + from + " -> " + to);
        }

    }

    // Adds a node.
    //  Checks if the passed param is of object type string
    public void addNode(Object nodeObj) {
        if(!(nodeObj instanceof String)) {
            throw new GraphManagerException("Node must be a String.");
        }

        String node = (String)nodeObj;

        if(!uniqueNodes.contains(node)) {
            uniqueNodes.add(node);
            isolatedNodes.add(node);
            graph.computeIfAbsent(node, k -> new ArrayList<>()).add(node);
        }
    }

    // Adds a list of nodes.
    //  Checks if the passed param is of object type string[]
    public void addNodes(Object[] nodesObjList){
        if(!(nodesObjList instanceof String[])) {
            throw new GraphManagerException("Node must be a String array.");
        }

        String[] nodeList = (String[])nodesObjList;
        for(String nodes : nodeList) {
            addNode(nodes);
        }
    }

    // Removes a node
    public void removeNode(String label){

        if(!uniqueNodes.contains(label)) {
            throw new GraphManagerException("Node does not exist: " + label);
        }

        removeNodeEdges(label);

        uniqueNodes.remove(label);
        isolatedNodes.remove(label);
        graph.remove(label);

        for(String node : uniqueNodes) {
            boolean hasOutgoingEdge = graph.containsKey(node) && !graph.get(node).isEmpty();
            boolean hasIncomingEdge = graphEdges.stream().anyMatch(edge -> edge.getValue().equals(node));

            if(!hasOutgoingEdge && !hasIncomingEdge) {
                isolatedNodes.add(node);
            }
        }
    }

    // removes A list of Nodes
    public void removeNodes(Object[] nodesObjList){
        if(!(nodesObjList instanceof String[])) {
            throw new GraphManagerException("Node must be a String array.");
        }

        String[] nodeList = (String[])nodesObjList;
        for(String nodes : nodeList) {
            removeNode(nodes);
        }
    }

    // Removes an Edge
    public void removeEdge(String from, String to) {
        if(!hasEdge(from, to)) {
            throw new GraphManagerException("Edge does not exist from \"" + from + "\" to \"" + to + "\".");
        }

        graph.get(from).remove(to);
        graphEdges.remove(new AbstractMap.SimpleEntry<>(from, to));

        if(graph.get(from).isEmpty()){
            isolatedNodes.add(from);
        }
    }

    // Removes edges associates with nodes
    private void removeNodeEdges(String label) {
        if(!graph.containsKey(label)) {
            throw new GraphManagerException("Node does not exist: " + label);
        }

        // Removes outgoing edges
        List<String> outgoingEdges = new ArrayList<>(graph.get(label));
        for (String to : outgoingEdges) {
            if (hasEdge(label, to)) {
                removeEdge(label, to);
            }
        }

        // Finds incoming edges pointing to a node
        //  Two step process to prevent modifying the structure
        //  as we iterate over it
        List<AbstractMap.SimpleEntry<String, String>> incomingEdges = new ArrayList<>();
        for(AbstractMap.SimpleEntry<String, String> edge : graphEdges) {
            if(edge.getValue().equals(label)) {
                incomingEdges.add(edge);
            }
        }

        // Removes the edges
        for(AbstractMap.SimpleEntry<String, String> edge : incomingEdges) {
            if(hasEdge(edge.getKey(), edge.getValue())) {
                removeEdge(edge.getKey(), edge.getValue());
            }
        }
    }

    // Will output.txt the graph to a given file.
    public void outputGraph(String filePath) {
        try {
            File outputFile = new File(filePath);
            PrintWriter writeToFile = new PrintWriter(outputFile);

            writeToFile.println(graphBuilder().toString());
            writeToFile.close();

        } catch(GraphManagerException | FileNotFoundException err) {
            throw new GraphManagerException("Graph Error, could not write to file.");
        }
    }

    // Outputs graph to .dot file
    public void outputDOTGraph(String filePath) {
        try(PrintWriter writeToFile = new PrintWriter(new File(filePath))){
            writeToFile.println("digraph {\n");
            for(AbstractMap.SimpleEntry<String, String> edge : graphEdges){
                writeToFile.println("\t" + edge.getKey() + "->" + edge.getValue() + ";\n");
            }
            for (String node : isolatedNodes) {
                writeToFile.append("\t").append(node).append(";\n");
            }
            writeToFile.println("}\n");

        } catch (GraphManagerException | FileNotFoundException e){
            throw new GraphManagerException("Output DOT Graph Error, could not write to file.");
        }
    }

    // Outputs graph as PNG
    public void outputGraphics(String filePath, String format) {
        if(!format.equalsIgnoreCase("png") || filePath.isEmpty()){
            throw new GraphManagerException("Format must be png or empty filePath.");
        }

        String tempDotFile = "graph.dot";
        try(PrintWriter w = new PrintWriter(new File(tempDotFile))){

            w.write(graphBuilder().toString());

            System.out.println(graphBuilder().toString());
            System.out.print("No Edge Nodes: ");
            System.out.println(isolatedNodes);

            System.out.print("Unique Nodes: ");
            System.out.println(uniqueNodes+"\n");

        } catch (FileNotFoundException e) {
            throw new GraphManagerException("Temp Graph File Error: " + e.getMessage());
        }

        try{
            String toPngCommand = String.format("dot -Tpng %s -o %s.png", tempDotFile, filePath);
            Process toPng = Runtime.getRuntime().exec(toPngCommand);

            int exitCode = toPng.waitFor();
            if(exitCode != 0){
                throw new GraphManagerException("Error generating png: " + exitCode);
            }
        } catch (IOException | InterruptedException e){
            throw new GraphManagerException("Error generating png: " + e.getMessage());
        }
    }

    // toString Override
    @Override
    public String toString(){
        StringBuilder graphToString = new StringBuilder();
        graphToString.append("Number of nodes: ").append(uniqueNodes.size()).append("\n");
        graphToString.append("Nodes: ").append(uniqueNodes).append("\n");
        graphToString.append("Number of edges: ").append(graphEdges.size()).append("\n");
        graphToString.append("Edges:\n");
        for(AbstractMap.SimpleEntry<String, String> edge : graphEdges){
            graphToString.append(" ").append(edge.getKey()).append(" -> ").append(edge.getValue()).append("\n");
        }
        return graphToString.toString();
    }

    //Helper method for repeated code
    private StringBuilder graphBuilder(){
        StringBuilder graphBuilder = new StringBuilder();

        graphBuilder.append("digraph {\n");
        for(AbstractMap.SimpleEntry<String, String> edge : graphEdges){
            graphBuilder.append("\t").append(edge.getKey()).append(" -> ").append(edge.getValue()).append(";\n");
        }
        for (String node : isolatedNodes) {
            graphBuilder.append("\t").append(node).append(";\n");
        }
        graphBuilder.append("}\n");

        return graphBuilder;
    }

    // Returns the graph
    public Map<String, List<String>> getGraph(){
        return graph;
    }

    public Set<String> getGraphNodes() {
        return uniqueNodes;
    }

    // Check if a node exists
    public boolean hasNode(String node) {
        return uniqueNodes.contains(node);
    }

    // Checks if an edge exists.
    public boolean hasEdge(String from, String to) {
        return graph.containsKey(from) && graph.get(from).contains(to);
    }

    // Gets the edge count.
    public int getEdgeCount() {
        return graphEdges.size();
    }


}
