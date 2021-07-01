package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Контроллер формы работы с водителями.
 */
public class Controller {

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> surnameColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> patronymicColumn;
    @FXML
    private TableColumn<Customer, String> carColumn;
    @FXML
    private TableColumn<Customer, String> vacationColumn;

    public Label lblLog;

    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    public static ShowDialog sd;
    Connection conn;
    DBHandler dbHandler;

    @FXML
    void initialize(){
        surnameColumn.setCellValueFactory(studentStringCellDataFeatures -> studentStringCellDataFeatures.getValue().surnameProperty());
        nameColumn.setCellValueFactory(studentStringCellDataFeatures -> studentStringCellDataFeatures.getValue().nameProperty());
        patronymicColumn.setCellValueFactory(studentStringCellDataFeatures -> studentStringCellDataFeatures.getValue().patronymicProperty());
        carColumn.setCellValueFactory(studentStringCellDataFeatures -> studentStringCellDataFeatures.getValue().сarProperty());
        vacationColumn.setCellValueFactory(studentStringCellDataFeatures -> studentStringCellDataFeatures.getValue().vacationProperty());



        dbHandler = new DBHandler();
        conn = dbHandler.getConnection();
        try {
            fillTableView(SQL_requests.SelectAllFromCustomer(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        customerTable.setItems(customers);


        customerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer c1) {
                if(c1 != null){
                    setDataToLable(c1);
                }
            }
        });
    }

    /**
     * Загружает диалоговое окно для класса Customer.
     * @param customer объект
     * @throws IOException при ошибке выполнения запроса.
     */
    private void showDialog(Customer customer) throws IOException {
        sd = new ShowDialog(customer, "sampleToAdd.fxml", "Информация о водителе");
        sd.addOrEditCustomerDialog();
    }

    /**
     * Загружает диалоговое окно для класса Order.
     * @param order объект
     * @throws IOException при ошибке выполнения запроса.
     */
    private void showDialogAddOrder (Order order) throws IOException {
    sd = new ShowDialog(order, "sampleToAddOrder.fxml", "Добавление заказа");
    sd.addOrEditOrderDialog();
    }


    /**
     * Обработчик кнопки добавления. Добавляет данные в таблицу.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onAdd(ActionEvent actionEvent) throws IOException {
        Customer customer = new Customer();
        showDialog(customer);
        if(sd.isDialogResultOK())
        {
            try {
                SQL_requests.AddEntryInCustomer(conn, customer);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                fillTableView(SQL_requests.SelectAllFromCustomer(conn));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Обработчик кнопки ребактирования. Редактирует данные в таблице.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onEdit(ActionEvent actionEvent) throws IOException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null) {
            showDialog(selectedCustomer);
            if(sd.isDialogResultOK())
            {
                try {
                    SQL_requests.EditEntryInCustomerWithId(conn, selectedCustomer);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    fillTableView(SQL_requests.SelectAllFromCustomer(conn));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * Обработчик кнопки удаления. Удаляет текущего выбранного водителя в таблице.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onDelete(ActionEvent actionEvent) throws IOException  {
        int selectedId = customerTable.getSelectionModel().getSelectedItem().getId();
        try {
            SQL_requests.DeleteEntryInCustomerWithId(conn, selectedId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            fillTableView(SQL_requests.SelectAllFromCustomer(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        lblLog.setText("Строка удалена");
    }

    /**
     *
     * @param actionEvent собтие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onIndividualTask(ActionEvent actionEvent)  throws IOException  {
        try {
            fillTableView(SQL_requests.SelectMazdaFromCustomer(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Устанавливает значения для параметров класса Customer.
     * @param localResultSet
     * @throws SQLException при ошибке выполнения запроса.
     */
    private void fillTableView(ResultSet localResultSet) throws SQLException {
        customerTable.getItems().clear();
        while (localResultSet.next())
        {
            Customer customer = new Customer();
            customer.setId(localResultSet.getInt("id"));
            customer.setName(localResultSet.getString("Drivername"));
            customer.setSurname(localResultSet.getString("Driversurname"));
            customer.setPatronymic(localResultSet.getString("Driverpatronymic"));
            customer.setСar(localResultSet.getString("Carname"));
            customer.setVacation(localResultSet.getString("Drivervacation"));
            customers.add(customer);
        }
    }

    /**
     * Обработчик кнопки выхода. Закрывает программу.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onExit(ActionEvent actionEvent) throws IOException  {
        Platform.exit();
    }

    /**
     * Обработчик строки состояния. Показывает информацию текучего выбранного водителя.
     * @param customer
     */
    private void setDataToLable(Customer customer){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Фамилия: ");
        stringBuilder.append(customer.getSurname());
        stringBuilder.append(", Имя: ");
        stringBuilder.append(customer.getName());
        stringBuilder.append(", Отчество: ");
        stringBuilder.append(customer.getPatronymic());
        stringBuilder.append(", Машина: ");
        stringBuilder.append(customer.getСar());
        stringBuilder.append(", Отпуск: ");
        stringBuilder.append(customer.getVacation());
        lblLog.setText(stringBuilder.toString());
    }

    /**
     * Обработчик кнопки меню добавления. Открыает окно для добавления водителя.
     * @param actionEvent событие.
     */
    public void menuItemOnAdd(ActionEvent actionEvent) {
        try {
            onAdd(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки редактирования. Открыает окно для редактирования водителя.
     * @param actionEvent событие.
     */
    public void menuItemOnEdit(ActionEvent actionEvent) {
        try {
            onEdit(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки удаления. Удаляет данные.
     * @param actionEvent событие.
     */
    public void menuItemOnDelete(ActionEvent actionEvent) {
        try {
            onDelete(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки поиска. Показывает из базы данных запись с определенным параметром.
     * @param actionEvent  событие.
     */
    public void menuItemOnIndividualTask(ActionEvent actionEvent) {
        try {
            onIndividualTask(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки выхода. Закрывает программу.
     * @param actionEvent событие
     */
    public void menuItemOnExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * Обработчик кнопри открытия другого диалогового окна. Вызывает метод showDialogAddOrder.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onOrder(ActionEvent actionEvent) throws IOException { //показать заказ
        Order order = new Order();
        showDialogAddOrder(order);
    }

    /**
     * Обработчик кнопки, которая показывает в таблице только то, что указано в sql запросе.
     * @param actionEvent событие.
     */
    public void onVacation(ActionEvent actionEvent) {
        try {
            fillTableView(SQL_requests.SelectVacationFromCustomer(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки возвращения таблици со всеми данными. Отчищает таблицу и заполняет ее данными.
     * @param actionEvent событие.
     */
    public void onBack(ActionEvent actionEvent) {
        try {
            fillTableView(SQL_requests.SelectAllFromCustomer(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
