package client.core;

import client.model.ChatModel;
import client.model.ChatModelManager;
import client.network.Client;

import java.rmi.RemoteException;

public class ModelFactory {

    private ChatModel chatModel;
    private ClientFactory clientFactory = new ClientFactory();

    public ModelFactory()
    {

    }

    public ChatModel getChatModel()
    {
        if (chatModel == null)
        {
            chatModel = new ChatModelManager(clientFactory.getClient());
        }
        return chatModel;
    }
}
