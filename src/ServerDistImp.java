import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ServerDistImp extends UnicastRemoteObject implements ServerDist {
    private static final long serialVersionUID = 3969952077114579849L;

    // Nombre de clients maximum qui peuvent se connecter
    private static final int MAX_CLIENT = 2;
    // Ensemble des todo-lists associées à chaque client connecté
    private HashMap<Long, List<Task>> clientTaskList;

    protected ServerDistImp() throws RemoteException {
        super();
        clientTaskList = new HashMap<Long, List<Task>>();
    }

    /**
     * Effectue la connexion du client avec le serveur. Vérifie que le client peut se connecter en fonction du
     * nombre de clients déjà connectés. Attribue au client un identifiant unique.
     *
     * @return L'identifiant unique du client.
     */
    @Override
    public long connection() throws RemoteException{
        long id;
        synchronized (clientTaskList) {
            if (clientTaskList.size() < MAX_CLIENT) {
                Random rnd = new Random(System.currentTimeMillis());
                do {
                    id = rnd.nextLong();
                } while (clientTaskList.containsKey(id));
                List<Task> list = new ArrayList<Task>();
                clientTaskList.put(id, list);
            } else {
                id = -1;
            }
        }
        return id;
    }

    /**
     * EFfectue la déconnexion du client et supprime la todo-list associée.
     *
     * @param idClient
     * @throws NoClientException
     */
    @Override
    public void disconnection(final long idClient) throws NoClientException, RemoteException {
        if (clientTaskList.containsKey(idClient)) {
            synchronized (clientTaskList) {
                clientTaskList.remove(idClient);
            }
        } else {
            throw new NoClientException();
        }
    }


    @Override
    public int addTask(final long idClient, final Task task) throws NoClientException, RemoteException {
        List<Task> taskList;
        synchronized (clientTaskList) {
            taskList = clientTaskList.get(idClient);
        }
        if (taskList != null) {
            taskList.add(task);
            return taskList.size() - 1;
        } else {
            throw new NoClientException();
        }
    }

    @Override
    public Task getTask(final long idClient, final int idTask) throws NoClientException, NoTaskException,
            RemoteException {
        List<Task> taskList;
        synchronized (clientTaskList) {
            taskList = clientTaskList.get(idClient);
        }
        if (taskList != null) {
            if (idTask >= 0 && idTask < taskList.size()) {
                return taskList.get(idTask);
            } else {
                throw new NoTaskException();
            }

        } else {
            throw new NoClientException();
        }
    }

    @Override
    public int getTaskCount(final long idClient) throws NoClientException, RemoteException {
        List<Task> taskList;
        synchronized (clientTaskList) {
            taskList = clientTaskList.get(idClient);
        }
        if (taskList != null) {
            return taskList.size();
        } else {
            throw new NoClientException();
        }
    }

    @Override
    public void toHead(final long idClient, final int idTask) throws NoClientException, NoTaskException,
            RemoteException {
        List<Task> taskList;
        synchronized (clientTaskList) {
            taskList = clientTaskList.get(idClient);
        }
        if (taskList != null) {
            if (idTask >= 0 && idTask < taskList.size()) {
                taskList.add(0, taskList.remove(idTask));
            } else {
                throw new NoTaskException();
            }
        } else {
            throw new NoClientException();
        }
    }

    @Override
    public void deleteTask(final long idClient, final int idTask) throws NoClientException, NoTaskException
            , RemoteException {
        List<Task> taskList;
        synchronized (clientTaskList) {
            taskList = clientTaskList.get(idClient);
        }
        if (taskList != null) {
            if (idTask >= 0 && idTask < taskList.size()) {
                taskList.remove(idTask);
            } else {
                throw new NoTaskException();
            }
        } else {
            throw new NoClientException();
        }
    }
}
