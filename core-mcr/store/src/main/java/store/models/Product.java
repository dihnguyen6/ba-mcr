package store.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product extends PagingResourceSupport {
    @Id
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

    /*public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }*/

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
                //+ ", Category:- " + getCategory()
                + ", Unit:- " + getUnit()
                + ", Preis:- " + getPrice();
    }
}
