import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Bonus Class
 * [BONUS TASK]
 *
 * Contains experimental solution for bonus requirement
 *
 * @author Loghin Vlad
 */
public class Bonus {

    /**
     * costOrder enum containing edge sorting orders
     */
    public enum costOrder{
        shortest, longest
    }

    private Problem problem;
    private Cost costs;
    private KCost[] orderedCosts;
    private costOrder orderOfAssignment;
    private int totalCost;

    /**
     * Bonus Class Constructor [Overloaded]
     *
     * Builds the object based on the problem and generates a default solution (random with cost range 10)
     *
     * @param problem pointer to a problem object
     */
    public Bonus(Problem problem){
        this.problem = problem;
        this.orderOfAssignment = costOrder.shortest;
        this.assignRandomCosts(10);
    }

    /**
     * Bonus Class Constructor [Overloaded]
     *
     * Builds the object based on the problem and generates a random custom solution with costs based on given range
     *
     * @param problem pointer to a problem object
     * @param costRange range of costs found in graph costs matrix
     */
    public Bonus(Problem problem, int costRange){
        this.problem = problem;
        this.orderOfAssignment = costOrder.shortest;
        this.assignRandomCosts(costRange);
    }

    /**
     * setter for edge sorting order for Kruskal
     * @param order given order
     */
    public void setOrderOfAssignment(costOrder order){
        this.orderOfAssignment = order;
    }

    /**
     * getter for cost matrix
     * @return pointer to the requested matrix
     */
    private KCost[] getAllCosts(){
        KCost[] result = new KCost[this.costs.getExistingRoadsCount()];
        for(int i = 0, length = 0; i < this.costs.getNodeCount(); i++)
            for(int j = 0; j < this.costs.getNodeCount(); j++)
                if(this.costs.getCost(i,j) > 0)
                    result[length++] = new KCost(i, j, this.costs.getCost(i,j));

        return result;
    }

    /**
     * Orders edges by default or overridden order
     * @return pointer to an array containing edges in requested order
     */
    private KCost[] orderCosts(){
        KCost[] orderedCosts = this.getAllCosts();

        for(int i = 0; i < orderedCosts.length - 1; i++)
            for(int j = i + 1; j < orderedCosts.length; j++)
                if(this.orderOfAssignment == costOrder.shortest) {
                    if (orderedCosts[i].getCost() > orderedCosts[j].getCost()) {
                        KCost aux = orderedCosts[i];
                        orderedCosts[i] = orderedCosts[j];
                        orderedCosts[j] = aux;
                    }
                }
                else if(this.orderOfAssignment == costOrder.longest){
                    if(orderedCosts[i].getCost() < orderedCosts[j].getCost()){
                        KCost aux = orderedCosts[i];
                        orderedCosts[i] = orderedCosts[j];
                        orderedCosts[j] = aux;
                    }
                }
        return orderedCosts;
    }

    /**
     * Creates a Cost object with a random costs matrix
     * @param costRange given cost range
     */
    public void assignRandomCosts(int costRange){
        this.costs = new Cost();
        costs.setSize(problem.getClients().length);
        costs.generateRandomCosts(costRange);
        this.orderedCosts = this.orderCosts();
    }

    /**
     * Prints all edges with costs and edges in MST
     */
    public void printCosts(){
        System.out.println("Costs in order\n"
                + Arrays.toString(this.orderedCosts)
                + "\n\nOptimised Costs"
                + Arrays.toString(new Graph(this.costs.getNodeCount(), this.orderedCosts.length, orderedCosts).getKruskalOptimizedEdges()));
    }

    /**
     * Sorts edges from MST by from node
     * @param edges pointer to the array containing MST edges
     * @return pointer to the ordered array
     */
    private static KCost[] sortByFrom(KCost[] edges){
        for(int i = 0; i < edges.length - 1; i++)
            for(int j = i+1; j < edges.length; j++)
                if(edges[i].getFrom() > edges[j].getFrom())
                {
                    KCost aux = edges[i];
                    edges[i] = edges[j];
                    edges[j] = aux;
                }
        return edges;
    }

    /**
     * Checks whether array contains given value TODO : deprecate
     * Used to check if all nodes have been visited with a tour
     * @param visited pointer to the visited array
     * @param value value being looked for (0 = not visited)
     * @return true if exists !visited node, false otherwise
     */
    private static boolean contains(int[] visited, int value){
        for( int i : visited )
            if(i == value)
                return true;
        return false;
    }

    /**
     * Gets the order in which nodes have to be put in a tour to get minimum cost path
     * Creates Graph object and applies Kruskal to generate MST edges
     * @return pointer to an array containing the order in which nodes have to be taken
     */
    public int[] generateTourOrder(){
        Graph G = new Graph(this.costs.getNodeCount(), this.orderedCosts.length, orderedCosts);
        KCost[] edges = G.getKruskalOptimizedEdges();

        int[] visited = new int[this.costs.getNodeCount()];
        int[] order = new int[this.costs.getNodeCount()];
        int nodeCount = 0;

        while(contains(visited, 0)){
            LinkedList<Integer> path = new LinkedList<>();
            for(int i = 0; i < this.costs.getNodeCount(); i++)
                if(visited[i] == 0){
                    path.add(i);
                    visited[i] = 1;
                    break;
                }

            for( KCost edge : edges ){
                if(edge.getTo() == path.peekFirst() && visited[edge.getFrom()] == 0){
                    path.addFirst(edge.getFrom());
                    visited[edge.getFrom()] = 1;
                }
                else if(edge.getFrom() == path.peekLast() && visited[edge.getTo()] == 0){
                    path.add(edge.getTo());
                    visited[edge.getTo()] = 1;
                }
            }

            while(!path.isEmpty())
                order[nodeCount++] = path.removeFirst();
        }
        this.totalCost = G.getTotalCost();

        return order;
    }

    /**
     * Getter for the totalCost of the MST
     * @return the total cost of the MST
     */
    public int getTotalCost(){
        return this.totalCost;
    }
}

/**
 * Class KCost
 * [BONUS TASK]
 *
 * Internally used class to contain Kruskal Edges (Kruskal edge with Cost)
 *
 * @author Loghin Vlad
 */
class KCost{
    private int from;
    private int to;
    private int cost;

    /**
     * Constructor
     * @param from where the edge starts
     * @param to where the edge ends
     * @param cost edge cost
     */
    public KCost(int from, int to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    /**
     * Getter for edge cost
     * @return edge cost
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * Getter for From node
     * @return from node
     */
    public int getFrom(){
        return this.from;
    }

    /**
     * Getter for To node
     * @return to node
     */
    public int getTo(){
        return this.to;
    }

    /**
     * Overload of toString method
     * @return String describing edge
     */
    public String toString(){
        return "From : " + this.from + ", To : " + this.to + " with COST : " + this.cost;
    }
}

