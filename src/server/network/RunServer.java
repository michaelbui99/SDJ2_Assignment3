package server.network;

import server.model.ChatModel;
import server.model.ChatModelManager;
import shared.RMIServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;

public class RunServer
{
  public static void main(String[] args)
      throws IOException, AlreadyBoundException
  {
    ChatModel model = new ChatModelManager();
    RMIServer server = new RMIServerImplChat(model);
    server.startServer();
    System.out.println("Server starts...");
  }
}
