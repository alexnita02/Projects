package Model;



public class Order {
    private int id;
    private int clientID;
    private int productID;
    private int quantity;


    /**
     *
     * constructor default
     */

    public Order(){
    }

    /**
     * constructor cu parametrii specifici
     * @param orderID
     * @param clientID
     * @param productID
     * @param quantity
     */
    public Order(int orderID, int clientID, int productID, int quantity) {
        this.id = orderID;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     *  getter
     * @return id comanda
     */

    public int getId() {
        return id;
    }


    /**
     *  setter pt id comenzii
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return id ul clientului asociat comenzii
     */
    public int getClientID() {
        return clientID;
    }

    /**
     *
     * @param clientID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    /**
     *
     * @return id ul produsului asociat comenzii
     */
    public int getProductID() {
        return productID;
    }

    /**
     * setter pentru id ul produsului asociat comenzii
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     *
     * @return cantitatea comenzii
     */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return comanda reprezentata ca string
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + id +
                ", clientID=" + clientID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}