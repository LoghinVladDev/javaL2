import java.util.Arrays;

/**
 * Graph Class
 * [BONUS TASK]
 *
 * Used to generate MST
 *
 * @author Loghin Vlad
 */
public class Graph {
    private int nodeCount;
    private int edgeCount;
    private KCost[] edges;
    private int totalCost;

    /**
     * Constructor
     * @param nodeCount value of count of nodes in graph
     * @param edgeCount value of count of edges in graph
     * @param edges pointer to the array containing edges
     */
    public Graph(int nodeCount, int edgeCount, KCost[] edges){
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.edges = Arrays.copyOf(edges,edges.length);
    }

    /**
     * Getter for MST total cost
     * @return value of MST total cost
     */
    public int getTotalCost(){
        return this.totalCost;
    }

    /**
     * Method returns a pointer to an array containing edges from MST
     * @return pointer to the requested array
     */
    public KCost[] getKruskalOptimizedEdges(){
        this.totalCost = 0;
        int selectedEdges = 0;
        KCost[] result = new KCost[this.nodeCount - 1];

        DisjointSets sets = new DisjointSets(this.nodeCount);

        for( KCost edge : this.edges ){
            int setFrom = sets.find(edge.getFrom());
            int setTo = sets.find(edge.getTo());

            if(setFrom != setTo){
                result[selectedEdges++] = edge;

                this.totalCost += edge.getCost();

                sets.merge(setFrom, setTo);

                if(selectedEdges == this.nodeCount - 1)
                    return result;
            }
        }

        ErrorHandler.handleError(ErrorHandler.Error.cannotVisitAllLocations);
        return null;
    }
}

/**
 * DisjointSets Class
 * [BONUS TASK]
 *
 * Used to represent union-find and absence of cycles in MST
 *
 * @author Loghin Vlad
 */
class DisjointSets {
    private int[] parent;
    private int[] size;
    int nodeCount;

    /**
     * Constructor
     * @param nodeCount value containing number of nodes in graph
     */
    public DisjointSets(int nodeCount){
        this.nodeCount = nodeCount;
        this.parent = new int[nodeCount];
        this.size = new int[nodeCount];

        for(int i = 0; i < nodeCount; i++){
            this.size[i] = 0;
            this.parent[i] = i;
        }
    }

    /**
     * Find function for Kruskal
     *
     * Recursive. Finds the root of a node
     *
     * @param node value of node whose root is requested
     * @return parent of node
     */
    public int find(int node) {
        if( parent[node] != node )
            parent[node] = find(parent[node]);
        return parent[node];
    }

    /**
     * Merge function for Kruskal
     *
     * Merges on root based on height of tree
     *
     * @param leftTree node from tree to be merged
     * @param rightTree node from tree to be merged
     */
    void merge(int leftTree, int rightTree){
        leftTree = this.find(leftTree);
        rightTree = this.find(rightTree);

        if(size[leftTree] > size[rightTree])
            parent[rightTree] = leftTree;
        else
            parent[leftTree] = rightTree;

        if(size[leftTree] == size[rightTree])
        size[rightTree]++;
    }
}
