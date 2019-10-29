package order.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_line")
public class OrderLine {
    @Id
    private ObjectId orderLineId;

    private ObjectId productId;
    private int amount;

    public OrderLine(int amount, ObjectId productId) {
        this.amount = amount;
        this.productId = productId;
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(ObjectId orderLineId) {
        this.orderLineId = orderLineId;
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
