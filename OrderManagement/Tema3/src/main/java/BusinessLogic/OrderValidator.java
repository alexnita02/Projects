package BusinessLogic;

import Model.Order;
public class OrderValidator {

    public boolean validateOrder(Order order) {
        return validateQuantity(order.getQuantity());
    }

    public boolean validateQuantity(int quantity) {
        return quantity > 0;
    }
}
