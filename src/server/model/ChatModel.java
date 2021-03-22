package server.model;

import shared.Message;
import shared.PropertyChangeSubject;

import java.util.List;

public interface ChatModel extends PropertyChangeSubject
{
  void  sendMessage(Message msg);
  void addConnectedUser(String user);
  List<String> getConnectedUsers();
}
