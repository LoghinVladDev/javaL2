import java.util.Arrays;

public class Bonus {
    public enum costOrder{
        shortest, longest
    }

    private Problem problem;
    private Cost costs;
    private KCost[] orderedCosts;
    private costOrder orderOfAssignment;

    public Bonus(Problem problem){
        this.problem = problem;
        this.orderOfAssignment = costOrder.shortest;
        this.assignRandomCosts(10);
    }

    public Bonus(Problem problem, int costRange){
        this.problem = problem;
        this.orderOfAssignment = costOrder.shortest;
        this.assignRandomCosts(costRange);
    }

    public void setOrderOfAssignment(costOrder order){
        this.orderOfAssignment = order;
    }

    private KCost[] getAllCosts(){
        KCost[] result = new KCost[this.costs.getExistingRoadsCount()];
        for(int i = 0, length = 0; i < this.costs.getNodeCount(); i++)
            for(int j = 0; j < this.costs.getNodeCount(); j++)
                if(this.costs.getCost(i,j) > 0)
                    result[length++] = new KCost(i, j, this.costs.getCost(i,j));

        return result;
    }

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

    public void assignRandomCosts(int costRange){
        this.costs = new Cost();
        costs.setSize(problem.getClients().length);
        costs.generateRandomCosts(costRange);
        this.orderedCosts = this.orderCosts();
    }

    public void printCosts(){
        System.out.println("Costs in order\n" + Arrays.toString(this.orderedCosts));
    }
}

class KCost{
    private int from;
    private int to;
    private int cost;

    public KCost(int from, int to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }

    public String toString(){
        return "From : " + this.from + ", To : " + this.to + " with COST : " + this.cost;
    }
}

