
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Game {

    private Game() {
    }

    //    private String playerId;
//    private String trackerIp;
//    private int trackerPort;
    private String primaryPlayerId;
    private String secondaryPlayerId;

    public static void main(String[] args) {

        String rmiIp = (args.length < 1) ? null : args[0];
        int rmiPort = (args.length < 2) ? null : Integer.parseInt(args[1]);
        String playerId = (args.length < 3) ? null : args[2];

        try {
            // find tracker
            Registry registry = LocateRegistry.getRegistry(rmiIp, rmiPort);
            Tracker stub = (Tracker) registry.lookup("Tracker");
            // export player as remote object
            Player player = new Player(playerId);
            UnicastRemoteObject.exportObject(player, 0);
            // register player at tracker
            PlayerRemote randomPlayer = stub.registerPlayer(player);
            if (Objects.nonNull(randomPlayer)) {System.out.println(randomPlayer.getId());}
            // setup player

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
