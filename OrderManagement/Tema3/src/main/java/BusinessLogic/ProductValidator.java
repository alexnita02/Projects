package BusinessLogic;

import Model.Product;

public class ProductValidator {


    public boolean validateID(int ID){

        if(ID > 0)
            return true;

        else return false;
    }

    public boolean validateName(String name){

        if(name == "")
            return false;
        else if(name.matches("[ a-zA-Z]+") == false)
            return false;

        return true;
    }

    public boolean validatePrice( int price){

        if(price > 0)
            return true;

        else return false;
    }


    public boolean validateStock(int stock){

        if(stock > 0)
            return true;

        else return false;
    }

    public boolean validateProduct(Product product){

        if(validateID(product.getId()) == false)
            return false;
        else if(validateName(product.getName()) == false)
            return false;
        else if(validatePrice(product.getPrice()) == false)
            return false;
        else if(validateStock(product.getStock()) == false)
            return false;

        return true;
    }
}
