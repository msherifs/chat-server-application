package main;

public class OnlineStatusUpdater extends Thread{

    @Override
    public void run() {
        while (true){
            System.out.println("UPDATING !!!");
            for (Client c :
                    Main.CURRENT_USERS) {
                c.sendOnlineStatus();
            }
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
