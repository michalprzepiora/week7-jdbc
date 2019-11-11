package pl.com.przepiora.week7jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.przepiora.week7jdbc.model.Car;

import java.util.List;
import javax.sql.DataSource;

@Repository
public class CarRepoInDatabase implements CarRepository {

  private JdbcTemplate jdbcTemplate;

  public CarRepoInDatabase(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(
        "CREATE TABLE IF NOT EXISTS cars(car_id int, mark varchar(255), model varchar(255),colour varchar(255), year int, PRIMARY KEY (car_id))");
  }

  @Override
  public List<Car> getAll() {
    String sql = "SELECT * FROM cars";

    return jdbcTemplate.query(sql, (resultSet, i) -> new Car(resultSet.getLong(1),
        resultSet.getString(2),
        resultSet.getString(3),
        resultSet.getString(4),
        resultSet.getInt(5)));
  }

  @Override
  public List<Car> getByDateRange(int at, int to) {
    String sql = "SELECT * FROM cars WHERE year BETWEEN ? AND ?";
    return jdbcTemplate.query(sql, (resultSet, i) -> new Car(resultSet.getLong(1),
        resultSet.getString(2),
        resultSet.getString(3),
        resultSet.getString(4),
        resultSet.getInt(5)), at, to);
  }

  @Override
  public void add(Car car) {
    String sql = "INSERT INTO cars (mark,model,colour,year) VALUES (?,?,?,?)";
    jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColour(), car.getYear());
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM cars WHERE car_id=?", id);
  }
}
