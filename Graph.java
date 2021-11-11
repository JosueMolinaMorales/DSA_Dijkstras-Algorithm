import java.util.*;

public class Graph {
    private HashSet <GNode> nodes;

    public Graph() {
        nodes = new HashSet<GNode>();
    }

    public boolean addEdge(GNode vertex1, GNode vertex2, double weight) {
        return vertex1.getEdges().add(new Edge(vertex2, weight)) && vertex2.getEdges().add(new Edge(vertex1, weight));
    }

    public boolean addVertex(GNode vertex) {
        return nodes.add(vertex);
    }

    public void printGraph() {
        for(GNode v: nodes) {
            System.out.print("vertex name: " + v.getData() + ":\n");
            for(Edge e: v.getEdges()) {
                System.out.print("destVertex: " + e.getDestVertex().getData() + ", weight: " + e.getWeight() + "\n");
            }
            System.out.print("\n");
        }
    }

    /**
     * Dijkstra's shortest path. algorithm from the textbook.
     * @param start start node
     * @param end end node
     */
    public void dijkstraShortestPath(GNode start, GNode end){
        PriorityQueue<GNode> minQueue = new PriorityQueue<GNode>();// min queue to keep the smallest distance on top.

        //set all node distance to infinity, while adding them to queue
        for(GNode node : nodes){
            node.setDistance(Double.POSITIVE_INFINITY);
        }
        start.setDistance(0);
        for(GNode node: nodes){
            minQueue.add(node);
        }
        
        GNode currNode;
        while(!minQueue.isEmpty()){
            currNode = minQueue.peek();
            //System.out.println(currNode.getData() + " " + currNode.getDistance());
            for(Edge edge : currNode.getEdges()){
                relax(currNode, edge.getDestVertex());
            }
            minQueue.remove();
           //System.out.println(minQueue.toString()); 
        }
        
        System.out.print("Shortest path from " + start.getData() + " to " + end.getData() +  " is ");

        LinkedList<GNode> pathFromStartToEnd = new LinkedList<>();
        GNode tempNode = end;
        while(tempNode != null){
            pathFromStartToEnd.addFirst(tempNode);
            tempNode = tempNode.getPrevNode();
        }

        for(int i = 0; i < pathFromStartToEnd.size(); i++){
            if(i < pathFromStartToEnd.size()-1)
                System.out.print(pathFromStartToEnd.get(i).getData() + " --> ");
            else
                System.out.print(pathFromStartToEnd.getLast().getData());
        }
        System.out.println("\nWith a total weight of : " + end.getDistance());
    }

    /**
     * Relax function used for Dijkstra's algorithm. Inspired from the textbook.
     * @param vertex1 vertex that leads to vertex2
     * @param vertex2
     */
    private void relax(GNode vertex1, GNode vertex2){
        if(vertex2.getDistance() > vertex1.getDistance() + vertex1.getWeightedEdge(vertex2)){
            // System.out.println("Relaxing " + vertex1.getData() + " and " + vertex2.getData() + " new weight is: " + 
            //                     (vertex1.getDistance() + vertex1.getWeightedEdge(vertex2)));
            vertex2.setDistance(vertex1.getDistance() + vertex1.getWeightedEdge(vertex2));
            vertex2.setPrevNode(vertex1);
        }
    }

    /**
     * public function for DFS
     * @param start The start of the DFS algorithm
     */
    public void depthFirstSearch(GNode start){
        HashMap<GNode, Boolean> visited = new HashMap<>();
        for(GNode node: nodes){
            visited.put(node, false);
        }
        //System.out.println(visited.get(start));

        dfs(start,visited, start);
    }

    /**
     * Practice with graphs, Depth First Search
     * @param currNode currNode being visited
     * @param visited Hash map of nodes that have already been visited or not
     * @param lastNode the last node 
     */
    private void dfs(GNode currNode, HashMap<GNode, Boolean> visited, GNode lastNode){
        //mark node as visited
        visited.replace(currNode, false, true);
        System.out.println("CurrNode is: " + currNode.getData() + " The previous node is: " + lastNode.getData());
        //for every unvisited node adjacent to currNode
        for(Edge edge: currNode.getEdges()){
            if(!visited.get(edge.getDestVertex())){
                dfs(edge.getDestVertex(), visited, currNode);
            }
        }
    }

    /**
     * Gets the node from the graph
     * @param data the information being looked for
     * @return returns the node, or null if not found 
     */
    public GNode getNode(int data){
        for(GNode node : nodes){
            if(node.getData() == data){
                return node;
            }
        }
        return null;
    }
}
