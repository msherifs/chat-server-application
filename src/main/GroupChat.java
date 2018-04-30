package main;

import java.util.ArrayList;

public class GroupChat {
    private String chatName = "";
    private ArrayList<Client> chatClients = new ArrayList<>();
    private static int Index = 0;
    private int groupId;

    public GroupChat (String chatName) {
        this.chatName = chatName;
        this.groupId = Index;
        Index++;
    }

    public void broadcast(String s) {
        for (Client cl:
             chatClients) {
            try {
                cl.sendMessage(s);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addClient(Client c) {
        chatClients.add(c);
    }

    @Override
    public String toString() {
        return groupId + "," + chatName;
    }
}
