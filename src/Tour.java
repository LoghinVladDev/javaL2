import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Tour{
    public enum Constraint{
        costInsteadOfOrder
    }

    private Vehicle vehicle;
    private Client[] clients;
    private int clientCount;

    private Constraint[] constraints;

    public void setConstraints(Constraint ... constraints) {
        this.constraints = Arrays.copyOf(constraints, constraints.length);
    }

    public Tour(){
        this.vehicle = null;
        this.clients = null;
        this.clientCount = 0;

        this.constraints = null;
    }

    public Tour(Vehicle vehicle){
        this.vehicle = vehicle;
        this.clients = null;
        this.clientCount = 0;

        this.constraints = null;
    }

    public Tour(Vehicle vehicle, @NotNull Client ... clients){
        this.vehicle = vehicle;
        this.clients = Arrays.copyOf(clients, clients.length);
        this.clientCount = clients.length;

        this.constraints = null;
    }

    public Client[] getClients(){
        this.shrinkClientsToFit();
        return this.clients;
    }

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

    public void addClients(@NotNull Client ... objects){
        for( Client obj : objects )
            if(this.clientExists(obj))
                ErrorHandler.handleError(ErrorHandler.Error.tourDuplicateClient);
            else
                this._addClient(obj);

        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);
    }

    public void addClientsArray(@NotNull Client[] objects){
        for( Client obj : objects )
            if(this.clientExists(obj))
                ErrorHandler.handleError(ErrorHandler.Error.tourDuplicateClient);
            else
                this._addClient(obj);

        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);
    }

    public void assignVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void assignClients(@NotNull Client ... clients){
        this.clients = Arrays.copyOf(clients, clients.length);
        this.clientCount = clients.length;

        if(!this.checkTour())
            ErrorHandler.handleError(ErrorHandler.Error.tourInvalidClientOrder);
    }

    public void allocClients(int reqSize){
        this.clients = Arrays.copyOf(this.clients, Math.max(2*this.clients.length, clientCount + reqSize));
    }

    public void shrinkClientsToFit(){
        this.clients = Arrays.copyOf(this.clients, this.clientCount);
    }

    public boolean clientExists(Client obj){
        for(int i = 0; i < this.clientCount; i++)
            if(this.clients[i].equals(obj))
                return true;
        return false;
    }
}