package pl.com.przepiora.week7jdbc.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.com.przepiora.week7jdbc.model.Car;
import pl.com.przepiora.week7jdbc.service.CarService;


@Route
@UIScope
public class MainView extends VerticalLayout {

  private Grid<Car> carGrid;
  private TextField markTextField;
  private TextField modelTextField;
  private TextField colourTextField;
  private TextField yearTextField;
  private Button addButton;
  private TextField fromTextField;
  private TextField toTextField;
  private Button applyFilterButton;
  private Checkbox activeFilterCheckbox;

  public MainView() {
    this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    Label label = new Label("JDBC");

    HorizontalLayout carGridLayout = new HorizontalLayout();
    carGridLayout.setWidth("950px");
    carGrid = new Grid<>();
    carGrid.setItems(CarService.getAll());
    carGrid.addColumn((ValueProvider<Car, String>) Car::getMark).setHeader("Mark")
        .setSortable(true);
    carGrid.addColumn((ValueProvider<Car, String>) Car::getModel).setHeader("Model")
        .setSortable(true);
    carGrid.addColumn((ValueProvider<Car, String>) Car::getColour).setHeader("Colour")
        .setSortable(true);
    carGrid.addColumn((ValueProvider<Car, Integer>) Car::getYear).setHeader("Year")
        .setSortable(true);
    carGrid.addComponentColumn((ValueProvider<Car, Component>) this::getDeleteButton);
    carGrid.setWidth("80%");
    carGridLayout.add(carGrid);

    HorizontalLayout headerButtons = new HorizontalLayout();
    headerButtons.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    markTextField = new TextField("Mark");
    markTextField.setRequired(true);
    modelTextField = new TextField("Model");
    modelTextField.setRequired(true);
    colourTextField = new TextField("Colour");
    colourTextField.setRequired(true);
    yearTextField = new TextField("Year");
    yearTextField.setRequired(true);
    addButton = new Button("Add new car");
    addButton.addClickListener(event -> addCarFromTextFields());
    headerButtons.add(markTextField, modelTextField, colourTextField, yearTextField, addButton);

    HorizontalLayout filterRow = new HorizontalLayout();
    filterRow.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    fromTextField = new TextField("From");
    toTextField = new TextField("To");
    applyFilterButton = new Button("Apply");
    applyFilterButton.addClickListener(event -> setFilteredItemsInCarGrid());
    activeFilterCheckbox = new Checkbox("Filter by year range.");
    setEnabledFilterForm(false);
    filterRow.add(fromTextField, toTextField, applyFilterButton, activeFilterCheckbox);
    activeFilterCheckbox
        .addClickListener(event -> setEnabledFilterForm(activeFilterCheckbox.getValue()));

    add(label, headerButtons, carGridLayout, filterRow);
  }

  private void setFilteredItemsInCarGrid() {
    try {
      int from, to;
      from = Integer.parseInt(fromTextField.getValue());
      to = Integer.parseInt(toTextField.getValue());
      carGrid.setItems(CarService.getByDateRange(from, to));
    } catch (NumberFormatException e) {
      Notification
          .show("Check your filter year and try again. Reason:" + e.getMessage(), 5000,
              Position.MIDDLE);
    }
  }

  private void setEnabledFilterForm(boolean isEnabled) {
    fromTextField.setEnabled(isEnabled);
    toTextField.setEnabled(isEnabled);
    applyFilterButton.setEnabled(isEnabled);
    setEnabledAddCarForm(!isEnabled);
    if (!isEnabled) {
      carGrid.setItems(CarService.getAll());
    }
  }

  private void setEnabledAddCarForm(boolean isEnabled) {
    markTextField.setEnabled(isEnabled);
    modelTextField.setEnabled(isEnabled);
    colourTextField.setEnabled(isEnabled);
    yearTextField.setEnabled(isEnabled);
    addButton.setEnabled(isEnabled);

  }

  private void addCarFromTextFields() {
    try {
      boolean isEmptySomeTextField =
          markTextField.isEmpty() & modelTextField.isEmpty() & colourTextField.isEmpty() &
              yearTextField.isEmpty();
      if (isEmptySomeTextField) {
        throw new IllegalArgumentException();
      }
      Car car = new Car(markTextField.getValue(), modelTextField.getValue(),
          colourTextField.getValue(),
          Integer.parseInt(yearTextField.getValue()));
      CarService.add(car);
      carGrid.setItems(CarService.getAll());
      eraseValuesFromTextFields();
    } catch (Exception e) {
      Notification
          .show("Check your data and try again. Reason:" + e.getMessage(), 5000, Position.MIDDLE);
    }
  }

  private void eraseValuesFromTextFields() {
    markTextField.clear();
    modelTextField.clear();
    colourTextField.clear();
    yearTextField.clear();
  }

  private Component getDeleteButton(Car car) {
    Button button = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));
    button.addClickListener(event -> deleteCarAndRefreshGrid(car));
    return button;
  }

  private void deleteCarAndRefreshGrid(Car car) {
    CarService.delete(car.getCarId());
    carGrid.setItems(CarService.getAll());
  }
}
