package BusinessLogic;

import DataAccess.ProductDAO;
import Model.*;

import javax.swing.*;

public class ProductBLL {

    ProductDAO productDAO;

    ProductValidator productValidator;

    public ProductBLL() {
        productDAO = new ProductDAO();
        productValidator = new ProductValidator();
    }

    public Product searchByID(int ID){

        return productDAO.findById(ID);
    }

    public void insertProduct(String ID, String name, int price, int stock)  {


        boolean flagID = true;
        int productID = 0;

        try {
            productID = Integer.parseInt(ID);
        } catch (Exception e) {
            flagID = false;
            JOptionPane.showMessageDialog(null, "Bad ID!");

        }
        if (flagID == true) {
            ProductValidator prodValidator = new ProductValidator();
            Product product = new Product(productID,name,price,stock);

            if (prodValidator.validateProduct(product) == true) {
                Product newProduct = new Product(productID, name,price,stock);
                productDAO.insert(newProduct);
            } else
                JOptionPane.showMessageDialog(null, "Bad input!");
        }
    }

    public JTable createTable(){
        return productDAO.createTable();
    }

    public void deleteProduct(String id) {
        int idDelete;
        try {
            idDelete = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        Product prodDelete = productDAO.findById(idDelete);
        if (prodDelete!=null) {
            productDAO.delete(prodDelete);
        } else
            JOptionPane.showMessageDialog(null, "Product inexistent! ");
    }


    public void updateProduct(String id, String name, String productPrice, String productStock) {
        int idUpdate , price, stock;
        try {
            idUpdate=Integer.parseInt(id);
            price=Integer.parseInt(productPrice);
            stock=Integer.parseInt(productStock);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        Product product = new Product(idUpdate,name,price,stock);
        if (productValidator.validateProduct(product)) {
            Product newProduct = productDAO.findById(idUpdate);
            newProduct.setName(name);
            newProduct.setStock(stock);
            newProduct.setPrice(price);
            productDAO.update(newProduct);
        }
    }
}
