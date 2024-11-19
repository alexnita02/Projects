package BusinessLogic;


import Model.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;


public class SimulationManager implements Runnable {
        private int timeLimit;
        private int minArrivalTime;
        private int maxArrivalTime;
        private int minProcessingTime;

        private int maxProcessingTime;

        private int numberClients;

        private int numberServers;

        private SelectionPolicy selectionPolicy;
        private Scheduler scheduler;
        private List<Client> generatedClients;
        private float averageWaitingTime;

        private float averageServiceTime;
        private int peekHour;


        public boolean flag = true;

        public SimulationManager(int timeLimit,int minArrivalTime,int maxArrivalTime, int minProcessingTime, int maxProcessingTime, int numberOfClients, int numberOfServers, String selectedStrategy) {
            super();
            this.timeLimit = timeLimit;
            this.minArrivalTime = minArrivalTime;
            this.maxArrivalTime = maxArrivalTime;
            this.minProcessingTime = minProcessingTime;
            this.maxProcessingTime = maxProcessingTime;
            this.numberClients = numberOfClients;
            this.numberServers = numberOfServers;


            this.averageWaitingTime = 0;
            this.peekHour = 0;
            this.averageServiceTime = 0;
            this.selectionPolicy = SelectionPolicy.valueOf(selectedStrategy);
            this.scheduler = new Scheduler(numberOfServers, numberOfClients);
            this.scheduler.changeStrategy(selectionPolicy);
            this.generateNRandomTasks();


        }
        public ArrayList<Client> getGeneratedClients() {
            return (ArrayList<Client>) generatedClients;
        }



        //        private void generateNRandomTasks() {             //NU MERGE IMI ARUNCA EXCEPTIE
//            Random r = new Random();
//            this.generatedClients = new ArrayList<Client>(numberClients);
//
//            for (int i = 0; i < numberClients; i++) {
//                int arrivalTime = r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
//                int processingTime = r.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
//                generatedClients.add(new Client(i, arrivalTime, processingTime));
//                averageWaitingTime += processingTime;
//            }
//
//            generatedClients.sort(Comparator.naturalOrder());
//            averageServiceTime = averageWaitingTime / numberClients;
//            averageWaitingTime = averageWaitingTime / (numberClients * numberServers);
//        }

        private void generateNRandomTasks() {
            Random r = new Random();
            this.generatedClients = new ArrayList<Client>(numberClients);

            for (int i = 0; i < numberClients; i++) {
                generatedClients.add(new Client(i, r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime, r.nextInt(abs(maxProcessingTime - minProcessingTime) + minProcessingTime)));
                averageWaitingTime += generatedClients.get(i).getServiceTime();
            }

            Collections.sort(generatedClients);
            averageServiceTime = averageWaitingTime / numberClients;
            averageWaitingTime = averageWaitingTime / (numberClients * numberServers);
        }

        public String clientsToString() {
            String result = new String();

            for (Client c: this.getGeneratedClients()) {
                if (c.getServiceTime()>0)
                    result = result + "(ID: " + c.getID() +", Arrival Time: " + c.getArrivalTime() +", Service time: " + c.getServiceTime()  +") ";
            }

            return result;
        }

        public String serversToString() {
            String result = new String();

            for (Server s: scheduler.getServers()) {
                result = result + "\nServer " + s.getIdServer() + ":";
                for (Client c: s.getClients())
                    if (c.getServiceTime() != 0)
                        result = result + "(ID: " + c.getID() +", Arrival Time: " + c.getArrivalTime() +", Service time: " + c.getServiceTime()  +") ";
            }
            result = result + "\n";

            return result;
        }
        @Override
        public void run() {
            int currentTime = 0;
            int maxClients = Integer.MIN_VALUE;
            try {
                FileWriter myWriter = new FileWriter("fisier.txt");
                PrintWriter printWriter = new PrintWriter(myWriter);
                printWriter.print(generatedClients.toString() + "\n"); //afisam pe prima linie waiting listul


                while (currentTime < timeLimit) {  //parcurgem lista de clienti

                    for (int i = 0; i < generatedClients.size(); i++) {
                        if (generatedClients.get(i).getArrivalTime() == currentTime) {
                            scheduler.dispachTask(generatedClients.get(i)); //atribuim clientul la un server
                            generatedClients.remove(i--); //scadem un index ca sa nu afectam loop-ul dupa ce am eliminat un client
                                                            //se poate intampla sa sarim peste un client
                        }
                    }


                    printWriter.print("Time : " + currentTime + "\n");

                    //afisam fiecare lista cu clientii ei
                    for (int i = 0; i < scheduler.getServers().size(); i++) {
                        String print = new String();
                        printWriter.print("Queue " + (i + 1) + ": ");
                        print = scheduler.getServers().get(i).getClients().toString();//ia queue de clienti si o apeleaza cu .toString ca sa putem afisa in fisier
                        printWriter.write(print + "\n");

                    }

                    //facem suma clientilor la fiecare queue si dupa comparam cu maxClients ca sa putem determina peekHour
                    //folosim k ca flag pt queueurile goale
                    int k = 0;
                    int sum = 0;

                    for(int i = 0; i < scheduler.getServers().size(); i ++){
                        sum += scheduler.getServers().get(i).getClients().size();
                    }

                    if(sum > maxClients){
                        maxClients = sum;
                        peekHour = currentTime;
                    }

                    //parcurgem toate serverele
                    for (int i = 0; i < scheduler.getServers().size(); i++) {
                        if (scheduler.getServers().get(i).getClients().size() != 0) {
                            //daca queue nu e goala verificam serviceTime clientului ca sa stim daca
                            //stergem sau nu din lista
                            int time = scheduler.getServers().get(i).getClients().peek().getServiceTime();
                            if (time != 0) {
                                scheduler.getServers().get(i).getClients().peek().setServiceTime(time - 1);
                            } else {
                                scheduler.getServers().get(i).deleteClients();
                            }
                        } else {
                            k++;
                        }
                    }
                    //daca toate queuerile is goale si nu mai sunt clienti trecem flag ul pe true ca sa oprim
                    if (k == numberServers && generatedClients.size() == 0){
                        flag = true;
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    currentTime++;
                }
                printWriter.write("Average waiting time " + averageWaitingTime + ".\n");
                printWriter.write("Average service time " + averageServiceTime + ".\n");
                printWriter.write("Peek hour  " + peekHour + ".\n");
                printWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }






