package main;

import java.util.Scanner;

public class KickClass extends Thread {
    Scanner sc = new Scanner(System.in);
    @Override
    public void run() {
        while (true){
            try{
                System.out.println("TRUSA");
                if(sc.hasNext()) {
                    System.out.println("DQI#RQ@###R@Q#RQRQ#R#EQ@#");
                    int id = sc.nextInt();
                    for (Client cl :
                            Main.CURRENT_USERS) {
                        if (cl.getUserId() == id) {
                            cl.sendMessage("kick");
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
