import java.util.Arrays;

public class StartPoint{
    public static void main(String[] args){
        Problem MDVSP = new Problem();

        MDVSP.addClients(
            new Client("Loghin", 1),
            new Client("Doni", 1),
            new Client("Moto", 2),
            new Client("Cheli", 2),
            new Client("Fotache", 3)
        );

        MDVSP.addDepots(
            new Depot("Copou",
                new Car("VW"),
                new Drone("Droni")),
            new Depot("Tudor",
                new Car("Rostogolea"),
                new Car("Rostogolea"))
        );

        System.out.println(MDVSP);

        System.out.println("Vehicles in all depots : \n\t" + Arrays.toString(MDVSP.getVehicles()));

        Solution solution = new Solution(MDVSP);

        System.out.println(solution);
    }
}