package delivery.repository;

import com.orm.SugarRecord;

import java.util.List;

import delivery.model.order.Order;
import delivery.model.order.OrderItem;

public class OrderRepo {

    public List<Order> getAll() {
        return SugarRecord.listAll(Order.class);
    }

    public List<OrderItem> getItems(Order order) {
        return SugarRecord.find(OrderItem.class, "order_id = ?", String.valueOf(order.getId()));
    }

}
