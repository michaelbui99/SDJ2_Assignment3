package client.model;

import client.network.Client;
import client.network.ClientSocket;
import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

public class ChatModelManager implements ChatModel
{
  private Client client;
  private PropertyChangeSupport support;

  public ChatModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.startClient();
    client.addPropertyChangeListener("SendMessage", (evt)->support.firePropertyChange(evt));
  }

  @Override public void sendMessage(String msg)
  {
    client.sendMessage(msg);
  }

  @Override public List<String> getConnectedUsers()
  {
    return client.getConnectedUsers();
  }

  @Override public String getUserName()
  {
    return client.getUserName();
  }

  @Override public void setUserName(String name, String oldName)
      throws IOException, ClassNotFoundException
  {
    client.setUserName(name, oldName);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
