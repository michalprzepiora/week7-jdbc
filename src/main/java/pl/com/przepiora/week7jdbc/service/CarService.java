package pl.com.przepiora.week7jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.przepiora.week7jdbc.model.Car;
import pl.com.przepiora.week7jdbc.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

  private static CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    CarService.carRepository = carRepository;
  }

  public static List<Car> getAll() {
    return carRepository.getAll();
  }

  public static void add(Car car) {
    carRepository.add(car);
  }

  public static void delete(Long id) {
    carRepository.delete(id);
  }

  public static List<Car> getByDateRange(int at, int to) {
    return carRepository.getByDateRange(at, to);
  }
}
