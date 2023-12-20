package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class Data implements Serializable {
    private long _id;
    private int image;
    private String name;
    private String location;
    private String tel;
    private String parking;
    private String dayOff;

    public Data(int image, String name, String location, String tel, String parking, String dayOff) {
        this.image = image;
        this.name = name;
        this.location = location;
        this.tel = tel;
        this.parking = parking;
        this.dayOff = dayOff;
    }

    public Data(long _id, int image, String name, String location, String tel, String parking, String dayOff) {
        this._id = _id;
        this.image = image;
        this.name = name;
        this.location = location;
        this.tel = tel;
        this.parking = parking;
        this.dayOff = dayOff;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getDayOff() {
        return dayOff;
    }

    public void setDayOff(String dayOff) {
        this.dayOff = dayOff;
    }
}
