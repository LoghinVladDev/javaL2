/**
 * Truck Class
 * [OPTIONAL TASK]
 *
 * @author Loghin Vlad
 */
public class Truck extends Vehicle{

    /**
     * Constructor
     * @param name the name of the Truck
     */
    public Truck(String name){
        super(name);
    }

    /**
     * Overloaded toString Method
     * @return String interpretation of Truck object
     */
    public String toString(){
        return "Truck named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}