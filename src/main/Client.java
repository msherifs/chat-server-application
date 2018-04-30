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
        out.write(s + "\n");
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
                if (s.equals("clients_online")) {
                    String av = Main.getAvailableClients();
                    System.out.println(av);
                    out.write(av + "\n");
                    out.flush();
                } else if (s.equals("group_join")){
                    int ss = Integer.parseInt(in.readLine());
                    Main.joinGroup(ss, this);
                } else if(s.equals("group_msg")){
                    String ss = in.readLine();
                    String[] grpMsg = ss.split(";");
                    Main.AVAILABLE_GROUPCHATS.get(Integer.parseInt(grpMsg[0])).broadcast(grpMsg[1]);
                } else if(s.equals("group_available")) {
                    out.write(Main.getAvailableGroups() + "\n");
                    out.flush();
                } else if(s.equals("group_create")){
                    String ss = in.readLine();
                    Main.createGroupChat(ss);
                }
            } catch (Exception e){
//                e.printStackTrace();
                System.out.println("User Disconnected! ID: " + this.getUserId());
                Thread.currentThread().interrupt();
                Main.CURRENT_USERS.remove(this);
            }

        }
    }

    @Override
    public String toString() {
        return "" + this.user;
    }
}
