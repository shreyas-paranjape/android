package model.trip;

public class Deals {
    private int id;
    private String dealName;
    private String dealDescription;
    private int imageDeal;

    public Deals(String dealName, String dealDescription, int imageDeal) {
        this.dealName = dealName;
        this.dealDescription = dealDescription;
        this.imageDeal = imageDeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public int getImageDeal() {
        return imageDeal;
    }

    public void setImageDeal(int imageDeal) {
        this.imageDeal = imageDeal;
    }
}
