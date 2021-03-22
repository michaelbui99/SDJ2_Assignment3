package client.network;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {
    ClientFactory cf = new ClientFactory();
    ModelFactory mf = new ModelFactory(cf.getClient());
    ViewModelFactory viewModelFactory = new ViewModelFactory(mf);
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    viewHandler.start(stage);
  }
}
