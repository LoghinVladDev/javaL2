import javax.swing.text.Utilities;
import java.util.Arrays;

public class Client{
    private String name;
    private int visitingTime;
    //private static Client[] clients;
    //private static int clientCount;

    public String toString(){
        return "Client named : " + this.name + " with visiting order : " + visitingTime;
    }

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



    public boolean equals(Client obj){
        return this.name.equals(obj.name);
    }

    public String getName(){
        return this.name;
    }

    public int getVisitingTime(){
        return this.visitingTime;
    }
}