package server.model;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private List<String> users;

  public ConnectionPool()
  {
    users = new ArrayList<>();
  }

  public List<String> getUsers()
  {
    return users;
  }

  public void addUser(String user)
  {
    users.add(user);
  }

  public void removeUser(String user)
  {
    users.remove(user);
  }

}
