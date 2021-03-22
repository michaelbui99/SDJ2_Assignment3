package server.network;

import server.model.ChatModel;
import server.model.ConnectionPool;

import java.io.IOException;
import java.net.Socket;

public class ServerSocket
{
  private ChatModel model;

  public ServerSocket(ChatModel model)
  {
    this.model = model;
  }

  public void start() throws IOException
  {
    try(java.net.ServerSocket serverSocket = new java.net.ServerSocket(1234))
    {
      System.out.println("Starting server...");
      while (true)
      {
        Socket socket = serverSocket.accept();
        ServerSocketHandler handler = new ServerSocketHandler(socket, model);
        model.addHandler(handler);
        Thread handlerThread = new Thread(handler);
        handlerThread.start();
      }
    }
  }


}
