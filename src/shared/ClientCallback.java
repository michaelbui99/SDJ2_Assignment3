package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void sendMessage(Message msg) throws RemoteException;
}
