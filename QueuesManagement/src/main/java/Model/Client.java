package Model;

public class Client implements Comparable{
    private int ID;
    private int arrivalTime;
    private int serviceTime;


    public Client(int ID,int arrivalTime,int serviceTime){
        super();
        this.ID=ID;
        this.arrivalTime=arrivalTime;
        this.serviceTime=serviceTime;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void run() throws InterruptedException {
        //process task
        Thread.sleep(serviceTime);
    }


    @Override
    public int compareTo(Object o) {
        Client c=(Client)o;
        if (this.getArrivalTime() < c.getArrivalTime()) {
            return -1;
        } else {

            if (this.getArrivalTime() > c.getArrivalTime()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    @Override
    public String toString() {
        return "(" + ID +
                "," + arrivalTime +
                "," + serviceTime + ")";
    }
}
