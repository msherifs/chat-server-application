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
                System.out.println(s);
                if (s.equals("clients_online")) {

                    String av = "";
                    for (Client c :
                            Main.CURRENT_USERS) {
                        System.out.println("DEEQQQQQQQQ" + c.user);
                        if (c.equals(this)) {
                            continue;
                        }
                        if (c.user.getName().equals("admoon")){
                            continue;
                        }
                        av += c.toString() + ";";
                    }

                    System.out.println("CCC" + av);
                    out.write("clients_online%" + av + "\n");
                    out.flush();
                } else if (s.equals("group_join")){
                    int ss = Integer.parseInt(in.readLine());
                    Main.joinGroup(ss, this);
                } else if(s.equals("group_msg")){
                    String ss = in.readLine();
                    String[] grpMsg = ss.split(";");
                    for (GroupChat gc :
                            Main.AVAILABLE_GROUPCHATS) {
                        System.out.println("SDAQQR!#R!#R@#R@!#R" + ss);
                        if (gc.getGroupId() == Integer.parseInt(grpMsg[0])) {
                            System.out.println("Broadcasting");
                            gc.broadcast("group_msg%" + gc.getGroupId() + "," + grpMsg[1] + "," + grpMsg[2]);
                        }
                    }
//                    Main.AVAILABLE_GROUPCHATS.get(Integer.parseInt(grpMsg[0])).broadcast(grpMsg[1]);
                } else if(s.equals("group_available")) {
                    out.write("group_available%" + Main.getAvailableGroups() + "\n");
                    out.flush();
                } else if(s.equals("group_create")){
                    String ss = in.readLine();
                    System.out.println(ss);
                    Main.createGroupChat(ss);
                } else if(s.equals("change_status")){
                    user.setStatus(in.readLine());
                } else if (s.equals("group_leave")){
                    int ss = Integer.parseInt(in.readLine());
                    Main.leaveGroup(ss, this);
                    out.write("group_leave%" + ss + "\n");
                    out.flush();
                } else if (s.equals("kick_client")){
                    int ss = Integer.parseInt(in.readLine());
                    for(Client c:Main.CURRENT_USERS){
                        if(c.getUserId() == ss){
                            c.sendMessage("kick%out");
                        }
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("User Disconnected! ID: " + this.getUserId());
                Thread.currentThread().interrupt();
                Main.CURRENT_USERS.remove(this);
            }

        }
    }

    public void sendOnlineStatus(){
        try{
            String av = "";
            for (Client c :
                    Main.CURRENT_USERS) {
                if (c.equals(this)) {
                    continue;
                }
                if (c.user.getName().equals("admoon")){
                    continue;
                }
                av += c.toString() + ";";
            }

            System.out.println(av);
            out.write("clients_online%" + av + "\n");
            out.flush();
            out.write("group_available%" + Main.getAvailableGroups() + "\n");
            out.flush();
            System.out.println("FLUSHED");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "" + this.user;
    }
}
