import java.nio.file.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "E:\\intelliJ\\CSE-464-2025-eabauti2\\graphTest\\";
        String dotFile = path+"input.dot";
        String outputFile = path+"output.txt";

        String fileContent = Files.readString(Paths.get(dotFile));

        Lexer lexer = new Lexer(fileContent);
        GraphManager graphManager = new GraphManager();
        GraphParser graphParser = new GraphParser(lexer, graphManager);

        graphParser.beginParsingGraph();

        System.out.println(graphManager);

        /*
        System.out.println("======================\n"+"Removing A");
        graphManager.removeNode("a");
        System.out.println(graphManager);
        graphManager.outputGraphics(path+"outputpng1", "png");
        System.out.println("======================\n");

        System.out.println("======================\n" + "Removing c and e");
        String[] nodes = {"c", "e"}; graphManager.removeNodes(nodes);
        System.out.println(graphManager);
        graphManager.outputGraphics(path+"outputpng2", "png");
        System.out.println("======================\n");

        graphManager.outputGraph(outputFile);
        graphManager.outputGraph(path+"output2.dot");
        graphManager.outputGraphics(path+"outputpngFinal", "png");
        */

        GraphSearch search = new GraphSearch(graphManager.getGraph(), graphManager.getGraphNodes());
        GraphPath graphPathDFS;
        GraphPath graphPathBFS;
        GraphPath graphPathRWS;

        graphPathDFS = search.graphSearch("a", "c", GraphSearch.Search.DFS);

        graphPathBFS = search.graphSearch("a", "c", GraphSearch.Search.BFS);

        //graphPathRWS = search.graphSearch("a", "c", GraphSearch.Search.RWS);
        //System.out.println("\n"+"RWS: "+graphPathRWS+"\n");


        if(graphPathDFS != null) {
            System.out.println("DFS: "+graphPathDFS + "\nDFS END=====\n");
/*
            graphPathRWS = search.graphSearch("A", "L", GraphSearch.Search.RWS);
            System.out.println("RWS: "+graphPathRWS);

            graphPathRWS = search.graphSearch("A", "L", GraphSearch.Search.RWS);
            System.out.println("RWS: "+graphPathRWS);

            graphPathRWS = search.graphSearch("a", "L", GraphSearch.Search.RWS);
            System.out.println("RWS: "+graphPathRWS);*/
        } else{
            System.out.println("No path found DFS");
        }

        if(graphPathBFS != null) {
            System.out.println("BFS: "+graphPathBFS + "\nBFS END=====\n");
        } else{
            System.out.println("No path found BFS");
        }

        System.out.println("RWS1:");
        graphPathRWS = search.graphSearch("a", "c", GraphSearch.Search.RWS);
        System.out.println(graphPathRWS+"\nEnd1\n");

        System.out.println("RWS2:");
        graphPathRWS = search.graphSearch("a", "c", GraphSearch.Search.RWS);
        System.out.println(graphPathRWS+"\nEnd2\n");

        System.out.println("RWS3:");
        graphPathRWS = search.graphSearch("a", "c", GraphSearch.Search.RWS);
        System.out.println(graphPathRWS+"\nEnd3\n");

        graphManager.outputGraph(outputFile);
        graphManager.outputGraph(path+"output2.dot");
        graphManager.outputGraphics(path+"outputpngFinal", "png");

    }
}