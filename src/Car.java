public class Car extends Vehicle{

    public Car(String name){
        super(name);
    }

    public String toString(){
        return "Car named : " + super.name + " belonging to depot : " + super.parentDepot.getName();
    }
}