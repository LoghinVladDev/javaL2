import java.util.Arrays;

/**
 * Abstract Class Vehicle
 * [COMPULSORY = NON-ABSTRACT, OPTIONAL = ABSTRACT]
 *
 * @author Loghin Vlad
 */
public abstract class Vehicle{
    protected String name;
    protected Depot parentDepot;
    //private static Vehicle[] vehicles;
    //private static int vehicleCount;

    /**
     * Constructor
     * @param name String containing given Vehicle name.
     */
    public Vehicle(String name){
        this.name = name;
        this.parentDepot = null;

        //addVehicle(this);
    }

    /**
     * Setter for parent depot
     * @param obj pointer to the parent Depot
     */
    public void setParentDepot(Depot obj){
        if(this.parentDepot != null)
            ErrorHandler.handleError(ErrorHandler.Error.vehicleDepotAssignDuplicate);

        this.parentDepot = obj;
    }

    /**
     * Getter for parent depot
     * @return pointer to the parent depot
     */
    public Depot getParentDepot(){
        return this.parentDepot;
    }

    /**
     * Abstract Overloaded toString method
     * @return String containing interpretation of Vehicle object, based on extension
     */
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

    /**
     * Overloaded equals method
     *
     * @param obj pointer to the object to which this is compared
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Vehicle obj) { return this.name.equals(obj.name); }
}