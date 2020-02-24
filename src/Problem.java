import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Problem Class
 * [COMPULSORY, OPTIONAL TASKS]
 *
 * @author Loghin Vlad
 */
public class Problem{
    private Depot[] depots;
    private Client[] clients;

    private int clientCount;
    private int depotCount;

    /**
     * Overloaded toString method
     * @return String interpretation of Problem object
     */
    public String toString(){
        this.shrinkAllArraysToFit();
        return "Problem contains sets : \n\tDepots : "
                + Arrays.toString(depots)
                + "\n\tClients : "
                + Arrays.toString(clients);
    }

    /**
     * Getter for Clients array
     * @return pointer to the clients array in problem
     */
    public Client[] getClients(){
        this.shrinkClientsToFit();
        return this.clients;
    }

    /**
     * Internal method used to enlarge clients array
     * @param reqSize required size
     */
    private void allocClients(int reqSize){
        this.clients = Arrays.copyOf(this.clients, Math.max(reqSize + this.clientCount, 2 * this.clients.length));
    }

    /**
     * Internal method used to enlarge depots array
     * @param reqSize required size
     */
    private void allocDepots(int reqSize){
        this.depots = Arrays.copyOf(this.depots, Math.max(reqSize + this.depotCount, 2 * this.depots.length));
    }

    /**
     * Method used to shrink clients array. Eliminates null values
     */
    public void shrinkClientsToFit(){
        this.clients = Arrays.copyOf(this.clients, this.clientCount);
    }

    /**
     * Method used to shrink depots array. Eliminates null values
     */
    public void shrinkDepotsToFit(){
        this.depots = Arrays.copyOf(this.depots, this.depotCount);
    }

    /**
     * Method used to shrink all arrays in problem
     */
    public void shrinkAllArraysToFit(){
        this.shrinkClientsToFit();
        this.shrinkDepotsToFit();
        for( Depot obj : this.depots )
            obj.shrinkVehiclesToFit();
    }

    /**
     * Getter for the vehicles array
     * @return pointer to the array containing Vehicle objects
     */
    public Vehicle[] getVehicles(){
        int returnedArraySize = 0;
        for(int i = 0; i < this.depotCount; i++)
            returnedArraySize += this.depots[i].getVehicleCount();

        Vehicle[] returnedArray = new Vehicle[returnedArraySize];

        for(int length = 0, i = 0; i < this.depotCount; i++)
            for(int j = 0; j < this.depots[i].getVehicleCount(); j++)
                returnedArray[length++] = this.depots[i].getVehicles()[j];

        return returnedArray;
    }

    /**
     * Constructor
     * Default
     */
    public Problem() {
        this.depots = null;
        this.clients = null;
        this.clientCount = this.depotCount = 0;
    }

    /**
     * Method is used to check whether depot exists or not
     * @param obj pointer to the depot being checked
     * @return true if depot exists, false otherwise
     */
    public boolean depotExists(Depot obj){
        for(int i = 0; i < this.depotCount; i++)
            if(this.depots[i].equals(obj))
                return true;
        return false;
    }

    /**
     * Method is used to check whether client exists or not
     * @param obj pointer to the client being checked
     * @return true if client exists, false otherwise
     */
    public boolean clientExists(Client obj){
        for(int i = 0; i < this.clientCount; i++)
            if(this.clients[i].equals(obj))
                return true;
        return false;
    }

    /**
     * Internal Method used to add a depot once it was found non existing
     * @param obj Pointer to the Depot object
     */
    private void _addDepot(Depot obj){
        if(this.depots == null) {
            this.depots = new Depot[1];
            this.depotCount = 1;
            this.depots[0] = obj;
            return;
        }
        else if(this.depotCount + 1 >= this.depots.length)
            this.allocDepots(1);

        this.depots[this.depotCount++] = obj;
    }

    /**
     * Method used to add a depot to the problem
     * @param obj Pointer to the Depot object
     */
    public void addDepot(Depot obj) {
        if(this.depotExists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateDepot);

        this._addDepot(obj);
    }

    /**
     * Method used to add depots to the problem. Varargs or array
     * @param objects pointer to array containing depot objects
     */
    public void addDepots(@NotNull Depot ... objects){
        for( Depot obj : objects ){
            if(this.depotExists(obj)){
                ErrorHandler.handleError(ErrorHandler.Error.duplicateDepotNoExit);
                continue;
            }

            this._addDepot(obj);
        }
    }

    /**
     * Internal method used to add Client once it has been proven to be non existing
     * @param obj pointer to the Client object
     */
    private void _addClient(Client obj){
        if(this.clients == null) {
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
     * Method used to add Client to problem
     * @param obj Pointer to the Client object
     */
    public void addClient(Client obj){
        if(this.clientExists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateClient);

        this._addClient(obj);
    }

    /**
     * Method used to add Clients to problem. Can be Varargs or array
     * @param objects Pointer to the array containing Client objects
     */
    public void addClients(@NotNull Client ... objects){
        for( Client obj : objects ){
            if(this.clientExists(obj)){
                ErrorHandler.handleError(ErrorHandler.Error.duplicateClientNoExit);
                continue;
            }

            this._addClient(obj);
        }
    }
}