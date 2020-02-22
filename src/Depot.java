import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Depot{
    private String name;
    //private static Depot[] depots;
    //private static int depotCount;
    private Vehicle[] vehicles;
    private int vehicleCount;

    public String toString(){
        return "Depot named : " + this.name + " containing : " + Arrays.toString(vehicles);
    }

    public int getVehicleCount(){
        return this.vehicleCount;
    }

    public void shrinkVehiclesToFit(){
        this.vehicles = Arrays.copyOf(this.vehicles, this.vehicleCount);
    }

    public Vehicle[] getVehicles(){
        this.shrinkVehiclesToFit();
        return this.vehicles;
    }

    public Depot(String name){
        this.name = name;

        this.vehicleCount = 0;
        this.vehicles = null;
    }

    public Depot(String name, @NotNull Vehicle ... objects){
        this.name = name;

        this.vehicleCount = 0;
        this.vehicles = new Vehicle[objects.length];

        this.addVehicles(objects);
    }

    private void _addVehicle(Vehicle obj){
        if(this.vehicles == null) {
            this.vehicles = new Vehicle[1];
            this.vehicleCount = 1;
            this.vehicles[0] = obj;
            return;
        }
        else if(this.vehicleCount + 1 > this.vehicles.length)
            this.allocVehicles(1);

        this.vehicles[this.vehicleCount++] = obj;
    }

    public void addVehicle(Vehicle obj){
        if(this.vehicleExists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateVehicle);

        this._addVehicle(obj);

        obj.setParentDepot(this);
    }

    public void addVehicles(@NotNull Vehicle ... objects){
        for( Vehicle obj : objects ){
            if(this.vehicleExists(obj)){
                ErrorHandler.handleError(ErrorHandler.Error.duplicateVehicleNoExit);
                continue;
            }

            this._addVehicle(obj);

            obj.setParentDepot(this);
        }
    }

    private void allocVehicles(int reqSize){
        this.vehicles = Arrays.copyOf(this.vehicles, Math.max(reqSize + this.vehicleCount, 2 * this.vehicles.length));
    }

    public boolean vehicleExists(Vehicle obj){
        for(int i = 0; i < this.vehicleCount; i++)
            if(this.vehicles[i].equals(obj))
                return true;
        return false;
    }

    public String getName(){
        return this.name;
    }
}