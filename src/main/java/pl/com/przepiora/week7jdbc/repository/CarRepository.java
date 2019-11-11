package pl.com.przepiora.week7jdbc.repository;

import pl.com.przepiora.week7jdbc.model.Car;

import java.util.List;

public interface CarRepository {

  List<Car> getAll();

  List<Car> getByDateRange(int at, int to);

  void add(Car car);

  void delete(Long id);

}
