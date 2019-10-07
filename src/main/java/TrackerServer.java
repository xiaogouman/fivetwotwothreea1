import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackerServer implements Tracker {

    private List<PlayerRemote> players;
    private int N;
    private int K;

    public TrackerServer(int N, int K) throws RemoteException {
        this.N = N;
        this.K = K;
        players = new ArrayList<>();
    }

    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }

    public List<PlayerRemote> getPlayers() throws RemoteException {
        return players;
    }

    public PlayerRemote getRandomPlayer() throws RemoteException {
        Random rand = new Random();
        PlayerRemote randomPlayer = null;
        if (players.size() > 0) {
            randomPlayer = players.get(rand.nextInt(players.size()));
        }
        return randomPlayer;
    }

    public PlayerRemote registerPlayer(PlayerRemote p) throws RemoteException {
        PlayerRemote existingPlayer = getRandomPlayer();
        System.out.println(String.format("Register player %s", p.getId()));
        players.add(p);
        return existingPlayer;
    }

    public static void main(String args[]) {
        Tracker stub = null;
        Registry registry = null;

        int rmiPort = (args.length < 1) ? null : Integer.parseInt(args[0]);
        int N = (args.length < 2) ? null : Integer.parseInt(args[1]);
        int K = (args.length < 3) ? null : Integer.parseInt(args[2]);
        System.out.println(rmiPort);
        try {
            TrackerServer obj = new TrackerServer(N, K);
            stub = (Tracker) UnicastRemoteObject.exportObject(obj, 0);
            registry = LocateRegistry.getRegistry(rmiPort);
            registry.rebind("Tracker", stub);
            System.err.println("Tracker ready");
        } catch (Exception e) {
            try{
                registry.unbind("Tracker");
                registry.bind("Tracker", stub);
                System.err.println("Tracker ready");
            }catch(Exception ee){
                System.err.println("Tracker exception: " + ee.toString());
                ee.printStackTrace();
            }
        }
    }
}