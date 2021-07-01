package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Контроллер формы работы с заказами.
 */
public class SampleToAddOrder {
    public TextField tbDepAddres;
    public TextField tbArrAdres;
    public TextField tbDateTimeArrival;
    public TextField tbOrderNumber;
    public TextField tbCarName;

    Connection conn;
    DBHandler dbHandler;

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> depadresColumn;
    @FXML
    private TableColumn<Order, String> aradresColumn;
    @FXML
    private TableColumn<Order, String> orderdateColumn;
    @FXML
    private TableColumn<Order, String> ordernumberColumn;
    @FXML
    private TableColumn<Order, String> ordercarColumn;
    private Stage dialogStage;
    private Order order;
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        depadresColumn.setCellValueFactory(orderStringCellDataFeatures -> orderStringCellDataFeatures.getValue().depadresProperty());
        aradresColumn.setCellValueFactory(orderStringCellDataFeatures -> orderStringCellDataFeatures.getValue().arrAdresProperty());
        orderdateColumn.setCellValueFactory(orderStringCellDataFeatures -> orderStringCellDataFeatures.getValue().DateTimeArrivalProperty());
        ordernumberColumn.setCellValueFactory(orderStringCellDataFeatures -> orderStringCellDataFeatures.getValue().OrderNumberProperty());
        ordercarColumn.setCellValueFactory(orderStringCellDataFeatures -> orderStringCellDataFeatures.getValue().CarNameProperty());

        dbHandler = new DBHandler();
        conn = dbHandler.getConnection();
        try {
            fillTableView(SQL_requests.SelectAllFromOrder(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        orderTable.setItems(orders);

        orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                if(t1 != null){
                    setDataToLableOrder(t1);
                }
            }
        });
    }

    /**
     * Получает данные из полей таблицы.
     * @param order
     */
    private void setDataToLableOrder(Order order){
        tbDepAddres.setText(order.getDepAdres());
        tbArrAdres.setText(order.getArrAdres());
        tbDateTimeArrival.setText(order.getDateTimeArrival());
        tbOrderNumber.setText(order.getOrderNumber());
        tbCarName.setText(order.getCarName());
        //lblLog.setText(stringBuilder.toString());
    }


    /**
     *
     * @param dialogStage оъект для создания окна.
     */
    public void setDialogStage(Stage dialogStage) { this.dialogStage = dialogStage; }

    /**
     *
     * @param order объект класса Order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Считывает введенные параметры объекта класса Order
     * @param order объект класса Order.
     */
    public void setOrderWithValues(Order order) {
        this.order = order;
        tbDepAddres.setText(order.getArrAdres());
        tbArrAdres.setText(order.getArrAdres());
        tbDateTimeArrival.setText(order.getDateTimeArrival());
        tbOrderNumber.setText(order.getOrderNumber());
        tbCarName.setText(order.getCarName());
    }

    /* public void setStudent(Customer customer) {
        this.customer = customer;
        tbSecondName.setText(customer.getSurname());
        tbFirstName.setText(customer.getName());
        tbPatronymic.setText(customer.getPatronymic());
        tbCar.setText(customer.getСar());
        tbPhone.setText(customer.getPhone());
    } */

    /**
     * Обработчик кнопки. Принимает изменения и закрывает окно.
     * @param actionEvent
     */
    public void onEnter(ActionEvent actionEvent) {
        Controller.sd.setDialogResultOK(true);
        dialogStage.close();
    }

    /**
     * Обработчик кнопки. Не принимает изменения и закрывает окно.
     * @param actionEvent событие.
     */
    public void onCancel(ActionEvent actionEvent) {
        Controller.sd.setDialogResultOK(false);
        dialogStage.close();
    }

    /**
     * Обработчик кнопки редактирования данных в таблице.
     * @param actionEvent событие.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onEditOrder(ActionEvent actionEvent) throws IOException { //редактировать
        Order selectedOrder = (Order) orderTable.getSelectionModel().getSelectedItem();
       selectedOrder.setCarName(tbCarName.getText());
       selectedOrder.setOrderNumber((tbOrderNumber.getText()));
       selectedOrder.setDepAdres(tbDepAddres.getText());
       selectedOrder.setArrAdres(tbArrAdres.getText());
       selectedOrder.setDateTimeArrival(tbDateTimeArrival.getText());
        try {
            SQL_requests.EditEntryInOrderWithId(conn, selectedOrder);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки удаления данных из таблицы.
     * @throws IOException при ошибке выполнения запроса.
     */
    public void onDeleteOrder()  { //удалить
        int selectedId = orderTable.getSelectionModel().getSelectedItem().getId();
        try {
            SQL_requests.DeleteEntryInOrderWithId(conn, selectedId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            fillTableView(SQL_requests.SelectAllFromOrder(conn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Обработчик кнопки добавления данных в таблицу.
     */
    public void onOrder() {
        Order selectedOrder = new Order();
        selectedOrder.setCarName(tbCarName.getText());
        selectedOrder.setOrderNumber((tbOrderNumber.getText()));
        selectedOrder.setDepAdres(tbDepAddres.getText());
        selectedOrder.setArrAdres(tbArrAdres.getText());
        selectedOrder.setDateTimeArrival(tbDateTimeArrival.getText());
        try {
            SQL_requests.AddEntryInOrder(conn, selectedOrder);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        orders.add(selectedOrder);
    }

    /**
     * Отчищает таблицу и заплняет ее данными.
     * @param localResultSet
     * @throws SQLException при ошибке выполнения запроса.
     */
    private void fillTableView(ResultSet localResultSet) throws SQLException {
        orderTable.getItems().clear();
        while (localResultSet.next())
        {
            Order order = new Order();
            order.setId(localResultSet.getInt("id"));
            order.setDepAdres(localResultSet.getString("DepAdres"));
            order.setArrAdres(localResultSet.getString("ArrAdres"));
            order.setOrderNumber(localResultSet.getString("OrderNumber"));
            order.setDateTimeArrival(localResultSet.getString("DateTimeArrival"));
            order.setCarName(localResultSet.getString("Carname"));
            orders.add(order);
        }
    }
}
