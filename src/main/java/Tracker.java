import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Tracker extends Remote {
    public String sayHello() throws RemoteException;
    public List<PlayerRemote> getPlayers() throws RemoteException;
    public PlayerRemote getRandomPlayer() throws RemoteException;
    public PlayerRemote registerPlayer(PlayerRemote p) throws RemoteException;
}