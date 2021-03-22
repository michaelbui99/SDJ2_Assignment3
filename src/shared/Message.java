package shared;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
  private String message;
  private String userName;
  private Date time;

  public Message(String message, String userName)
  {
    this.message = message;
    this.userName = userName;
    time =new Date();
  }

  @Override public String toString()
  {
    return time+" ["+userName+"]: "+ message + " ";
  }
}
