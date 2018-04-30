package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Client> CURRENT_USERS = new ArrayList<Client>();
    public static ArrayList<GroupChat> AVAILABLE_GROUPCHATS = new ArrayList<>();
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(50000);
            acceptClient(ss);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void acceptClient(ServerSocket s) throws IOException{
        while(true) {
            Socket client = s.accept();
            System.out.println(client.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String initConnection = in.readLine();
            System.out.println(initConnection);
            String[] parsedInitUser = initConnection.split(";");

            User connectedUser = new User(parsedInitUser[0], parsedInitUser[1], parsedInitUser[2], parsedInitUser[3]);

            Client cc = new Client(client, connectedUser);
            cc.start();
            Main.CURRENT_USERS.add(cc);
        }
    }

    public static String getAvailableClients() {
        String s = "";
        for (Client c :
                Main.CURRENT_USERS) {
            s += c.toString() + ";";
        }
        return s;
    }

    public static String getAvailableGroups(){
        String s = "";
        for (GroupChat gc: Main.AVAILABLE_GROUPCHATS){
            s += gc + ";";
        }
        return s;
    }

    public static void joinGroup(int id, Client c){
        try{
            Main.AVAILABLE_GROUPCHATS.get(id).addClient(c);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createGroupChat(String chatName){
        Main.AVAILABLE_GROUPCHATS.add(new GroupChat(chatName));
    }
}
