package client.view.clinetswindow;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ClientsController
{
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;
  private ClientsVM clientsVM;
  @FXML private TextArea usersAria;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = vmf;
    clientsVM = vmf.getClientsVM();
  }


  @FXML public void onButtonUpdate(ActionEvent actionEvent)
  {
    usersAria.textProperty().setValue(clientsVM.showConnectedUsers());
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    viewHandler.openView("ChatRoom");
  }
}
