import javax.swing.text.Utilities;
import java.util.Arrays;

/**
 * Client Class
 * [COMPULSORY TASK]
 *
 * @author Loghin Vlad
 */
public class Client{
    private String name;
    private int visitingTime;
    //private static Client[] clients;
    //private static int clientCount;

    /**
     * Overloaded toString method
     * @return String containing representation of Client object
     */
    public String toString(){
        return "Client named : " + this.name + " with visiting order : " + visitingTime;
    }

    /**
     * Constructor
     * @param name given Client name
     * @param visitingTime given Client visiting time
     */
    public Client(String name, int visitingTime){
        this.name = name;
        this.visitingTime = visitingTime;

        //addClient(this);
    }

    /*private static void addClient(Client obj){
        if(exists(obj))
            ErrorHandler.handleError(ErrorHandler.Error.duplicateClient);

        if(clientCount + 1 >= clients.length)
            allocClients(1);

        clients[clientCount++] = obj;
    }

    private static void allocClients(int reqSize){
        clients = Arrays.copyOf(clients, Math.max(2 * clients.length, clientCount + reqSize));
    }

    public static boolean exists(Client obj){
        for(int i = 0; i < clientCount; i++)
            if(clients[i].equals(obj))
                return true;
        return false;
    }

    public static boolean clientExists(String name){
        for(int i = 0; i < clientCount; i++)
            if(clients[i].name.equals(name))
                return true;
        return false;
    }*/


    /**
     * Overloaded equals method
     * @param obj pointer to other object
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Client obj){
        return this.name.equals(obj.name);
    }

    /**
     * Getter for client name
     * @return String containing name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for client visiting name
     * @return value of visiting time
     */
    public int getVisitingTime(){
        return this.visitingTime;
    }
}