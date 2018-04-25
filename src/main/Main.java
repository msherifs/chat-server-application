package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Client> CURRENT_USERS = new ArrayList<Client>();
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
//            OutputStream outputStream = client.getOutputStream();
//            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            Main.CURRENT_USERS.add(new Client(client));
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            out.write("Welcome to HELL\n");
//            out.flush();
//            System.out.println("END");
//            for (Client c:
//                 Main.CURRENT_USERS) {
//                c.sendMessage("Hello\n");
//                c.sendMessage("EQEQ\n");
//            }
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
}
