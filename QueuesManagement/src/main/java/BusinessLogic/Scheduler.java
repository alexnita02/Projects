package BusinessLogic;

import Model.*;

import java.util.*;

public class Scheduler {

    private List<Server> servers ;
        private int maxNoServers;
        private int maxClientsPerServer;
        private Strategy strategy;
        private SimulationManager simulator;


    public Scheduler(int maxNoServers,int maxClientsPerServer){

        this.maxNoServers=maxNoServers;
        this.maxClientsPerServer=maxClientsPerServer;
        this.servers=new ArrayList<Server>();

        //for maxNoServers
        for(int i=0;i<maxNoServers;i++){
            // -create server object
            Server server = new Server(i,maxClientsPerServer);
            servers.add(server);
            // -create thread with the object
            Thread thread= new Thread(server);
            thread.start();
        }


    }

    public void changeStrategy(SelectionPolicy policy){
        //apply strategy patter to instantiate the strategy with the concrete
        //strategy corresponding to policy
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispachTask(Client t){
        //call the strategy addTask method
        strategy.addTask(servers,t);
    }

    public List<Server>getServers(){
        return (List<Server>) servers;
    }
}
