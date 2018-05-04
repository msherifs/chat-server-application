package main;

public class User {
    private int idNumber;
    private String name;
    private String ip;
    private String rxPort;
    private String txPort;
    private String status;
    private static int id = 0;

    public User(String name, String ip, String rxPort, String txPort, String status) {
        this.name = name;
        this.ip = ip;
        this.rxPort = rxPort;
        this.txPort = txPort;
        this.idNumber = User.id;
        this.status = status;
        User.id++;
    }

    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "" + idNumber + "," + name + "," + ip + "," + rxPort + "," + txPort + "," + status;

    }

    public void setStatus(String status) {
        this.status = status;
    }
}
