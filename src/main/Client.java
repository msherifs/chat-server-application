package main;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private User user;

    public Client(Socket socket, User user) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.user = user;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return this.user.getIdNumber();
    }

    public void sendMessage(String s) throws IOException {
        out.write(s);
        out.flush();
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try{
                String s = in.readLine();
                if (s.equals("info")) {
                    String av = Main.getAvailableClients();
                    System.out.println(av);
                    out.write(av + "\n");
                    out.flush();
                }
            } catch (Exception e){
//                e.printStackTrace();
                System.out.println("User Disconnected! ID: " + this.getUserId());
                Thread.currentThread().interrupt();
            }

        }
    }

    @Override
    public String toString() {
        return "" + this.user;
    }
}
