import java.util.Arrays;

/**
 * StartPoint Class. Contains main
 *
 * @author Loghin Vlad
 */
public class StartPoint{

    /**
     * Program's main function
     * @param args arguments passed through command line / program arguments
     */
    public static void main(String[] args){

        // ! Compulsory
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

        // ! Optional ( Solution has to exist for compulsory, but optional requires printing ALL VEHICLES )

        System.out.println("Vehicles in all depots : \n\t" + Arrays.toString(MDVSP.getVehicles()));

        Solution solution = new Solution(MDVSP);

        System.out.println(solution);

        // ! Bonus ( experimental)

        Bonus bonus = new Bonus(MDVSP, 10);

        /*

        bonus.setOrderOfAssignment(Bonus.costOrder.longest);
        bonus.assignRandomCosts(10);
        */


        bonus.printCosts();

        //System.out.println(Arrays.toString(bonus.generateTourOrder()));

        int[] order = bonus.generateTourOrder();

        /*System.out.println(Arrays.toString(order)
                + "\nTotal cost : "
                + bonus.getTotalCost());*/

        Client[] orderedClients = new Client[MDVSP.getClients().length];
        for(int i = 0; i < MDVSP.getClients().length; i++){
            orderedClients[i] = MDVSP.getClients()[order[i]];
        }

        Tour optimisedTour = new Tour(MDVSP.getVehicles()[0], orderedClients);
        optimisedTour.setConstraints(Tour.Constraint.costInsteadOfOrder);

        System.out.println(optimisedTour);
    }
}