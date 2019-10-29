package order.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import order.models.ObjectId_Serializer;
import order.models.PagingResourceSupport;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


public class Product extends PagingResourceSupport {
    @JsonProperty("productId")
    private ObjectId productId;

    private String name;
    private String description;
    /*@DBRef
    private Category category;*/
    private double price;
    private String unit;

    public Product() {
    }


    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Id:- " + getProductId()
                + ", Name:- " + getName()
                + ", Unit:- " + getUnit()
                + ", Preis:- " + getPrice();
    }
}
