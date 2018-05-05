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
                System.out.println("sending" + s + "to " + cl);
                cl.sendMessage(s);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addClient(Client c) {
        System.out.println("In ADD CLIENT !");
        chatClients.add(c);
    }

    public void removeClient(Client c){
        chatClients.remove(c);
    }
    @Override
    public String toString() {
        return groupId + "," + chatName;
    }

    public int getGroupId() {
        return groupId;
    }
}
