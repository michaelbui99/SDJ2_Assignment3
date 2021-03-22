package client.network;

import shared.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClientChat implements Client, ClientCallback
{
  private PropertyChangeSupport support;
  private String userName;
  private String oldName;
  private RMIServer server;

  public RMIClientChat() // we use try and catch so the exception don't bleed threw the layers
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    Registry registry = null;
    try
    {
      registry = LocateRegistry.getRegistry(1099);
      server = (RMIServer) registry.lookup(Util.SERVERNAME);
    }
    catch (RemoteException | NotBoundException e)
    {
      throw new RuntimeException("Server connection failed!!!");
    }
  }

  @Override public void sendMessage(String msg)
  {
    try
    {
      server.sendMessage(msg,userName);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<String> getConnectedUsers()
  {
    try
    {
     return server.getConnectedUsers();
    }
    catch (RemoteException e)
    {
     e.printStackTrace();
    }
    return null;
  }

  @Override public String getUserName()
  {
    return userName;
  }

  @Override public void setUserName(String name, String oldName)
  {
    userName = name;
    this.oldName = oldName;
    String s = oldName +","+name;
   server.adduser(s);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name,listener);

  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
   support.removePropertyChangeListener(name,listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
   support.removePropertyChangeListener(listener);
  }

  @Override public void sendMessage(Message msg)
  {
    support.firePropertyChange("SendMessage", null, msg);
  }
}
