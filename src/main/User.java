package main;

public class User {
    private int idNumber;
    private String name;
    private String ip;
    private String rxPort;
    private String txPort;
    private static int id = 0;

    public User(String name, String ip, String rxPort, String txPort) {
        this.name = name;
        this.ip = ip;
        this.rxPort = rxPort;
        this.txPort = txPort;
        this.idNumber = User.id;
        User.id++;
    }

    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "" + idNumber + "," + name + "," + ip + "," + rxPort + "," + txPort;

    }
}
