package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowDialog {
    private boolean DialogResultOK;
    private static String viewFileName;
    private static String windowTitle;
    private Customer customer;

    private Order order;

    /**
     *
     * @param order
     * @param viewFileName
     * @param windowTitle
     */
    public ShowDialog(Order order, String viewFileName, String windowTitle) {
        this.order = order;
        this.viewFileName = viewFileName;
        this.windowTitle = windowTitle;
    }

    /**
     *
     * @return
     */
    public boolean isDialogResultOK() {
        return DialogResultOK;
    }

    /**
     *
     * @param dialogResultOK
     */
    public void setDialogResultOK(boolean dialogResultOK) {
        DialogResultOK = dialogResultOK;
    }

    /**
     *
     * @return
     */
    public String getViewFileName() {
        return viewFileName;
    }

    /**
     *
     * @param viewFileName
     */
    public void setViewFileName(String viewFileName) {
        this.viewFileName = viewFileName;
    }

    /**
     *
     * @return
     */
    public String getWindowTitle() {
        return windowTitle;
    }

    /**
     *
     * @param windowTitle
     */
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    /**
     *
     * @param customer
     * @param viewFileName
     * @param windowTitle
     */
    public ShowDialog(Customer customer, String viewFileName, String windowTitle) {
        this.customer = customer;
        this.viewFileName = viewFileName;
        this.windowTitle = windowTitle;
    }

    /**
     *
     * @param viewFileName
     * @param windowTitle
     */
    public ShowDialog(String viewFileName, String windowTitle) {
        this.viewFileName = viewFileName;
        this.windowTitle = windowTitle;
    }

    /**
     *
     * @throws IOException при ошибке выполнения запроса.
     */
    public void defaultDialog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(viewFileName));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(windowTitle);
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        ControllerToAddAndEdit controller = loader.getController();
        controller.setDialogStage(addStage);
        addStage.showAndWait();
    }

    /**
     *
     * @throws IOException при ошибке выполнения запроса.
     */
    public void addOrEditCustomerDialog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(viewFileName));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(windowTitle);
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        ControllerToAddAndEdit controller = loader.getController();
        controller.setDialogStage(addStage);
        if (customer.getName().equals("") && customer.getSurname().equals(""))
        {
            controller.setCustomer(customer);
        }
        else
        {
            controller.setCustomerWithValues(customer);
        }
        addStage.showAndWait();
    }

    /**
     *
     * @throws IOException при ошибке выполнения запроса.
     */
    public void addOrEditOrderDialog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(viewFileName));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(windowTitle);
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        SampleToAddOrder controller = loader.getController();
        controller.setDialogStage(addStage);
        if (order.arrAdresProperty().equals("") && order.depadresProperty().equals(""))
           //if (order.CarNameProperty().equals("") && order.OrderNumberProperty().equals(""))
        {
            controller.setOrder(order);
        }
        else
        {
            controller.setOrderWithValues(order);
        }
        addStage.showAndWait();
    }
}
