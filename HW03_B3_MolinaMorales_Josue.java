import java.util.*;
import java.io.*;
public class HW03_B3_MolinaMorales_Josue {
    public static void main(String[] args){
        runTestCases();

    }

    public static void runTestCases(){
        File inFile = new File("Graph_InputFile_Hw2.txt");
        Scanner scan = null;
        StringTokenizer st1 = null;
        StringTokenizer st2 = null;
        String input = "";

        if(!inFile.exists()){
            System.out.println("File was not opened. Program terminating.");
            System.exit(1);
        }
        GNode node1 = null;
        GNode node2 = null;
        GNode startDest = null;
        GNode endDest = null;

        int num1 = 0;

        Graph myGraph = new Graph();
        //keep track of the numbers that have been added to graph
        HashSet<Integer> nodes = new HashSet<>();

        try{   
            scan = new Scanner(inFile); //each test case contains a start and end node, then a list of edges, with the weight at the end, -> node1, node2, weight
            
            while(scan.hasNext()){
                //Get the startDest and EndDest for Dijkstra's algo.
                st1 = new StringTokenizer(scan.nextLine(), " ");
                num1 = Integer.parseInt(st1.nextToken());
                startDest = new GNode(num1);
                nodes.add(num1);

                num1 = Integer.parseInt(st1.nextToken());
                nodes.add(num1);
                endDest = new GNode(num1);

                //add nodes to graph
                myGraph.addVertex(startDest);
                myGraph.addVertex(endDest);

                input = scan.nextLine();
                st1 = new StringTokenizer(input, " ");
                while(st1.hasMoreTokens()){
                    input = st1.nextToken();
                    st2 = new StringTokenizer(input, ",");
                    num1 = Integer.parseInt(st2.nextToken());
                    if(nodes.add(num1)){ //if num is not in list
                        node1 = new GNode(num1); //create a node
                        myGraph.addVertex(node1); //add node to graph
                    }else{ //if num is in list
                        node1 = myGraph.getNode(num1); //get the node 
                    }

                    num1 = Integer.parseInt(st2.nextToken());
                    if(nodes.add(num1)){
                        node2 = new GNode(num1);
                        myGraph.addVertex(node2);
                    }else{
                        node2 = myGraph.getNode(num1);
                    }

                    myGraph.addEdge(node1, node2, Double.parseDouble(st2.nextToken()));

                }
                //run algorithms
                //myGraph.printGraph();
                myGraph.dijkstraShortestPath(startDest, endDest);
                System.out.println("================================");

                //prep graph for next test case
                nodes.clear();
                myGraph = new Graph();
            }

        }catch(IOException e){
            System.out.print(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
/*

Graph myGraph = new Graph();
        GNode n1 = new GNode(5);
        GNode n2 = new GNode(4);
        GNode n3 = new GNode(3);
        GNode n4 = new GNode(2);
        GNode n5 = new GNode(7);

        myGraph.addVertex(n1);
        myGraph.addVertex(n2);
        myGraph.addVertex(n3);
        myGraph.addVertex(n4);
        myGraph.addVertex(n5);

        myGraph.addEdge(n1, n2, 3);
        myGraph.addEdge(n1, n3, 5);
        myGraph.addEdge(n1, n4, 12);
        myGraph.addEdge(n3, n4, 4);
        myGraph.addEdge(n2, n4, 7);
        myGraph.addEdge(n4, n5, 3);

        myGraph.printGraph();
        myGraph.depthFirstSearch(n1);

        myGraph.dijkstraShortestPath(n1, n5);
        myGraph.dijkstraShortestPath(n2, n3);

        */
