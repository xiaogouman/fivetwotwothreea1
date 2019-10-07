import java.rmi.RemoteException;

public class Player implements PlayerRemote {

    private String id;

    private Player primaryServer;
    private Player backupServer;

    // local state
    protected GameState localState;

    // primary server or secondary server
    private boolean isPrimaryServer;
    private boolean isBackupServer;

    public Player() throws RemoteException {
        super();
    }
    public Player(String id) throws RemoteException {
        super();
        this.id = id;
    }

    public String getId() throws RemoteException{
        return id;
    }


    public GameState getLocalState() {
        return this.localState;
    }

    public void setLocalState(GameState state) {
        this.localState = state;
    }

    public void setPrimaryServer(Player primaryServer) {
        this.primaryServer = primaryServer;
    }

    public void setBackupServer(Player backupServer) {
        this.backupServer = backupServer;
    }

    /**
     * retrieve the latest game state from primary/backup server
     */
    private GameState retrieveGameState() {
        GameState state;
        try {
            state = this.primaryServer.getLocalState();
        } catch(Exception e1){
            try {
                System.out.println("Getting state from primary server failed: " + e1);
                state = this.backupServer.getLocalState();
            } catch(Exception e2){
                System.out.println("Getting state from backup server failed: " + e2);
                throw(e2);
            }
        }
        return state;
    }

    public void move(int direction) {
        GameState state;
        switch(direction) {
            case 0:
                state = retrieveGameState();
                this.setLocalState(state);
            case 1:
                // move west
                state = retrieveGameState();



            case 2:
            case 3:
            case 4:
            case 9:
            default:
                // do nothing

        }
    }


}
