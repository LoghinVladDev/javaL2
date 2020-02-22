import java.util.Arrays;

public abstract class Vehicle{
    protected String name;
    protected Depot parentDepot;
    //private static Vehicle[] vehicles;
    //private static int vehicleCount;

    public Vehicle(String name){
        this.name = name;
        this.parentDepot = null;

        //addVehicle(this);
    }

    public void setParentDepot(Depot obj){
        if(this.parentDepot != null)
            ErrorHandler.handleError(ErrorHandler.Error.vehicleDepotAssignDuplicate);

        this.parentDepot = obj;
    }

    public Depot getParentDepot(){
        return this.parentDepot;
    }

    public abstract String toString();

    /*
    private static void allocVehicles(int reqSize){
        vehicles = Arrays.copyOf(vehicles, Math.max(2 * vehicles.length, reqSize + vehicleCount));
    }

    protected static void addVehicle(Vehicle obj){
        if(exists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateVehicle);

        if(vehicleCount + 1 >= vehicles.length)
            allocVehicles(1);

        vehicles[vehicleCount++] = obj;
    }

    public static boolean exists(Vehicle obj){
        for(int i = 0; i < vehicleCount; i++)
            if(vehicles[i].equals(obj))
                return true;
        return false;
    }*/

    public boolean equals(Vehicle obj) { return this.name.equals(obj.name); }
}