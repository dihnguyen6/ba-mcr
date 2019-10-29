package order.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import order.clients.StoreClient;
import org.aspectj.weaver.ast.Or;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "order")
public class Order  extends PagingResourceSupport {
    @Id
    private ObjectId orderId;

    private ObjectId customerId;

    @DBRef
    private List<OrderLine> orderLine;

    public Order() {
        orderLine = new ArrayList<OrderLine>();
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getOrderId() {
        return orderId;
    }

    public void setOrderId(ObjectId orderId) {
        this.orderId = orderId;
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(ObjectId customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    public void addLine(int amount, ObjectId productId) {
        this.orderLine.add(new OrderLine(amount, productId));
    }

    public int getNumberOfLines() {
        return orderLine.size();
    }
    public double totalPrice(StoreClient storeClient) {
        return orderLine.stream()
                .map((ol) -> ol.getAmount() * storeClient.getPrice(ol.getProductId()))
                .reduce(0.0, (d1, d2) -> d1 + d2);
    }
}
