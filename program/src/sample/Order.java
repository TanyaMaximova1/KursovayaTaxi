package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс для работы с Заказами.
 */
public class Order {
    private IntegerProperty id;
    private StringProperty depAdres;
    private StringProperty arrAdres;
    private StringProperty dateTimeArrival;
    private StringProperty orderNumber;
    private StringProperty carName;

    /**
     * Конструктор класса
     * @param id идентификатор
     * @param depadress адрес отправления
     * @param arradres адрес прибытия
     * @param datetimearrival дата заказа
     * @param ordernumber номер заказа
     * @param carname название мащины
     */
    public Order(int id, String depadress, String arradres, String datetimearrival, String ordernumber, String carname) {
        this.id = new SimpleIntegerProperty(id);
        this.depAdres = new SimpleStringProperty(depadress);
        this.arrAdres = new SimpleStringProperty(arradres);
        this.dateTimeArrival = new SimpleStringProperty(datetimearrival);
        this.orderNumber = new SimpleStringProperty(ordernumber);
        this.carName = new SimpleStringProperty(carname);
    }

    /**
     * Конструктор класса. Устанавливает полям класса пустые значения. Id = 0.
     */
    public Order() {
        this(0,"", "", "", "", "");
    }

    /**
     * Получает значение идентификатора.
     * @return идентификатор.
     */
    public int getId() {
        return id.get();
    }

    /**
     * Реализует интерфейс Property для целочисленных значений.
     * @return идентификатор.
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param id идентификатор.
     */
    public void setId(int id) { this.id.set(id); }

    /**
     * Получает значение адреса отправления.
     * @return адрес отправления.
     */
    public String getDepAdres() { return depAdres.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return адрес отправления.
     */
    public StringProperty depadresProperty() {
        return depAdres;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param depadress адрес отправления.
     */
    public void setDepAdres(String depadress) { this.depAdres.set(depadress); }

    /**
     * Получает значение адреса прибытия.
     * @return адрес прибытия.
     */
    public String getArrAdres() { return arrAdres.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return адрес прибытия.
     */
    public StringProperty arrAdresProperty() {
        return arrAdres;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param arradres адрес прибытия.
     */
    public void setArrAdres(String arradres) { this.arrAdres.set(arradres); }

    /**
     * Получает значение даты заказа.
     * @return дата заказа.
     */
    public String getDateTimeArrival() { return dateTimeArrival.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return дата заказа.
     */
    public StringProperty DateTimeArrivalProperty() {
        return dateTimeArrival;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param datetimearrival дата заказа.
     */
    public void setDateTimeArrival(String datetimearrival) { this.dateTimeArrival.set(datetimearrival); }

    /**
     * Получает значение номера заказа.
     */
    public String getOrderNumber() { return orderNumber.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return номер заказа.
     */
    public StringProperty OrderNumberProperty() {
        return orderNumber;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param ordernumber номер заказа.
     */
    public void setOrderNumber(String ordernumber) { this.orderNumber.set(ordernumber); }

    /**
     * Получает значение названя машины.
     * @return название мащины.
     */
    public String getCarName() { return carName.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return название мащины.
     */
    public StringProperty CarNameProperty() {
        return carName;
    }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param carname название мащины.
     */
    public void setCarName(String carname) { this.carName.set(carname); }
}

