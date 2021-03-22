package client.network;

import shared.Message;
import shared.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientSocket implements Client
{
  private PropertyChangeSupport support;
  private String userName;
  private String oldName;

  public ClientSocket()
  {

    support = new PropertyChangeSupport(this);

  }

  @Override public void startClient(){
    try
    {
      Socket socket = new Socket("localhost",1234);
      ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

      Thread thread = new Thread(() -> listenToServer(outToServer, inFromServer));
      thread.start();

    } catch (IOException e){
      e.printStackTrace();
    }
  }

  private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer){
    try
    {
      outToServer.writeObject(new Request("Listener", null));
      while (true){
        Request request = (Request) inFromServer.readObject();
        support.firePropertyChange(request.getType(), null, request.getObj());
      }
    }catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
    }
  }

  private Request request(String type, Object obj) throws IOException, ClassNotFoundException
  {
    Socket socket = new Socket("localhost", 1234);
    ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
    outToServer.writeObject(new Request(type,obj));
    return (Request) inFromServer.readObject();
  }

  @Override public void sendMessage(String msg)
  {
     try
     {
       Request response = request("SendMessage",new Message(msg, userName));
     }catch (IOException | ClassNotFoundException e){
       e.printStackTrace();
     }
  }

  @Override public List<String> getConnectedUsers()
  {
    try
    {
      Request response = request("getConnectedUsers", null);
      return (List<String>) response.getObj();
    } catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public String getUserName()
  {
    return userName;
  }

  @Override public void setUserName(String name, String oldName)
      throws IOException, ClassNotFoundException
  {
    userName = name;
    this.oldName = oldName;
    String s = oldName +","+name;
    Request response = request("addUser",s);
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
}
