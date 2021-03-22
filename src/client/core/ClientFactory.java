package client.core;

import client.network.Client;
import client.network.RMIClientChat;

import java.rmi.RemoteException;

public class ClientFactory {

    private Client client;



    public Client getClient()
    {
        if (client == null)
        {
                client = new RMIClientChat();
        }
        return client;
    }
}
