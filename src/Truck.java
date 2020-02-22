public class Truck extends Vehicle{

    public Truck(String name){
        super(name);
    }

    public String toString(){
        return "Truck named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}