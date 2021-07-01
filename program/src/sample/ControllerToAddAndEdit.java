package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Контроллер формы работы с добавлением и редактированием водиелей.
 */
public class ControllerToAddAndEdit {

    @FXML
    private TextField tbFirstName;
    @FXML
    private TextField tbSecondName;
    @FXML
    private TextField tbPatronymic;
    @FXML
    private TextField tbCar;
    @FXML
    private TextField tbPhone;
    private Stage dialogStage;
    private Customer customer;

    /**
     *
     * @param dialogStage оъект для создания окна.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     *
     * @param customer объект класса Customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Получает данные из таблицы водителей.
     * @param customer
     */
    public void setCustomerWithValues(Customer customer) {
        this.customer = customer;
        tbFirstName.setText(customer.getName());
        tbSecondName.setText(customer.getSurname());
        tbPatronymic.setText(customer.getPatronymic());
        tbCar.setText(customer.getСar());
        tbPhone.setText(customer.getVacation());
    }

    /**
     *
     * @param customer
     */
    public void setStudent(Customer customer) {
        this.customer = customer;
        tbSecondName.setText(customer.getSurname());
        tbFirstName.setText(customer.getName());
        tbPatronymic.setText(customer.getPatronymic());
        tbCar.setText(customer.getСar());
        tbPhone.setText(customer.getVacation());
    }

    /**
     * Обработчик кнопки, которая принимает изменения произведенные в таблице.
     * @param actionEvent событие при нажатии на кнопку.
     */
    public void onEnter(ActionEvent actionEvent) {
        customer.setName(tbFirstName.getText());
        customer.setSurname(tbSecondName.getText());
        customer.setPatronymic(tbPatronymic.getText());
        customer.setСar(tbCar.getText());
        customer.setVacation(tbPhone.getText());
        Controller.sd.setDialogResultOK(true);
        dialogStage.close();
    }

    /**
     * Обработчик кнопки, которая не принимает изменения произведенные в таблице.
     * @param actionEvent событие при нажатии на кнопку.
     */
    public void onCancel(ActionEvent actionEvent) {
        Controller.sd.setDialogResultOK(false);
        dialogStage.close();
    }
}
