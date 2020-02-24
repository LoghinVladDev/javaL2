/**
 * Cost Class
 * [BONUS TASK]
 *
 * Class containing representation of graph cost matrix and containing Kruskal MST implementation
 *
 * @author loghin Vlad
 */
public class Cost {
    private int[][] costs;
    private int nodeCount;
    private int existingRoadsCount;

    private boolean initialised;

    /**
     * Constructor
     * Default
     */
    public Cost(){
        this.nodeCount = 0;
        this.costs = null;
    }

    /**
     * Constructor
     * Overloaded
     * @param nodeCount value of nodes in Graph object
     */
    public Cost(int nodeCount){
        this.nodeCount = nodeCount;
        this.costs = new int[nodeCount][nodeCount];
    }

    /**
     * Getter for node count in Graph object
     * @return value of node count
     */
    public int getNodeCount(){
        return this.nodeCount;
    }

    /**
     * Getter for costs matrix
     * @return pointer to the requested matrix
     */
    public int[][] getCosts(){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);
        return this.costs;
    }

    /**
     * Getter for the cost value from a node to another
     * @param from node index from
     * @param to node index to
     * @return cost value from node to node
     */
    public int getCost(int from, int to){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);
        return this.costs[from][to];
    }

    /**
     * Setter for Graph size (node count)
     * @param nodeCount value of Graph size
     */
    public void setSize(int nodeCount){
        this.nodeCount = nodeCount;
        this.costs = new int[nodeCount][nodeCount];
    }

    /**
     * Internal function used to generate non existing roads
     * @param nonExistingRoadsCount value of how many edges do not exist
     */
    private void cleanCostMatrix(int nonExistingRoadsCount){
        for(int i = 0; i < this.nodeCount; i++)
            this.costs[i][i] = -1;

        while(nonExistingRoadsCount-- > 0){
            int i = (int)(Math.random() * this.nodeCount);
            int j = (int)(Math.random() * this.nodeCount);

            while(this.costs[i][j] == -1){
                i = (int)(Math.random() * this.nodeCount);
                j = (int)(Math.random() * this.nodeCount);
            }

            this.costs[i][j] = -1;
        }
    }

    /**
     * Internal function used to generate edge costs for existing edges
     * @param edgeCount value of how many edges are to be generated
     * @param costRange value containing cost range
     */
    private void generateCosts(int edgeCount, int costRange){
        while(edgeCount-- > 0){
            int i = (int)(Math.random() * this.nodeCount);
            int j = (int)(Math.random() * this.nodeCount);

            while(this.costs[i][j] > 0 || this.costs[i][j] == -1){
                i = (int)(Math.random() * this.nodeCount);
                j = (int)(Math.random() * this.nodeCount);
            }

            int cost = (int)((Math.random() * costRange))+1;
            this.costs[i][j] = cost;
        }

    }

    /**
     * Getter for number of edges
     * @return value of edge count (valid roads)
     */
    public int getExistingRoadsCount(){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);

        return this.existingRoadsCount;
    }

    /**
     * Method calls the generation of a cost matrix. Needs to be recalled if another order has been assigned.
     *
     * @param costRange value of cost range
     */
    public void generateRandomCosts(int costRange){
        if(this.nodeCount == 0)
            ErrorHandler.handleError(ErrorHandler.Error.costsNullNodeCount);

        int totalCosts = this.nodeCount * (this.nodeCount - 1);
        int nonExistingRoads = (int)(Math.random() * totalCosts);
        this.cleanCostMatrix(nonExistingRoads);

        this.existingRoadsCount = totalCosts - nonExistingRoads;

        this.generateCosts(this.existingRoadsCount, costRange);

        this.initialised = true;
    }

    /**
     * Overloaded toString method
     * @return String containing interpretation of Cost object
     */
    public String toString(){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);

        String result = "";

        for(int i = 0; i < this.nodeCount; i++){
            for(int j = 0; j < this.nodeCount; j++)
                result = result + this.costs[i][j] + " ";
            result = result + "\n";
        }

        return result;
    }
}
