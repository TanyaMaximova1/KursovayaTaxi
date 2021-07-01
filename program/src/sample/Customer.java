package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс для работы с Водителями.
 */
public class Customer {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty patronymic;
    private StringProperty car;
    private StringProperty vacation;

    /**
     *
     * @param id идентификатор
     * @param name имя
     * @param surname фамилия
     * @param patronymic отчество
     * @param car машина
     * @param vacation отпуск
     */
    public Customer(int id, String name, String surname, String patronymic, String car, String vacation) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.car = new SimpleStringProperty(car);
        this.vacation = new SimpleStringProperty(vacation);
    }

    /**
     * Конструктор класса. Устанавливает полям класса пустые значения. Id = 0.
     */
    public Customer() {
        this(0,"", "", "", "", "");
    }

    /**
     * Получает значение идентификатора.
     * @return идентификатор
     */
    public int getId() { return id.get(); }

    /**
     * Реализует интерфейс Property для целочисленных значений.
     * @return идентификатор
     */
    public IntegerProperty idProperty() { return id; }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param id идентификатор
     */
    public void setId(int id) { this.id.set(id); }

    /**
     * Получает значение имени.
     * @return имя.
     */
    public String getName() { return name.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return имя.
     */
    public StringProperty nameProperty() { return name; }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param name имя.
     */
    public void setName(String name) { this.name.set(name); }

    /**
     * Получает значение фамилия.
     * @return фамилия.
     */
    public String getSurname() { return surname.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return фамилия.
     */
    public StringProperty surnameProperty() { return surname; }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param surname фамилия.
     */
    public void setSurname(String surname) { this.surname.set(surname); }

    /**
     * Получает значение отчества.
     * @return отчество.
     */
    public String getPatronymic() { return patronymic.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return отчество.
     */
    public StringProperty patronymicProperty() { return patronymic; }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param patronymic отчество.
     */
    public void setPatronymic(String patronymic) { this.patronymic.set(patronymic); }

    /**
     * Получает значение машины.
     * @return машина.
     */
    public String getСar() { return car.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return машина.
     */
    public StringProperty сarProperty() { return car; }

    /**
     * Устанавливает идентификатор текущего экземпляра.
     * @param car машина.
     */
    public void setСar(String car) { this.car.set(car); }

    /**
     * Получает значение отпуска.
     * @return отпуск.
     */
    public String getVacation() { return vacation.get(); }

    /**
     * Реализует интерфейс Property для строковых значений.
     * @return отпуск.
     */
    public StringProperty vacationProperty() {
        return vacation;
    }

    /**
     *  Устанавливает идентификатор текущего экземпляра.
     * @param vacation отпуск.
     */
    public void setVacation(String vacation) { this.vacation.set(vacation); }

}