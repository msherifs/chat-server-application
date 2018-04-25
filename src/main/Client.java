package main;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{

    private Socket socket;
    private String listenPort;
    private BufferedReader in;
    private BufferedWriter out;
    private static int id = 0;
    private int userId;
    public Client(Socket socket, int listenPort) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.userId = Client.id;
            Client.id++;
            this.listenPort = listenPort + "";
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String s) throws IOException {
        out.write(s);
        out.flush();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getListenPort() {
        return listenPort;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort;
    }

    @Override
    public void run() {
        while(true) {
            try{
                String s = in.readLine();
                if (s.equals("info")) {
                    out.write(Main.getAvailableClients() + "\n");
                    out.flush();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString() {
        return this.userId + "," + this.listenPort + "," + socket.getInetAddress().g;
    }
}
