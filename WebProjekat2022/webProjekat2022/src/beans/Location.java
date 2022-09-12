package beans;

import java.io.Serializable;

public class Location implements Serializable {
    private float latitude;
    private float longitude;
    private Address address;

    public Location(float latitude, float longitude, Address address){
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
    }

    public Address getAddress(){
        return address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
