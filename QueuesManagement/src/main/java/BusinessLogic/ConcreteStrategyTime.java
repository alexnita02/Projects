package BusinessLogic;

import Model.*;

import java.util.*;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Client t){
        Server concreteTimeServer=null;
        int time=Integer.MAX_VALUE;
        //caut serverul cu timpul cel mai mic de asteptat
        for(Server s:servers) {
            if (s.getWaitingPeriod().intValue() < time) {
                time = s.getWaitingPeriod().intValue();
                concreteTimeServer = s;
            }
        }

        concreteTimeServer.addClients(t);
    }
}