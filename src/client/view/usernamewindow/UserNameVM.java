package client.view.usernamewindow;

import client.model.ChatModel;

import java.io.IOException;

public class UserNameVM
{

  private ChatModel model;

  public UserNameVM(ChatModel model)
  {
    this.model = model;

  }

  public void addUser(String name, String oldName) throws IOException, ClassNotFoundException
  {
    model.setUserName(name, oldName);
  }

  public String getUsername()
  {
    return model.getUserName();
  }
}
