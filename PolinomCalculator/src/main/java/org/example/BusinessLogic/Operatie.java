package org.example.BusinessLogic;

import org.example.DataModels.*;

import java.util.*;

public class Operatie {

        public static Polinom adunare(Polinom p1, Polinom p2){
                Polinom rezultat=new Polinom();
                Set<Integer>exponent= new HashSet<>();  //pun puterile intr-un set pentru ca-s unice

                exponent.addAll(p1.getCoeficient().keySet());   //adaug in exponent puterile de la cele 2 polinoame p1,p2
                exponent.addAll(p2.getCoeficient().keySet());

                for(int i:exponent){                //parcurg puterile
                        float coeficient1=p1.getCoeficient().getOrDefault(i,0.0f);
                        float coeficient2=p2.getCoeficient().getOrDefault(i,0.0f);
                        float coeficient=coeficient1+coeficient2;    //determin coeficientul adunarii fiecarei key(putere) de la cele 2 polinoame
                                                                    // ca s-o pun in set-ul de puteri
                        rezultat.setCoeficient(i,coeficient);
                }
                return rezultat;
        }

        public Polinom scadere(Polinom p1,Polinom p2) {
               Polinom rezultat = new Polinom();
               Set<Integer> exponent = new HashSet<>();
                                                                    //asemenea ca la adunare
               exponent.addAll(p1.getCoeficient().keySet());
               exponent.addAll(p2.getCoeficient().keySet());

               for (int i : exponent) {
                       float coeficient = p1.getCoeficient().getOrDefault(i, 0.0f) - p2.getCoeficient().getOrDefault(i, 0.0f);
                       rezultat.setCoeficient(i,coeficient);
               }
               return rezultat;

       }




        public Polinom inmultire(Polinom p1,Polinom p2){
                Polinom rezultat=new Polinom();
                Map<Integer,Float>coeficient1=p1.getCoeficient();
                Map<Integer,Float>coeficient2=p2.getCoeficient();
                Map<Integer,Float>coeficient=new TreeMap<Integer,Float>();

                for(Map.Entry<Integer,Float> i :coeficient1.entrySet()){
                        for(Map.Entry<Integer,Float> j :coeficient2.entrySet()){

                                float coef=i.getValue()*j.getValue();            //se parcurge fiecare pereche de coeficienti si se adauga in map dupa cheie
                                int exponent=i.getKey() + j.getKey();

                             if(coeficient.containsKey(exponent)){          //daca exista deja in Map actualizam coeficientul
                                coef=coef+coeficient.get(exponent);
                             }
                             coeficient.put(exponent,coef);
                        }
                }

                for(Map.Entry<Integer,Float> i : coeficient.entrySet()){
                        rezultat.setCoeficient(i.getKey(),i.getValue());
                }


                return rezultat;

}

        public Polinom derivare(Polinom p){
                TreeMap<Integer,Float> rezultat= new TreeMap<Integer,Float>();         //se parcurge dupa puterile polinomului dat ca parametru si daca puterea e nenula se calculeaza
                                                                                        //rezultatul dupa formula (n)x^n-1
                for(int exponent:p.getCoeficient().keySet()){
                        if(exponent>0){
                                float coeficient=exponent * p.getCoeficient().get(exponent);
                                rezultat.put(exponent-1,coeficient);
                        }
                }
                return new Polinom(rezultat);
        }

}
