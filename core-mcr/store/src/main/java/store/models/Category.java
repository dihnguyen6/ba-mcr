/*
package store.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category extends PagingResourceSupport {

    @Id
    private ObjectId categoryId;

    private String name;

    public Category() {
    }

    public Category(ObjectId categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    @JsonSerialize(using = ObjectId_Serializer.class)
    public ObjectId getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id:- " + getCategoryId() + ", Name:- " + getName();
    }
}
*/
