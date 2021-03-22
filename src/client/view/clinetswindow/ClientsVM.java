package client.view.clinetswindow;

import client.model.ChatModel;

public class ClientsVM
{
  private ChatModel model;

  public ClientsVM(ChatModel model)
  {
    this.model = model;
  }

  public String showConnectedUsers()
  {
    String st  = "";
    for (String string : model.getConnectedUsers())
    {
      st += "\n" + string;
    }
    return st;
  }
}
