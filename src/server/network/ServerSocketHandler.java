package server.network;

import server.model.ChatModel;
import shared.Message;
import shared.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  private ChatModel model;



  public ServerSocketHandler(Socket socket, ChatModel model) throws IOException
  {
    this.model = model;
    this.socket = socket;
    in = new ObjectInputStream(socket.getInputStream());
    out = new ObjectOutputStream(socket.getOutputStream());
  }

  @Override public void run()
  {
    System.out.println("Client connected from: " + socket.getInetAddress().getHostAddress() + " " +socket.getPort());
    while (true)
    {
      try
      {
        Request requestFromClient = (Request) in.readObject();
        System.out.println("Request from client received: " + requestFromClient.getType());

        if (requestFromClient.getType().equals("Listener"))
        {
          model.addPropertyChangeListener("SendMessage", this::onSendMessage);
        }
        else if (requestFromClient.getType().equals("SendMessage"))
        {
          model.sendMessage((Message) requestFromClient.getObj());
          out.writeObject(new Request("response", "Message has been sent"));
        }
        else if (requestFromClient.getType().equals("addUser"))
        {
          model.addConnectedUser((String) requestFromClient.getObj());
          out.writeObject(new Request("response", "I got it"));
        }
        else if (requestFromClient.getType().equals("getConnectedUsers"))
        {
          out.writeObject(new Request("getConnectedUsers", model.getConnectedUsers()));
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }

  }

  public void onSendMessage(PropertyChangeEvent evt)
  {
    try
    {
      out.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
