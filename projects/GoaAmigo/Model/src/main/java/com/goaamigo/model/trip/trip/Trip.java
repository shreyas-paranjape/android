package com.goaamigo.model.trip.trip;

public class Trip {
    private int id;
    private String tripName;
    private String tripDescription;
    private int imageId;

    public Trip(int imageId, String tripDescription, String tripName) {
        this.imageId = imageId;
        this.tripDescription = tripDescription;
        this.tripName = tripName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
