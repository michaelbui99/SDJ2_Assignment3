package server.network;

import server.model.ChatModel;
import shared.ClientCallback;
import shared.Message;
import shared.RMIServer;
import shared.Util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImplChat implements RMIServer
{
  private ChatModel model;

  public RMIServerImplChat(ChatModel model) throws RemoteException
  {
    this.model = model;
    UnicastRemoteObject.exportObject(this,0);
  }

  @Override public void startServer()
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(Util.SERVERNAME,this);
  }

  @Override public void sendMessage(String msg, String userName)
  {
    model.sendMessage(new Message(msg, userName));
  }

  @Override public List<String> getConnectedUsers()
  {
    return model.getConnectedUsers();
  }

  @Override public void registerCallback(ClientCallback ccb)
  {
    model.addPropertyChangeListener("SendMessage", new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          ccb.sendMessageCallback((Message) evt.getNewValue());
        }
        catch (RemoteException e)
        {
          model.removePropertyChangeListener(this);
        }
      }
    });
  }

  @Override public void adduser(String user)
  {
    model.addConnectedUser(user);
  }
}
