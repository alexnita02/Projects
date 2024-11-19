package BusinessLogic;

import Model.Client;

public class ClientValidator {


    public boolean validateID(int id) {

        if (id > 0)
            return true;

        else return false;
    }


    public boolean validateAge(int age) {

        if (age < 10 || age > 110)
            return false;

        return true;
    }

    public boolean validateName(String name) {

        if (name == "" || name == "." || name == "\\")
            return false;
        else if (name.matches("[ a-zA-Z]+") == false)
            return false;

        return true;
    }

    public boolean validateClient(Client client) {

        if (validateID(client.getID()) == false)
            return false;
        else if (validateName(client.getFirstName()) == false)
            return false;
        else if (validateName(client.getLastName()) == false)
            return false;
        else if (validateAge(client.getAge()) == false)
            return false;
        return true;

    }


}
