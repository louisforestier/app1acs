import java.rmi.*;
import java.util.Date;

public class Client {

    public static void Scenario1(ServerDist serverdist) throws RemoteException, NoClientException, NoTaskException {
        long id = serverdist.connection();
        if (id == -1) {
            System.out.println("Too many user using the serveur, please try later");
        } else {
            //Début de la session
            boolean isConnected = true;
            while (isConnected) {
                Task t1 = new Task("Tache 1", new Date());
                int n = serverdist.addTask(id, t1);
                System.out.println("Ajout d'une nouvelle task : " + n);
                Task t2 = new Task("Tache 2", new Date());
                n = serverdist.addTask(id, t2);
                Task ti = serverdist.getTask(id, 1);
                System.out.println(ti.getDescription());
                serverdist.disconnection(id);
                isConnected = false;
            }
        }
    }
    public static void Scenario2(ServerDist serverdist) throws RemoteException, NoClientException, NoTaskException {
        long id = serverdist.connection();
        if (id == -1) {
            System.out.println("Too many user using the serveur, please try later");
        } else {
            //Début de la session
            boolean isConnected = true;
            while (isConnected) {
                Task t1 = new Task("Tache 1", new Date());
                int n = serverdist.addTask(id, t1);
                System.out.println("Ajout d'une nouvelle task : " + n);
                Task t2 = new Task("Tache 2", new Date());
                n = serverdist.addTask(id, t2);
                Task ti = serverdist.getTask(id, 1);
                System.out.println(ti.getDescription());
                serverdist.toHead(id,1);
                Task t = serverdist.getTask(id, 0);
                System.out.println(t.getDescription());
                System.out.println("nb task " +serverdist.getTaskCount(id));
                serverdist.disconnection(id);
                isConnected = false;
            }
        }
    }

    public static void main(String[] args) {
        try {
            String url = "rmi://" + args[0] + "/todolist";
            ServerDist serverdist = (ServerDist) Naming.lookup(url);
            System.out.println("Envoie du message");
            Client.Scenario1(serverdist);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
