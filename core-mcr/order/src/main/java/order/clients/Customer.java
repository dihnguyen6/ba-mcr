package order.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import order.models.ObjectId_Serializer;
import order.models.PagingResourceSupport;
import org.bson.types.ObjectId;

public class Customer extends PagingResourceSupport {
    @JsonProperty("customerId")
    private ObjectId customerId;

    private String name;
    private String email;
    private String address;
    private String city;

    public Customer() {
    }

    public ObjectId getCustomerId() {
        return customerId;
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public void setCustomerId(ObjectId customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer:- " + getCustomerId()
                + ", Name:- " + getName()
                + ", Email:- " + getEmail()
                + ", Address:- " + getAddress()
                + ", City:-  " + getCity();
    }
}
