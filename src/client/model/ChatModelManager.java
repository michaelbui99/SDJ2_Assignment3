package client.model;

import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ChatModelManager implements ChatModel
{
  private Client client;
  private PropertyChangeSupport support;

  public ChatModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    try
    {
      client.startClient();
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
    client.addPropertyChangeListener("SendMessage", (evt)->support.firePropertyChange(evt));
  }

  @Override public void sendMessage(String msg)
  {
    try
    {
      client.sendMessage(msg);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
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
