import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Tour Class
 * [COMPULSORY, OPTIONAL TASKS]
 *
 * @author loghin Vlad
 */
public class Tour{

    /**
     * Enum containing Tour constraints and exceptions
     *
     * (Bonus requires the exception that clients are not taken in order)
     */
    public enum Constraint{
        costInsteadOfOrder
    }

    private Vehicle vehicle;
    private Client[] clients;
    private int clientCount;

    private Constraint[] constraints;

    /**
     * Setter for constraints
     * @param constraints pointer to the array containing constraints
     */
    public void setConstraints(Constraint ... constraints) {
        this.constraints = Arrays.copyOf(constraints, constraints.length);
    }

    /**
     * Constructor
     * Default
     */
    public Tour(){
        this.vehicle = null;
        this.clients = null;
        this.clientCount = 0;

        this.constraints = null;
    }

    /**
     * Constructor
     * Overloaded
     * @param vehicle pointer to the Vehicle object assigned to the tour
     */
    public Tour(Vehicle vehicle){
        this.vehicle = vehicle;
        this.clients = null;
        this.clientCount = 0;

        this.constraints = null;
    }

    /**
     * Constructor
     * Overloaded
     * @param vehicle pointer to the Vehicle object assigned to the tour
     * @param clients pointer to the Client array, containing clients assigned to the tour
     */
    public Tour(Vehicle vehicle, @NotNull Client ... clients){
        this.vehicle = vehicle;
        this.clients = Arrays.copyOf(clients, clients.length);
        this.clientCount = clients.length;

        this.constraints = null;
    }

    /**
     * Getter for clients array
     * @return pointer to the clients array
     */
    public Client[] getClients(){
        this.shrinkClientsToFit();
        return this.clients;
    }

    /**
     * Overloaded toString method
     * @return String interpretation of Tour Object
     */
    public String toString(){
        this.shrinkClientsToFit();
        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);

        return "\n\tTour takes vehicle "
                + this.vehicle.toString()
                + " from depot "
                + this.vehicle.getParentDepot().getName()
                + " through clients : \n\t\t"
                + Arrays.toString(this.clients)
                + ", back to the depot "
                + this.vehicle.getParentDepot().getName()
                + "\n";
    }

    /**
     * Internal method checking validity of a tour
     *
     * @return true if tour is valid, false otherwise
     */
    private boolean checkTour(){
        this.shrinkClientsToFit();

        if(this.constraints != null)
            for( Constraint i : this.constraints )
                if( i == Constraint.costInsteadOfOrder )
                    return true;

        for(int i = 0; i < this.clientCount - 1; i++)
            if(this.clients[i].getVisitingTime() >= this.clients[i+1].getVisitingTime())
                return false;
        return true;
    }

    /**
     * Internal method used to add client to tour, once it has been checked not to be a duplicate
     * @param obj pointer to the Client object
     */
    private void _addClient(Client obj){
        if(this.clients == null){
            this.clients = new Client[1];
            this.clientCount = 1;
            this.clients[0] = obj;
            return;
        }
        else if(this.clientCount + 1 >= this.clients.length)
            this.allocClients(1);

        this.clients[this.clientCount++] = obj;
    }

    /**
     * Internal method used to add clients to the tour, once they have been checked not to be duplicates
     * @param objects pointer to the Client array
     */
    public void addClients(@NotNull Client ... objects){
        for( Client obj : objects )
            if(this.clientExists(obj))
                ErrorHandler.handleError(ErrorHandler.Error.tourDuplicateClient);
            else
                this._addClient(obj);

        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);
    }

    /**
     * Setter for vehicle
     * @param vehicle pointer to the vehicle to be assigned to the tour
     */
    public void assignVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    /**
     * Setter for clients (clears old clients)
     * @param clients pointer to the clients array to be assigned
     */
    public void assignClients(@NotNull Client ... clients){
        this.clients = Arrays.copyOf(clients, clients.length);
        this.clientCount = clients.length;

        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);
    }

    /**
     * Method used to enlarge clients array
     * @param reqSize required size
     */
    public void allocClients(int reqSize){
        this.clients = Arrays.copyOf(this.clients, Math.max(2*this.clients.length, clientCount + reqSize));
    }

    /**
     * Method used to shrink clients array, eliminating null objects
     */
    public void shrinkClientsToFit(){
        this.clients = Arrays.copyOf(this.clients, this.clientCount);
    }

    /**
     * Method checking whether Client exists in Tour
     * @param obj pointer to the Client object being checked
     * @return true if the Client exists in tour, false otherwise
     */
    public boolean clientExists(Client obj){
        for(int i = 0; i < this.clientCount; i++)
            if(this.clients[i].equals(obj))
                return true;
        return false;
    }
}