import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphSearchTest {
    private GraphManager manager;
    private GraphPath graphPath;
    private GraphSearch search;

    @Before
    public void setUp(){
        manager = new GraphManager();
        System.out.println("This is the Graph Search setup");
    }

    @After
    public void tearDown(){
        manager = null;
        System.out.println("This is the  Graph Search teardown");
        System.out.println("===========================\n");
    }

    @Test
    public void testBFSNull(){
        // Arrange
        manager.addNode("a");
        manager.addNode("b");

        System.out.println("Expect Null Test:"+
                "\nmanager.addNode(\"a\");" +
                "\nmanager.addNode(\"b\");");

        // Act
        search = new GraphSearch(manager.getGraph(), manager.getGraphNodes());
        graphPath = search.graphSearch("a", "b", GraphSearch.Search.BFS);

        System.out.println("Expect NULL, the path does not exist|| graphPath output: " + graphPath);

        // Assert
        assertNull("Expect NULL:", graphPath);
    }

    @Test
    public void testDFSNull(){
        // Arrange
        manager.addNode("a");
        manager.addNode("b");

        System.out.println("Expect Null Test:"+
                "\nmanager.addNode(\"a\");" +
                "\nmanager.addNode(\"b\");");

        // Act
        search = new GraphSearch(manager.getGraph(), manager.getGraphNodes());
        graphPath = search.graphSearch("a", "b", GraphSearch.Search.DFS);

        System.out.println("Expect NULL, the path does not exist|| graphPath output: " + graphPath);

        // Assert
        assertNull("Expect NULL:", graphPath);
    }

    @Test
    public void testBFS(){
        // Arrange
        GraphPath output = new GraphPath();
        output.addNode("a");
        output.addNode("b");
        output.addNode("c");
        output.addNode("d");
        output.addNode("e");
        output.addNode("f");

        manager.addEdge("a", "b");
        manager.addEdge("b", "c");
        manager.addEdge("c", "d");
        manager.addEdge("d", "e");
        manager.addEdge("e", "f");

        System.out.println("Expect Path Test:"+
                "manager.addEdge(\"a\", \"b\");\n" +
                "manager.addEdge(\"b\", \"c\");\n" +
                "manager.addEdge(\"c\", \"d\");\n" +
                "manager.addEdge(\"d\", \"e\");\n" +
                "manager.addEdge(\"e\", \"f\");");

        // Act
        search = new GraphSearch(manager.getGraph(), manager.getGraphNodes());
        graphPath = search.graphSearch("a", "f", GraphSearch.Search.BFS);

        System.out.println("Expect: a -> b -> c -> d -> e -> f || graphPath output: " + graphPath);

        // Assert
        assertEquals("Expect correct path:", output.toString() ,graphPath.toString());
    }

    @Test
    public void testDFS(){
        // Arrange
        GraphPath output = new GraphPath();
        output.addNode("a");
        output.addNode("b");
        output.addNode("c");
        output.addNode("d");
        output.addNode("e");
        output.addNode("f");

        manager.addEdge("a", "b");
        manager.addEdge("b", "c");
        manager.addEdge("c", "d");
        manager.addEdge("d", "e");
        manager.addEdge("e", "f");

        System.out.println("Expect Path Test:"+
                "manager.addEdge(\"a\", \"b\");\n" +
                "manager.addEdge(\"b\", \"c\");\n" +
                "manager.addEdge(\"c\", \"d\");\n" +
                "manager.addEdge(\"d\", \"e\");\n" +
                "manager.addEdge(\"e\", \"f\");");

        // Act
        search = new GraphSearch(manager.getGraph(), manager.getGraphNodes());
        graphPath = search.graphSearch("a", "f", GraphSearch.Search.DFS);

        System.out.println("Expect: a -> b -> c -> d -> e -> f || graphPath output: " + graphPath);

        // Assert
        assertEquals("Expect correct path:", output.toString() ,graphPath.toString());
    }

    @Test
    public void testRWS(){
        // Arrange
        GraphPath output = new GraphPath();
        output.addNode("a");
        output.addNode("b");
        output.addNode("c");
        output.addNode("d");
        output.addNode("e");
        output.addNode("f");

        manager.addEdge("a", "b");
        manager.addEdge("b", "c");
        manager.addEdge("c", "d");
        manager.addEdge("d", "e");
        manager.addEdge("e", "f");

        System.out.println("Expect Path Test:"+
                "manager.addEdge(\"a\", \"b\");\n" +
                "manager.addEdge(\"b\", \"c\");\n" +
                "manager.addEdge(\"c\", \"d\");\n" +
                "manager.addEdge(\"d\", \"e\");\n" +
                "manager.addEdge(\"e\", \"f\");");

        // Act
        search = new GraphSearch(manager.getGraph(), manager.getGraphNodes());
        graphPath = search.graphSearch("a", "f", GraphSearch.Search.RWS);

        String hasEndingNode = graphPath.toString();

        if(hasEndingNode.contains("f")){
            assertEquals("Expect correct path:", "f" , "f");
        }
    }


}
