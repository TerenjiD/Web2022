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
}
