public class Cost {
    private int[][] costs;
    private int nodeCount;
    private int existingRoadsCount;

    private boolean initialised;

    public Cost(){
        this.nodeCount = 0;
        this.costs = null;
    }

    public Cost(int nodeCount){
        this.nodeCount = nodeCount;
        this.costs = new int[nodeCount][nodeCount];
    }

    public int getNodeCount(){
        return this.nodeCount;
    }

    public int[][] getCosts(){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);
        return this.costs;
    }

    public int getCost(int from, int to){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);
        return this.costs[from][to];
    }

    public void setSize(int nodeCount){
        this.nodeCount = nodeCount;
        this.costs = new int[nodeCount][nodeCount];
    }

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

    public int getExistingRoadsCount(){
        if(!this.initialised)
            ErrorHandler.handleError(ErrorHandler.Error.noCostsAssigned);

        return this.existingRoadsCount;
    }

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
