package pl.com.przepiora.week7jdbc.model;


public class Car {

  private long carId;
  private String mark;
  private String model;
  private String colour;
  private int year;

  public Car(long carId, String mark, String model, String colour, int year) {
    this.carId = carId;
    this.mark = mark;
    this.model = model;
    this.colour = colour;
    this.year = year;
  }

  public Car(String mark, String model, String colour, int year) {
    this.mark = mark;
    this.model = model;
    this.colour = colour;
    this.year = year;
  }

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


  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Car{" +
        "carId=" + carId +
        ", mark='" + mark + '\'' +
        ", model='" + model + '\'' +
        ", colour='" + colour + '\'' +
        ", year=" + year +
        '}';
  }
}
