package client.view.chatroom;

import client.model.ChatModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.PropertyChangeSubject;

public class ChatRoomVM
{
  private ChatModel model;
  private StringProperty messages;
  private String allMessages = "";
  public ChatRoomVM(ChatModel model)
  {
    this.model = model;
    messages = new SimpleStringProperty("");
    ((PropertyChangeSubject) model).addPropertyChangeListener((evt)->{
      Platform.runLater(()->{
        allMessages += "\n" + evt.getNewValue().toString();
        messages.setValue(allMessages);
      });

    });
  }

  public void sendMessage(String msg)
  {
    model.sendMessage(msg);
  }

  public StringProperty messagesProperty()
  {
    return messages;
  }
}
