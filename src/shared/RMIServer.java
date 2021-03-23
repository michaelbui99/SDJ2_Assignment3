package shared;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;
  void  sendMessage(String msg, String userName) throws RemoteException;
  List<String> getConnectedUsers()throws RemoteException;
  void registerCallback(ClientCallback ccb)throws RemoteException;
  void adduser(String user) throws RemoteException;
}
