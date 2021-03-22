package client.core;

import client.view.chatroom.ChatRoomVM;
import client.view.clinetswindow.ClientsVM;
import client.view.usernamewindow.UserNameVM;

public class ViewModelFactory {

    private ChatRoomVM chatRoomViewVM;
    private UserNameVM userNameViewVM;
    private ClientsVM clientsVM;

    public ViewModelFactory(ModelFactory mf)
    {
        userNameViewVM = new UserNameVM(mf.getChatModel());
        chatRoomViewVM = new ChatRoomVM(mf.getChatModel());
        clientsVM = new ClientsVM(mf.getChatModel());
    }

    public UserNameVM getUserNameViewVM()
    {
        return userNameViewVM;
    }

    public ChatRoomVM getChatRoomViewVM()
    {
        return chatRoomViewVM;
    }

    public ClientsVM getClientsVM()
    {
        return clientsVM;
    }
}
