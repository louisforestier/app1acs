import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerDist extends Remote {
    long connection() throws RemoteException;
    void disconnection(final long idClient) throws NoClientException, RemoteException;
    int addTask(final long idClient, final Task task) throws NoClientException, RemoteException;
    Task getTask(final long idClient, final int idTask) throws NoClientException, NoTaskException, RemoteException;
    int getTaskCount(final long idClient) throws NoClientException, RemoteException;
    void toHead(final long idClient, final int idTask) throws NoClientException, NoTaskException, RemoteException;
    void deleteTask(final long idClient, final int idTask) throws NoClientException, NoTaskException, RemoteException;
}
