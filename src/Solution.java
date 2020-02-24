import java.util.Arrays;

/**
 * Solution Class
 * [COMPULSORY, OPTIONAL TASKS]
 *
 * @author loghin Vlad
 */
public class Solution{
    private Problem problem;
    private Tour[] tours;
    private int tourCount;
    private int currentSelectedVehicle;
    private boolean[] clientInTour;

    /**
     * Constructor
     * @param problem pointer to the problem to which a solution is being computed
     */
    public Solution(Problem problem){
        this.problem = problem;
        this.tours = null;
        this.tourCount = 0;
        this.currentSelectedVehicle = 0;
        this.clientInTour = new boolean[problem.getClients().length];

        if(!generateSolution())
            ErrorHandler.handleError(ErrorHandler.Error.solutionInvalid);
    }

    /**
     * Method used to add a tour to the solution
     * @param obj Pointer to the Tour object
     */
    private void addTour(Tour obj){
        if(this.tours == null) {
            this.tours = new Tour[1];
            this.tourCount = 1;
            this.tours[0] = obj;
            return;
        }
        else if(this.tourCount + 1 >= this.tours.length)
            this.allocTours(1);

        this.tours[this.tourCount++] = obj;
    }

    /**
     * Internal method used to sort an array of clients by order
     * @param array pointer to the array containing Clients
     * @return pointer to the sorted array
     */
    private Client[] sortClientsByOrder(Client[] array){
        for(int i = 0; i < array.length - 1; i++)
            for(int j = i + 1; j < array.length; j++)
                if(array[i].getVisitingTime() > array[j].getVisitingTime()) {
                    Client aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                }
        return array;
    }

    /**
     * Method that generates a Tour object
     * @return pointer to the Tour object
     */
    private Tour generateTour(){
        int lastClientInTour = -1;
        Client[] clients = sortClientsByOrder(Arrays.copyOf(this.problem.getClients(), this.problem.getClients().length));

        //System.out.println(Arrays.toString(clients));

        Tour result = new Tour(this.problem.getVehicles()[this.currentSelectedVehicle++]);

        for(int i = 0; i < clients.length; i++)
            if( !clientInTour[i]
                    && lastClientInTour == -1
                    || !clientInTour[i]
                    && clients[i].getVisitingTime() > result.getClients()[lastClientInTour].getVisitingTime()){
                System.out.println(clients[i]);
                result.addClients(clients[i]);
                lastClientInTour++;
                this.clientInTour[i] = true;
            }

        return result;
    }

    /**
     * Method checking whether all clients have been visited
     * @return true if all clients have been visited, false otherwise
     */
    private boolean allClientsVisited(){
        for( boolean i : this.clientInTour )
            if( !i )
                return false;
        return true;
    }

    /**
     * Method that generates a solution
     *
     * While there are clients unvisited, create a new tour
     *
     * @return true if successful, false otherwise
     */
    private boolean generateSolution(){
        while(!allClientsVisited()) {
            if(this.currentSelectedVehicle == this.problem.getVehicles().length)
                return false;
            this.addTour(this.generateTour());
        }
        return true;
    }

    /**
     * Internal method used to enlarge tours array
     * @param reqSize required size
     */
    private void allocTours(int reqSize){
        this.tours = Arrays.copyOf(this.tours, Math.max(reqSize + tourCount, 2 * this.tours.length));
    }

    /**
     * Overloaded toString method
     * @return String interpretation of the Solution Object
     */
    public String toString(){
        return "Solution found!\n" + Arrays.toString(this.tours);
    }
}