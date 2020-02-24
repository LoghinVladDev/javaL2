/**
 * Class Car
 * [OPTIONAL TASK]
 *
 * Extends Vehicle
 *
 * @author loghin Vlad
 */
public class Car extends Vehicle{

    /**
     * Constructor
     * @param name given Name for the car
     */
    public Car(String name){
        super(name);
    }

    /**
     * Overloaded toString method
     * @return String containing representation of car object
     */
    public String toString(){
        return "Car named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}