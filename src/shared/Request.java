package shared;

import java.io.Serializable;

public class Request implements Serializable
{
  private String type;
  private Object obj;

  public Request(String type, Object obj)
  {
    this.type = type;
    this.obj = obj;
  }

  public String getType()
  {
    return type;
  }

  public Object getObj()
  {
    return obj;
  }
}
