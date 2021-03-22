package client.core;

import client.view.chatroom.ChatRoomController;
import client.view.clinetswindow.ClientsController;
import client.view.usernamewindow.UserNameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler extends Application {

    private Stage stage;
    private ViewModelFactory vmf;


    public ViewHandler(Stage stage, ViewModelFactory vmf) {
        this.stage = stage;
        this.vmf = vmf;
    }

    public void openView(String id) throws IOException
    {
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        Parent root= null;
        if (id.equals("ChatRoom"))
        {
            loader.setLocation(getClass().getResource("../view/chatroom/" + id +"View.fxml"));
            root = loader.load();
            ChatRoomController chatRoomController = loader.getController();
            chatRoomController.init(this, vmf);
            stage.setTitle("Chatroom");
        }
        else if (id.equals("UserName"))
        {
            loader.setLocation(getClass().getResource("../view/usernamewindow/" + id +"View.fxml"));
            root = loader.load();
            UserNameController userNameController = loader.getController();
            userNameController.init(this, vmf);
            stage.setTitle("Username View");
        }
        else if (id.equals("Clients"))
        {
            loader.setLocation(getClass().getResource("../view/clinetswindow/" + id +"View.fxml"));
            root = loader.load();
            ClientsController clientsController = loader.getController();
            clientsController.init(this, vmf);
            stage.setTitle("List of users");
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override public void start(Stage stage) throws Exception
    {
        openView("UserName");
    }
}
