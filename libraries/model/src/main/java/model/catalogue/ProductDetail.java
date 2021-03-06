package model.catalogue;

import android.os.Parcelable;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductDetail implements Serializable, Cloneable{

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

//    @Column(name = "rating")
//    private double rating;

    @Column(name = "image_url")
    private String imageUrl;

    @Override
    public String toString() {
        return "ProductDetail{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
//
//    public double getRating() {
//        return rating;
//    }
//    public void setRating(double rating) {
//        this.rating = price;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
