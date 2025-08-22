import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GraphManagerTest {
    private GraphManager manager;

    @Before
    public void setUp(){
        manager = new GraphManager();
        System.out.println("This is the Graph Manager setup");
    }

    @After
    public void tearDown(){
        manager = null;
        System.out.println("This is the graph manager teardown");
        System.out.println("===========================");
    }

    @Test
    public void testAddNode() {
        // No Arrange

        // Act
        manager.addNode("a");
        manager.addNode("b");
        manager.addNode("c");
        manager.addNode("d");
        manager.addNode("e");
        manager.addNode("f");
        manager.addNode("g");

        System.out.println("Add Node Test:\n" +
                "\tmanager.addNode(\"a\");\n" +
                "\tmanager.addNode(\"b\");\n" +
                "\tmanager.addNode(\"c\");\n" +
                "\tmanager.addNode(\"d\");\n" +
                "\tmanager.addNode(\"e\");\n" +
                "\tmanager.addNode(\"f\");\n" +
                "\tmanager.addNode(\"g\");\n");

        System.out.println("Expected: " +
                        "\tNumber of nodes: 7\n" +
                        "\tNodes: [a, b, c, d, e, f, g]\n" +
                        "\tNumber of edges: 0\n" +
                        "\tEdges:\n");

        // Assert
        assertTrue("Test if node was added: ", manager.hasNode("a"));
        assertTrue("Test if node was added: ", manager.hasNode("b"));
        assertTrue("Test if node was added: ", manager.hasNode("c"));
        assertTrue("Test if node was added: ", manager.hasNode("d"));
        assertTrue("Test if node was added: ", manager.hasNode("e"));
        assertTrue("Test if node was added: ", manager.hasNode("f"));
        assertTrue("Test if node was added: ", manager.hasNode("g"));

        System.out.println("\nOutcome: ");
        System.out.println(manager.toString());

    }

    @Test
    public void testAddListOfNodes() {
        // Arrange
        String[] listOfNodes = {"a","b","c","d","e","f","g"};
        System.out.println("Add List of Nodes: " + Arrays.toString(listOfNodes));

        // Act
        manager.addNodes(listOfNodes);
        System.out.println("Expected: " +
                "\tNumber of nodes: 7\n" +
                "\tNodes: [a, b, c, d, e, f, g]\n" +
                "\tNumber of edges: 0\n" +
                "\tEdges:\n");

        // Assert
        assertTrue("Test if node was added: ", manager.hasNode("a"));
        assertTrue("Test if node was added: ", manager.hasNode("b"));
        assertTrue("Test if node was added: ", manager.hasNode("c"));
        assertTrue("Test if node was added: ", manager.hasNode("d"));
        assertTrue("Test if node was added: ", manager.hasNode("e"));
        assertTrue("Test if node was added: ", manager.hasNode("f"));
        assertTrue("Test if node was added: ", manager.hasNode("g"));

        System.out.println("\nOutcome: ");
        System.out.println(manager.toString());
    }

    @Test
    public void testAddNodeAndAddNodesTogether() {
        // Arrange
        String[] nodesList = {"1","2","3","a1", "a2", "a3"};

        // Act
        manager.addNode("a");
        manager.addNode("b");
        manager.addNode("c");
        manager.addNodes(nodesList);

        System.out.println("addNode and addNodes Together Test\n" + Arrays.toString(nodesList) +
                "\nmanager.addNode(\"a\");\n" +
                "manager.addNode(\"b\");\n" +
                "manager.addNode(\"c\");\n");

        System.out.println("Expect: " +
                "Number of nodes: 9\n" +
                "Nodes: [a1, a, 1, a2, b, 2, a3, c, 3]\n" +
                "Number of edges: 0\n" +
                "Edges:\n");

        // Assert
        assertTrue("Test if node was added: ", manager.hasNode("a"));
        assertTrue("Test if node was added: ", manager.hasNode("b"));
        assertTrue("Test if node was added: ", manager.hasNode("c"));
        assertTrue("Test if node was added: ", manager.hasNode("1"));
        assertTrue("Test if node was added: ", manager.hasNode("2"));
        assertTrue("Test if node was added: ", manager.hasNode("3"));
        assertTrue("Test if node was added: ", manager.hasNode("a1"));
        assertTrue("Test if node was added: ", manager.hasNode("a2"));
        assertTrue("Test if node was added: ", manager.hasNode("a3"));

        System.out.println("\nOutcome: ");
        System.out.println(manager.toString());
    }

    @Test
    public void testWrongNodeListFormatException() {
        //Arrange
        Integer[] listNodes = {1,2,3,4,5,6,7,8,9};
        System.out.println("\nTry to insert integers:" + Arrays.toString(listNodes) + "\n");

        //Act
        GraphManagerException exception =assertThrows(GraphManagerException.class, () ->{
            manager.addNodes(listNodes);
        });

        System.out.println("Expect GraphManagerException\n");

        // Assert
        assertEquals("Node must be a String array.", exception.getMessage());

        System.out.println(exception.getMessage() + "\n");
    }

    @Test
    public void testWrongNodeFormatException() {
        //Arrange
        Integer node = 1;

        System.out.println("\nTry to insert integer:" + node.toString() + "\n");


        //Act
        GraphManagerException exception =assertThrows(GraphManagerException.class, () ->{
            manager.addNode(node);
        });

        System.out.println("Expect GraphManagerException\n");

        // Assert
        assertEquals("Node must be a String.", exception.getMessage());

        System.out.println(exception.getMessage() + "\n");
    }

    @Test
    public void testAddEdge() {
        // No arrange needed

        System.out.println("Test Add Edge: \n");

        // Act
        manager.addEdge("a", "b");

        System.out.println("manager.addEdge(a,b) \n");
        System.out.println("Expected: \n" +
                "Number of nodes: 2\n" +
                "Nodes: [a, b]\n" +
                "Number of edges: 1\n" +
                "Edges:\n" +
                " a -> b\n");

        // Assert
        assertTrue("Test if edge was added: ", manager.hasEdge("a","b"));

        System.out.println(manager.toString());
    }

    @Test
    public void testAddEdges(){
        // No arrange needed
        System.out.println("Test Add Edges: \n");

        // Act
        manager.addEdge("a", "b");
        manager.addEdge("a", "c");
        manager.addEdge("a", "d");
        manager.addEdge("b", "c");
        manager.addEdge("b", "d");
        manager.addEdge("1", "2");
        manager.addEdge("2", "3");
        manager.addEdge("3", "4");
        manager.addEdge("a", "1");

        System.out.println("manager.addEdge(\"a\", \"b\");\n" +
                "manager.addEdge(\"a\", \"c\");\n" +
                "manager.addEdge(\"b\", \"c\");\n" +
                "manager.addEdge(\"a\", \"d\");\n" +
                "manager.addEdge(\"b\", \"d\");\n" +
                "manager.addEdge(\"1\", \"2\");\n" +
                "manager.addEdge(\"2\", \"3\");\n" +
                "manager.addEdge(\"3\", \"4\");\n" +
                "manager.addEdge(\"a\", \"1\");");

        System.out.println("\nExpected: " +
                "Number of nodes: 8\n" +
                "Nodes: [a, 1, b, 2, c, 3, d, 4]\n" +
                "Number of edges: 9\n" +
                "Edges:\n" +
                " a -> b\n" +
                " a -> c\n" +
                " a -> d\n" +
                " b -> c\n" +
                " b -> d\n" +
                " 1 -> 2\n" +
                " 2 -> 3\n" +
                " 3 -> 4\n" +
                " a -> 1\n");

        // Assert
        assertTrue("Test if edge was added: ", manager.hasEdge("a","b"));
        assertTrue("Test if edge was added: ", manager.hasEdge("a","c"));
        assertTrue("Test if edge was added: ", manager.hasEdge("a","d"));
        assertTrue("Test if edge was added: ", manager.hasEdge("b","c"));
        assertTrue("Test if edge was added: ", manager.hasEdge("b","d"));
        assertTrue("Test if edge was added: ", manager.hasEdge("1", "2"));
        assertTrue("Test if edge was added: ", manager.hasEdge("2", "3"));
        assertTrue("Test if edge was added: ", manager.hasEdge("3", "4"));
        assertTrue("Test if edge was added: ", manager.hasEdge("a", "1"));

        System.out.println(manager.toString());
    }

    @Test
    public void testRemoveNode(){
        // Arrange
        System.out.println("\nTest Remove Node: \n");
        String[] nodesList = {"a", "b", "c", "d", "e", "f"};

        manager.addNodes(nodesList);

        System.out.println("Adding nodes: "+
                "String[] nodesToAdd = {\"a\", \"b\", \"c\", \"d\", \"e\", \"f\"};\n" +
                "for (String node : nodesToAdd) {\n" +
                "   manager.addNode(node);\n" +
                "}\n");

        System.out.println("Current state of Graph Manager: \n" + manager.toString() + "\n");

        // Act
        for (String nodeRemoval : nodesList) {
            manager.removeNode(nodeRemoval);
        }

        // Assert
        assertEquals("\nTest if the ending result of manager is all empty: \n",
                "Number of nodes: 0\n" +
                "Nodes: []\n" +
                "Number of edges: 0\n" +
                "Edges:\n", manager.toString());

        System.out.println("End Result:\n" + manager.toString());
    }

    @Test
    public void testRemoveEdges(){
        // Arrange
        System.out.println("Test Remove Edges: \n");
        manager.addEdge("a", "b");
        manager.addEdge("a", "c");
        manager.addEdge("a", "d");
        manager.addEdge("b", "c");
        manager.addEdge("b", "d");
        manager.addEdge("1", "2");
        manager.addEdge("2", "3");
        manager.addEdge("3", "4");
        manager.addEdge("a", "1");

        System.out.println("Adding Edges:\n"+
                "manager.addEdge(\"a\", \"b\");\n" +
                "manager.addEdge(\"a\", \"c\");\n" +
                "manager.addEdge(\"b\", \"c\");\n" +
                "manager.addEdge(\"a\", \"d\");\n" +
                "manager.addEdge(\"b\", \"d\");\n" +
                "manager.addEdge(\"1\", \"2\");\n" +
                "manager.addEdge(\"2\", \"3\");\n" +
                "manager.addEdge(\"3\", \"4\");\n" +
                "manager.addEdge(\"a\", \"1\");");

        System.out.println("\nState of the graph: " + manager.toString());

        // Act
        manager.removeEdge("a", "b");
        manager.removeEdge("a", "c");
        manager.removeEdge("a", "d");
        manager.removeEdge("b", "c");
        manager.removeEdge("b", "d");

        // Assert
        assertEquals("\nTest if the ending result of manager is all empty: \n",
                "Number of nodes: 8\n" +
                        "Nodes: [a, 1, b, 2, c, 3, d, 4]\n" +
                        "Number of edges: 4\n" +
                        "Edges:\n" +
                        " 1 -> 2\n" +
                        " 2 -> 3\n" +
                        " 3 -> 4\n" +
                        " a -> 1\n", manager.toString());

        System.out.println("End Result:\n" + manager.toString());
    }

    @Test
    public void testRemoveNonexistentNode() {
        // Arrange
        System.out.println("Test Add Duplicate Edges: \n");
        manager.addNode("c");

        // Act
        GraphManagerException exception = assertThrows(GraphManagerException.class, () ->{
            manager.removeNode("z");
        });

        System.out.println("Expect exception, tried removing node z \n");

        // Assert
        assertEquals("Node does not exist: z", exception.getMessage());
        System.out.println("Exception: " + exception.getMessage());
    }

    @Test
    public void testRemoveNonexistentEdge() {
        // Arrange
        System.out.println("Test Add Duplicate Edges: \n");
        manager.addEdge("a", "b");

        // Act
        GraphManagerException exception = assertThrows(GraphManagerException.class, () ->{
            manager.removeEdge("z", "asdasd");
        });

        System.out.println("Expect exception, tried removing edge \"z\" to \"asdasd\". ");

        // Assert
        assertEquals("Edge does not exist from \"z\" to \"asdasd\".", exception.getMessage());
        System.out.println("Exception: " + exception.getMessage());
    }

    @Test
    public void testAddEdgeExceptionWrongParameter(){
        // No arrange
        System.out.println("Test Exception Add Edges: \n");

        // Act
        GraphManagerException exception = assertThrows(GraphManagerException.class, () ->{
            manager.addEdge("", "");
        });

        // Assert
        assertEquals("Cannot add edge to null nodes. Check that the provided nodes are of type String.", exception.getMessage());
        System.out.println("Exception: " + exception.getMessage());
    }

    @Test
    public void testAddDuplicateEdge() {
        // Arrange
        System.out.println("Test Add Duplicate Edges: \n");
        manager.addEdge("a", "b");

        // Act
        GraphManagerException exception = assertThrows(GraphManagerException.class, () ->{
            manager.addEdge("a", "b");
        });

        System.out.println("Expect duplicate edges a -> b\n");

        // Assert
        assertEquals("Duplicate edge detected: a -> b", exception.getMessage());
        System.out.println("Exception: " + exception.getMessage());
    }

    @Test
    public void testOutputGraphException(){
        System.out.println("Test Output Graph Exception: \n");
        try{
            System.out.println("Enter empty directory.\n");
            manager.outputGraph("");
            fail("Exception should have been thrown.");
        }catch (Exception e){
            System.out.println("Caught exception: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            assertEquals("Graph Error, could not write to file.", e.getMessage());
        }
    }

    @Test
    public void testOutputDOTGraphException(){
        try{
            System.out.println("Test Output DOT Graph Exception: \n");
            System.out.println("Empty Directory  \n");
            manager.outputDOTGraph("");
            fail("Exception should have been thrown.");
        }catch (Exception e){
            System.out.println("Caught exception: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            assertEquals("Output DOT Graph Error, could not write to file.", e.getMessage());
        }
    }

    @Test
    public void testOutputGraphicsWrongFormatException(){
        try{
            System.out.println("Test Output Graphics Graph Exception: \n");
            System.out.println("Empty Directory and Erroneous format \"lolcat\" \n");
            manager.outputGraphics("", "lolcat");
            fail("Exception should have been thrown.");
        }catch (Exception e){
            System.out.println("Caught exception: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            assertEquals("Format must be png or empty filePath.", e.getMessage());
        }
    }
}
