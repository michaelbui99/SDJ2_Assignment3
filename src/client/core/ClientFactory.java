package client.core;

import client.network.Client;
import client.network.ClientSocket;

public class ClientFactory {

    private Client client;



    public Client getClient()
    {
        if (client == null)
        {
            client = new ClientSocket();
        }
        return client;
    }
}
