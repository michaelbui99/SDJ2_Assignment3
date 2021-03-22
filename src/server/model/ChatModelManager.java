package server.model;

import server.MessageList;
import server.network.ServerSocketHandler;
import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatModelManager implements ChatModel
{
  private MessageList messageList;
  private PropertyChangeSupport support;
  private ConnectionPool pool;

  public ChatModelManager()
  {
    support = new PropertyChangeSupport(this);
    messageList = new MessageList();
    pool = new ConnectionPool();
  }

  @Override public void sendMessage(Message msg)
  {
    support.firePropertyChange("SendMessage" , null, msg);
    messageList.addMessage(msg);
  }

  @Override public void addConnectedUser(String user)
  {
    String[] names = user.split(",");
    pool.removeUser(names[0]);
    pool.addUser(names[1]);
  }

  @Override public List<String> getConnectedUsers()
  {
    return pool.getUsers();
  }


  @Override public void addHandler(ServerSocketHandler handler)
  {
    pool.addHandler(handler);
  }

  @Override public void removeHandler(ServerSocketHandler handler)
  {
    pool.removeHandler(handler);
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
