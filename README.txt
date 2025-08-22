# Name: Edger Alexander Bautista Serrano

## Introduction
It is a java application that parses and manipulates graphs.
The application parses basic .dot format and allows for adding
new edges, new nodes, outputting to a txt, .dot, and .png file.

## Dependencies
- Graphviz 0.18.1
- JUnit 4.13.1

## Building and Running the Project
After extracting the zip file the project is in:
    1. Run mvn clean install
    2. Run mvn package

## Example usage of APIs
    1. parseGraph(String filepath)
        > To use parseGraph we first need to make a new Lexer and GraphParser
            The parseGraph takes in lexer and graph parser as inputs.
            Then the graph is parsed and added into graph manager where the 
            edges and nodes will be stored in graph manager.

        input.dot:
            digraph{
                a -> b;
                b -> c;
                c -> d;
            }

        String fileConent = Files.readString(Paths.get("input.dot"))

        Lexer lexer = new Lexer(fileContent);
        GraphManager graphManager = new GraphManager();
        GraphParser graphParser = new GraphParser(lexer, graphManager);

        graphParser.parseGraph();

    2. toString() or System.out.println(graphManager)
        > Prints the number and labels of nodes, the number of edges,
            the nodes and the edge direction of edges.
        
        graphParser.toString();

        Output:
            Number of Nodes: 4
            Nodes: [a, b, c, d]
            Number of edges: 3
            Edges:
                a -> b
                b -> c
                c -> d

    3. outputGraph(String filePath);
        > outputs the graph into a file.

        parseGraph.outputGraph(filePath);

        output:
            digraph{
                a -> b;
                b -> c;
                c -> d;
            }
    
    4. addNode(String label)
        > Adds a node. In code its handled as Object to make sure that
            the user inputs a String and not something else. It also 
            checks for duplicate node input.
        
        graphManager.addNode("nodeName");

        In graph manager:
                Number of Nodes: 5
                Nodes: [a, b, c, d, nodeName]
                Number of edges: 3
                Edges:
                    a -> b
                    b -> c
                    c -> d

    5. addNodes(String[] label)
        > Uses addNode(String label) to add a series of nodes. It also
            checks that the user inputs a String[] and not something else.

        String[] nodeList = {"ex1", "ex2", "ex3"};
        graphManager.addNodes(nodeList);

        In graph manager:
                Number of Nodes: 8
                Nodes: [a, b, c, d, nodeName, ex1 ,ex2, ex3]
                Number of edges: 3
                Edges:
                    a -> b
                    b -> c
                    c -> d

    6. addEdge(String srcLabel, String dstLabel)
        > Adds edges and also adds them as nodes if they do not already exist
            as nodes. It also checks for duplicates. It will add edges if
            there was a graph imported beforehand.

        graphManager.addEdge("g", "f"); 

        In graph manager:
                Number of Nodes: 10
                Nodes: [a, b, c, d, nodeName, ex1 ,ex2, ex3, g, f]
                Number of edges: 4
                Edges:
                    a -> b
                    b -> c
                    c -> d
                    g -> f
    
    7. outputDOTGraph(String path)
        > Stores the imported graph into a DOT file

        graphManager.outputDOTGraph("output");

        In graph manager:
                Number of Nodes: 10
                Nodes: [a, b, c, d, nodeName, ex1 ,ex2, ex3, g, f]
                Number of edges: 4
                Edges:
                    a -> b
                    b -> c
                    c -> d

        Output.dot:
            digraph{
                a -> b;
                b -> c;
                c -> d;
                g -> f;
                nodeName;
                ex1;
                ex2;
                ex3;
            }

    8. outputGraphics(String path, String format)
        > Outputs the imported graph into a PNG file

        graphManager.outputGraphics("imgOut", "png");

## Running Tests
    - Run mvn test

## GitHub Links

Github rep: https://github.com/eabauti2/CSE-464-2025-eabauti2

First commit -> feat1: https://github.com/eabauti2/CSE-464-2025-eabauti2/tree/feat1

Second commit -> feat2: https://github.com/eabauti2/CSE-464-2025-eabauti2/tree/feat2

