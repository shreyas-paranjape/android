package delivery.model.catalogue;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import delivery.model.profile.Party;

@Table
public class ProductParty implements Serializable {

    private long id;
    @Column(name = "product_id")
    private Product product;
    @Column(name = "party_id")
    private Party party;
    @Column(name = "description")
    private String description;
    @Column(name = "rate")
    private double rate;
    @Column(name = "image_url")
    private String imageUrl;

    public ProductParty() {
    }

    public ProductParty(long id, Product product, Party party,
                        String description, double rate, String imageUrl) {
        this.id = id;
        this.product = product;
        this.party = party;
        this.description = description;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductParty that = (ProductParty) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return !(party != null ? !party.equals(that.party) : that.party != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (party != null ? party.hashCode() : 0);
        return result;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
