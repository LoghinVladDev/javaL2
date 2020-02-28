import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Depot Class
 * [COMPULSORY, OPTIONAL TASKS]
 *
 * @author Loghin Vlad
 */
public class Depot{
    private String name;
    //private static Depot[] depots;
    //private static int depotCount;
    private Vehicle[] vehicles;
    private int vehicleCount;

    /**
     * Overloaded toString method
     * @return String interpretation of Depot object
     */
    public String toString(){
        return "Depot named : " + this.name + " containing : " + Arrays.toString(vehicles);
    }

    /**
     * Getter for vehicle count in depot
     * @return value of vehicle count
     */
    public int getVehicleCount(){
        return this.vehicleCount;
    }

    /**
     * Method used to eliminate extra memory from vehicles array
     */
    public void shrinkVehiclesToFit(){
        this.vehicles = Arrays.copyOf(this.vehicles, this.vehicleCount);
    }

    /**
     * Getter for the vehicles array
     * @return pointer to the vehicles array
     */
    public Vehicle[] getVehicles(){
        this.shrinkVehiclesToFit();
        return this.vehicles;
    }

    /**
     * Constructor
     * Default
     * @param name given depot name
     */
    public Depot(String name){
        this.name = name;

        this.vehicleCount = 0;
        this.vehicles = null;
    }

    /**
     * Constructor
     * Overloaded
     * @param name name given to depot
     * @param objects objects to be put into depot. Cannot be null. Array or Varargs
     */
    public Depot(String name, @NotNull Vehicle ... objects){
        this.name = name;

        this.vehicleCount = 0;
        this.vehicles = new Vehicle[objects.length];

        this.addVehicles(objects);
    }

    /**
     * Internal Method to add vehicle once it's existence in the depot is checked
     * @param obj pointer to the vehicle
     */
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

    /**
     * Method used to add one vehicle to the depot
     * @param obj pointer to the Vehicle object
     */
    public void addVehicle(Vehicle obj){

        ///pizda ma-sii
    }

    /**
     * Method used to add more vehicles to the depot
     * @param objects pointer to some vehicles, either Varargs or an array containing vehicles
     */
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

    /**
     * Internal Method used to enlarge vehicles array
     * @param reqSize value of required size
     */
    private void allocVehicles(int reqSize){
        this.vehicles = Arrays.copyOf(this.vehicles, Math.max(reqSize + this.vehicleCount, 2 * this.vehicles.length));
    }

    /**
     * Method used to check whether Vehicle is or isn't in depot
     * @param obj pointer to the Vehicle to be checked
     * @return true if Vehicle exists in depot, false otherwise
     */
    public boolean vehicleExists(Vehicle obj){
        for(int i = 0; i < this.vehicleCount; i++)
            if(this.vehicles[i].equals(obj))
                return true;
        return false;
    }

    /**
     * Getter for depot name
     * @return String containing name
     */
    public String getName(){
        return this.name;
    }
}