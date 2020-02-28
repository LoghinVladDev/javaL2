/**
 * Drone Class
 * [OPTIONAL TASK]
 *
 * Extends Vehicle
 *
 * @author Loghin Vlad
 */
public class Drone extends Vehicle{

    /**
     * Constructor
     * @param name given drone name
     */
    public Drone(String name){
        super(name);
    }

    /**
     * Overloaded toString method
     * @return String containing interpretation of the Drone object
     */
    public String toString(){
        return "Drone named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}