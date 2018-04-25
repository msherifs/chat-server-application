package main;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private static int id = 0;
    private int userId;
    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.userId = Client.id;
            Client.id++;
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

    @Override
    public void run() {
        while(true) {
            try{
                System.out.println(in.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString() {
        return socket.getPort() + "," + socket.getInetAddress().getHostName();
    }
}
