package model;



import java.io.DataOutputStream;
import java.util.Date;

public class SendingResultOfFire {

    private Date currentDate = new Date();
    private Engine engine;
    private ClientServerConnector connector;
    private int x;
    private int y;

    public SendingResultOfFire(Engine engine, ClientServerConnector connector) {
        this.engine = engine;
        this.connector = connector;
    }

    public void sendResult(int x, int y) {
        this.x = x;
        this.y = y;
        if (connector.getServer().isClosed()) {
            DataOutputStream out = new DataOutputStream(connector.getClient().getOutputClientStream());
            sendResultOne(out, "Client");

        } else {
            DataOutputStream out = new DataOutputStream(connector.getServer().getOutputServerStream());
            sendResultOne(out, "Server");
        }
    }

    private void sendResultOne(DataOutputStream out, String whoClientOrServer) {
        Cell cell = engine.getMap().getCellMY(x, y);
        if (cell.getFettle().equals("dam")) {
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: ($%d%%%d*DAM;",
                        whoClientOrServer, currentDate, x, y);
                engine.getStatus().setFollowStep(false);
                out.writeUTF(reportResultOfAttack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cell.getFettle().equals("kill")) {
            int index1 = cell.getShip().getX1() +
                    (10 * cell.getShip().getY1());
            int index2 = cell.getShip().getX2() +
                    (10 * cell.getShip().getY2());
            int index3 = cell.getShip().getX3() +
                    (10 * cell.getShip().getY3());
            int index4 = cell.getShip().getX4() +
                    (10 * cell.getShip().getY4());
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: "
                                + "($%d%%%d*DESTROY;%d&%d@%d#%d~",
                        whoClientOrServer, currentDate, x, y, index1, index2, index3, index4);
                engine.getStatus().setFollowStep(false);
                out.writeUTF(reportResultOfAttack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ((cell.getFettle().equals("nearship") || (cell.getFettle().equals("non")))) {
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: ($%d%%%d*MISS;",
                        whoClientOrServer, currentDate, x, y);
                out.writeUTF(reportResultOfAttack);
                engine.getStatus().setFollowStep(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
