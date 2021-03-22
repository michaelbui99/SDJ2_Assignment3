package client.core;

import client.network.Client;
import client.network.ClientSocket;

public class ClientFactory {

    private Client client;

//comment

    public Client getClient()
    {
        if (client == null)
        {
            client = new ClientSocket();
        }
        return client;
    }
}
