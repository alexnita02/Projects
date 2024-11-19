package Model;

import BusinessLogic.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Server implements Runnable {

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private Scheduler scheduler;
    private int idServer;


    public Server(int idServer,int nrClients) {
        this.waitingPeriod = new AtomicInteger(); // imi arunca exceptie daca nu pun asta
        this.idServer = idServer;
        this.clients=new ArrayBlockingQueue<Client>(nrClients);
    }




    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public int getIdServer() {
        return idServer;
    }

    public void setIdServer(int idServer) {
        this.idServer = idServer;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod.set(waitingPeriod);
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public void addClients(Client newClient) {
        //add task to queue
        clients.add(newClient);
        //increment the waitingPeriod
        getWaitingPeriod().set(this.getWaitingPeriod().intValue() + newClient.getServiceTime());
    }



    public void deleteClients() {
        while (!clients.isEmpty() && clients.peek().getServiceTime() == 0) {
            clients.remove();
        }
    }



    //https://stackoverflow.com/questions/6546193/how-to-catch-an-exception-from-a-thread
    public void run() {
        Client client = new Client(0, 0, 0);

        //take next task from queue
        while (true) {
            if (!(getClients().isEmpty())) {
                client = clients.peek();
                if (client != null) {
                    try {
                        Thread.sleep(client.getServiceTime() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //decrement the waitingPeriod
                    getWaitingPeriod().set(getWaitingPeriod().get() - client.getServiceTime());

                }

                getClients().remove(client);


            }
        }
    }
}





