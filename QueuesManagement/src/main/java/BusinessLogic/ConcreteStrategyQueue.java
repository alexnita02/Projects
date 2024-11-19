package BusinessLogic;
import Model.*;
import java.util.*;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addTask(List<Server> servers, Client t){
        //caut serverul cu cea mai mica coada
        Server concreteQueueServer = null;

        int size=Integer.MAX_VALUE;

        for(Server s:servers){
            if(s.getClients().size()<size){
                size=s.getWaitingPeriod().intValue();
                concreteQueueServer=s;
            }
        }
        //adaug clientul in serverul cu coada cea mai mica
        concreteQueueServer.addClients(t);
    }
}
