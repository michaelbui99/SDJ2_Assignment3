package server.network;

import server.model.ChatModel;
import server.model.ChatModelManager;

import java.io.IOException;

public class RunServer
{
  public static void main(String[] args) throws IOException
  {
    ChatModel model = new ChatModelManager();
    ServerSocket serverSocket = new ServerSocket(model);
    serverSocket.start();
  }
}
