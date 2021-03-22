package client.network;

import shared.PropertyChangeSubject;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface Client extends PropertyChangeSubject
{
   void startClient() throws RemoteException, NotBoundException;
   void  sendMessage(String msg) throws RemoteException;
   List <String> getConnectedUsers();
   String getUserName();
   void setUserName(String name, String oldName) throws IOException, ClassNotFoundException;
}
