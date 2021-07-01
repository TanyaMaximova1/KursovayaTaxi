package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс с sql запросами.
 */
public class SQL_requests {

    /**
     * Получает из таблицы Drivers все записи.
     * @param conn соединение к базе данных.
     * @return выбранные строки.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static ResultSet SelectAllFromCustomer(Connection conn) throws SQLException {
        String query = "SELECT * FROM Drivers";
        ResultSet set = conn.createStatement().executeQuery(query);
        return set;
    }

    /**
     * Добавляет в таблицу Drivers данные.
     * @param conn соединение к базе данных.
     * @param customer добавляемый объект класса Customer.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void AddEntryInCustomer(Connection conn, Customer customer) throws SQLException {
        String query = "INSERT INTO Drivers (Drivername, Driversurname, Driverpatronymic, Carname, Drivervacation) VALUES (\'" + customer.getName() + "\', \'" + customer.getSurname() + "\', \'" + customer.getPatronymic() + "\', \'" + customer.getСar() + "\', \'" + customer.getVacation()  + "\')";
        //String query = "INSERT INTO TaxiDrivers (Drivername, Driversurname, Driverpatronymic, Carname, Drivertelnumber) values ('Сидоров', 'Иван', 'Федорович', 'Mazda', '89209999999')";
        conn.createStatement().executeUpdate(query);
    }

    /**
     * Обновляет таблицу Drivers.
     * @param conn соединение к базе данных.
     * @param customer обновляемый объект класса Customer.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void EditEntryInCustomerWithId(Connection conn, Customer customer) throws SQLException {
        String query = "UPDATE Drivers SET Drivername = \'" + customer.getName() + "\', Driversurname = \'" + customer.getSurname() + "\', Driverpatronymic = \'" + customer.getPatronymic() + "\', Carname = \'" + customer.getСar() + "\', Drivervacation = \'" + customer.getVacation() + "\' WHERE ID = \'"+ customer.getId() + "\'";
        conn.createStatement().executeUpdate(query);
    }

    /**
     * Удаляет данные из таблицы Drivers.
     * @param conn соединение к базе данных.
     * @param id по этому параметру удаляется объект класса Customer.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void DeleteEntryInCustomerWithId(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM Drivers WHERE ID = " + id;
        conn.createStatement().executeUpdate(query);
    }

    /**
     * Получает из таблицы Drivers данные соответствующие sql запросу.
     * @param conn соединение к базе данных.
     * @return выбранные строки.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static ResultSet SelectMazdaFromCustomer(Connection conn) throws SQLException {
        String query = "SELECT * FROM Drivers WHERE Carname LIKE \'Mazda\'";
        ResultSet set = conn.createStatement().executeQuery(query);
        return set;
    }

    /**
     * Получает из таблицы Drivers данные соответствующие sql запросу.
     * @param conn соединение к базе данных.
     * @return выбранные строки.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static ResultSet SelectVacationFromCustomer(Connection conn) throws SQLException {
        String query = "SELECT * FROM Drivers WHERE Drivervacation LIKE \'Да\'";
        ResultSet set = conn.createStatement().executeQuery(query);
        return set;
    }

    /**
     * Получает из таблицы Orders все записи.
     * @param conn соединение к базе данных.
     * @return выбранные строки.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static ResultSet SelectAllFromOrder(Connection conn) throws SQLException { //вывести все из заказа
        String query = "SELECT * FROM Orders";
        ResultSet set = conn.createStatement().executeQuery(query);
        return set;
    }

    /**
     * Добавляет в таблицу Orders данные.
     * @param conn соединение к базе данных.
     * @param order добавляемый объект класса Order.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void AddEntryInOrder(Connection conn, Order order) throws SQLException { //добавить заказ
        String query = "INSERT INTO Orders (DepAdres, ArrAdres, DateTimeArrival, OrderNumber, Carname) VALUES (\'" + order.getArrAdres() + "\', \'" + order.getDepAdres() + "\', \'" + order.getDateTimeArrival() + "\', \'" + order.getOrderNumber() + "\', \'" + order.getCarName()  + "\')";
        //String query = "INSERT INTO TaxiDrivers (Drivername, Driversurname, Driverpatronymic, Carname, Drivertelnumber) values ('Сидоров', 'Иван', 'Федорович', 'Mazda', '89209999999')";
        conn.createStatement().executeUpdate(query);
    }

    /**
     * Получает из таблицы Drivers данные соответствующие sql запросу.
     * @param conn соединение к базе данных.
     * @return выбранные строки.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static ResultSet SelectMazdaFromOrder(Connection conn) throws SQLException { //вывести все заказы машины Мазда
        String query = "SELECT * FROM Orders WHERE Carname LIKE \'Mazda\'";
        ResultSet set = conn.createStatement().executeQuery(query);
        return set;
    }

    /**
     * Удаляет данные из таблицы Orders.
     * @param conn соединение к базе данных.
     * @param id по этому параметру удаляется объект класса Order.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void DeleteEntryInOrderWithId(Connection conn, int id) throws SQLException { //удалить заказ
        String query = "DELETE FROM Orders WHERE ID = " + id;
        conn.createStatement().executeUpdate(query);
    }

    /**
     * Обновляет таблицу Orders.
     * @param conn соединение к базе данных.
     * @param order обновляемый объект класса Order.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public static void EditEntryInOrderWithId(Connection conn, Order order) throws SQLException {
        String query = "UPDATE Orders SET DepAdres = \'" + order.getDepAdres() + "\', ArrAdres = \'" + order.getArrAdres() + "\', DateTimeArrival = \'" + order.getDateTimeArrival() + "\', OrderNumber = \'" + order.getOrderNumber() + "\', Carname = \'" + order.getCarName() + "\'";
        conn.createStatement().executeUpdate(query);
    }
}
