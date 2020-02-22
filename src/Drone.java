public class Drone extends Vehicle{

    public Drone(String name){
        super(name);
    }

    public String toString(){
        return "Drone named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}