Third commit -> feat3: https://github.com/eabauti2/CSE-464-2025-eabauti2/tree/feat3

Fourth commit -> feat4: 


### Example Inputs and Outputs

Graph Manager 
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


===========================================
Graph Parser

   public void testValidGraph(){
        // Arrange

        System.out.println("Test Parse Simple Graph: \n");

        input = "digraph { a -> b; b -> c; c -> d; }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println(input + "\n");

        // Act
        graphParser.parseGraph();
        System.out.println("Expected all edges to be added: \n");

        // Assert
        assertTrue("Test if edge was added: ", graphManager.hasEdge("a","b"));
        assertTrue("Test if edge was added: ", graphManager.hasEdge("b","c"));
        assertTrue("Test if edge was added: ", graphManager.hasEdge("c","d"));

        assertEquals("Test count of edges", 3, graphManager.getEdgeCount());

        System.out.println("Outcome: ");
        System.out.println(graphManager.toString());

    }

    @Test
    public void testGraphParserException(){
        // Arrange
        input = "digraph { -> -> a; }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Graph Parser Syntax Error: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.parseGraph();
        });
        System.out.println("Outcome: Expected ID at line 1.\n");

        // Assert
        assertEquals("Expected ID at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());

    }


    @Test
    public void testMissingSemicolon(){
        // Arrange
        input = "digraph { a -> b }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Missing Semicolon: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.parseGraph();
        });
        System.out.println("Outcome: Expected ID at line 1.\n");

        // Assert
        assertEquals("Expected SEMICOLON at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    public void testMissingDigraph(){
        // Arrange
        input = " { a -> b }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Missing digraph: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.parseGraph();
        });

        System.out.println("Outcome: Expected DIGRAPH at line 1.\n");

        // Assert
        assertEquals("Expected DIGRAPH at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

========================================================

Lexer 


    @Test
    public void testLexingForSimpleGraph(){
        //Arrange
        input = "digraph{" +
                "a -> b;" +
                "c -> d;" +
                "e -> f;" +
                "g -> h;" +
                "i -> j;" +
                "k -> l;" +
                "m -> n;" +
                "p -> o;" +
                "q -> p;" +
                "r -> q;" +
                "s -> t;" +
                "u -> v;" +
                "w -> x;" +
                "y -> z; }";
        Lexer lexer = new Lexer(input);

        System.out.println("Test Lexing Simple Graph: \n");
        System.out.println(input + "\n");

        // Act
        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getType() != Token.TokenType.EOF);

        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getType());
        System.out.println("Lexed: EOF\n");
        assertEquals("Check for RBRACE: ", Token.TokenType.RBRACE, stack.pop().getType());
        System.out.println("Lexed: RBRACE\n");;

        while(stack.peek().getType() != Token.TokenType.LBRACE){
            //System.out.println(stack.peek().toString());
            assertEquals("Check for semicolon: ", Token.TokenType.SEMICOLON, stack.pop().getType());
            System.out.println("Lexed: SEMICOLON\n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for 'to' ID: ", Token.TokenType.ID,        stack.pop().getType());
            System.out.println("Lexed: ID: \n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for arrow", Token.TokenType.ARROW,     stack.pop().getType());
            System.out.println("Lexed: ARROW\n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for 'from' ID: " ,Token.TokenType.ID,        stack.pop().getType());
            System.out.println("Lexed: ID\n");
        }

        assertEquals("Check for LBRACE: ", Token.TokenType.LBRACE, stack.pop().getType());
        System.out.println("Lexed: LBRACE\n");
        assertEquals("Check for digraph: ", Token.TokenType.DIGRAPH, stack.pop().getType());
        System.out.println("Lexed: DIGRAPH\n");
    }

    @Test
    public void testStringEndOfFile(){
        // Arrange
        input = "";
        Lexer lexer = new Lexer(input);

        System.out.println("Test EOF, No Graph\n");

        // Act
        token = lexer.nextToken();
        stack.push(token);

        System.out.println("Lexed: EOF\n");

        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getType());
        System.out.println("EOF\n");
    }


