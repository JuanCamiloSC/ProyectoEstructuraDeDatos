package Proceso.Model;

public class Notification {


    private MeansNotification meansNotication;

    public Notification(MeansNotification meansNotification){
        this.meansNotication = meansNotification;
    }

    public MeansNotification getMeansNotication() {
        return meansNotication;
    }

    public void setMeansNotication(MeansNotification meansNotification){
        this.meansNotication = meansNotication;
    }
    @Override
    public String toString(){
        return "Notification " + "meansNotification" + meansNotication;
    }


}
