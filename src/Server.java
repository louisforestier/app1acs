import java.rmi.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerDistImp serverDistImp = new ServerDistImp();
            System.out.println("Enregistrement de l'objet avec l'url");
            Naming.rebind("todolist", serverDistImp);
            System.out.println("Serveur lancé");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
