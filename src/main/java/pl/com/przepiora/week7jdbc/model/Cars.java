package pl.com.przepiora.week7jdbc.model;


public class Cars {

  private long carId;
  private String mark;
  private String model;
  private String colour;
  private long year;


  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }


  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }

}
