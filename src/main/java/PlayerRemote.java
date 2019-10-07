import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface PlayerRemote extends Remote {
    public String getId() throws RemoteException;
//    public PlayerRemote getPrimary();
//    public PlayerRemote getSecondary();
//    public GameState makeMove(String name, int move);
}
