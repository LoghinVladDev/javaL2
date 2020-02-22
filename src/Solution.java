import java.util.Arrays;

public class Solution{
    private Problem problem;
    private Tour[] tours;
    private int tourCount;
    private int currentSelectedVehicle;
    private boolean[] clientInTour;

    public Solution(Problem problem){
        this.problem = problem;
        this.tours = null;
        this.tourCount = 0;
        this.currentSelectedVehicle = 0;
        this.clientInTour = new boolean[problem.getClients().length];

        if(!generateSolution())
            ErrorHandler.handleError(ErrorHandler.Error.solutionInvalid);
    }

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

    private Client[] sortClientsByOrder(Client[] array){
        for(int i = 0; i < array.length - 1; i++)
            for(int j = i + 1; j < array.length; j++)
                if(array[i].getVisitingTime() > array[j].getVisitingTime()) {
                    Client aux = array[i];    array[i] = array[j];    array[j] = aux;
                }
        return array;
    }

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

    private boolean allClientsVisited(){
        for( boolean i : this.clientInTour )
            if( !i )
                return false;
        return true;
    }

    private boolean generateSolution(){
        while(!allClientsVisited()) {
            if(this.currentSelectedVehicle == this.problem.getVehicles().length)
                return false;
            this.addTour(this.generateTour());
        }
        return true;
    }

    private void allocTours(int reqSize){
        this.tours = Arrays.copyOf(this.tours, Math.max(reqSize + tourCount, 2 * this.tours.length));
    }

    public String toString(){
        return "Solution found!\n" + Arrays.toString(this.tours);
    }
}