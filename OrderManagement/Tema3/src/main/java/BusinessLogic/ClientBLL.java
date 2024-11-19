package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.*;


/**
 * Aceasta clasa ClientBLL ofera metode de inserare,updatare,stergere,cautare utilizate in lucrul cu gestionarea clientilor
 */
public class ClientBLL {

    ClientDAO clientDAO;

    ClientValidator clientValidator;



    public ClientBLL() {
        clientDAO = new ClientDAO();
        clientValidator = new ClientValidator();
    }

    /**
     * cautarea unui client dupa un anumit ID
     * @param ID ul clientului cautat
     * @return clientul cautat
     */
    public Client searchByID(int ID){

        return clientDAO.findById(ID);
    }

    public JTable createTable(){
        return clientDAO.createTable();
    }


    /**
     * metoda de inserare a unui client nou
     * @param ID             id client
     * @param firstName      prenume client
     * @param lastName       nume client
     * @param age            varsta
     */

    public void insertClient(String ID, String firstName,String lastName, int age){
        boolean flagID = true;
        int clientID = 0;
        try {
            clientID = Integer.parseInt(ID);
        } catch (Exception e) {
            flagID = false;
            JOptionPane.showMessageDialog(null, "Bad ID!");
        }

        if (flagID == true) {
            ClientValidator c = new ClientValidator();
            Client newClient = new Client(clientID, firstName, lastName, age);

            if (c.validateClient(newClient) == true) {
                clientDAO.insert(newClient);
            } else
                JOptionPane.showMessageDialog(null, "Bad input!");
        }

    }

    /**
     * stergere client in functie de id
     * @param id parametrul furnizat pentru identificarea clientului dorit a fi sters
     */


    public void deleteClient(String id) {
        int idDelete;
        try {
            idDelete = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        Client deletedClient = clientDAO.findById(idDelete);
        if (deletedClient!=null) {
            clientDAO.delete(deletedClient);
        } else
            JOptionPane.showMessageDialog(null, " Client inexistent! ");
    }


    /**
     * actualizarea detaliilor despre un client
     * @param ID
     * @param firstName
     * @param lastName
     * @param age
     */

    public void updateClient(String ID, String firstName,String lastName, int age)
    {
        int idUpdate;
        try {
            idUpdate=Integer.parseInt(ID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        Client client = new Client(idUpdate,firstName,lastName,age);
        if (clientValidator.validateClient(client) == true) {
            Client newClient = searchByID(idUpdate);
            newClient.setFirstName(firstName);
            newClient.setLastName(lastName);
            newClient.setAge(age);
            clientDAO.update(newClient);
        }

    }
}

