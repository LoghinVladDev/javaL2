import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Problem{
    private Depot[] depots;
    private Client[] clients;

    private int clientCount;
    private int depotCount;

    public String toString(){
        this.shrinkAllArraysToFit();
        return "Problem contains sets : \n\tDepots : "
                + Arrays.toString(depots)
                + "\n\tClients : "
                + Arrays.toString(clients);
    }

    public Client[] getClients(){
        this.shrinkClientsToFit();
        return this.clients;
    }

    private void allocClients(int reqSize){
        this.clients = Arrays.copyOf(this.clients, Math.max(reqSize + this.clientCount, 2 * this.clients.length));
    }

    private void allocDepots(int reqSize){
        this.depots = Arrays.copyOf(this.depots, Math.max(reqSize + this.depotCount, 2 * this.depots.length));
    }

    public void shrinkClientsToFit(){
        this.clients = Arrays.copyOf(this.clients, this.clientCount);
    }

    public void shrinkDepotsToFit(){
        this.depots = Arrays.copyOf(this.depots, this.depotCount);
    }

    public void shrinkAllArraysToFit(){
        this.shrinkClientsToFit();
        this.shrinkDepotsToFit();
        for( Depot obj : this.depots )
            obj.shrinkVehiclesToFit();
    }

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

    public Problem() {
        this.depots = null;
        this.clients = null;
        this.clientCount = this.depotCount = 0;
    }

    public boolean depotExists(Depot obj){
        for(int i = 0; i < this.depotCount; i++)
            if(this.depots[i].equals(obj))
                return true;
        return false;
    }

    public boolean clientExists(Client obj){
        for(int i = 0; i < this.clientCount; i++)
            if(this.clients[i].equals(obj))
                return true;
        return false;
    }

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

    public void addDepot(Depot obj) {
        if(this.depotExists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateDepot);

        this._addDepot(obj);
    }

    public void addDepots(@NotNull Depot ... objects){
        for( Depot obj : objects ){
            if(this.depotExists(obj)){
                ErrorHandler.handleError(ErrorHandler.Error.duplicateDepotNoExit);
                continue;
            }

            this._addDepot(obj);
        }
    }

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

    public void addClient(Client obj){
        if(this.clientExists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateClient);

        this._addClient(obj);
    }

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