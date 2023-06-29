package com.example.bankapp.controller;

import com.example.bankapp.BankAppMain;
import com.example.bankapp.model.LoadedData;
import com.example.bankapp.model.User;
import com.example.bankapp.repository.UserRepositoryJPA;
import com.example.bankapp.utility.StringCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankAppDBjpa");
    static EntityManager em = emf.createEntityManager();
    static UserRepositoryJPA userRepository = new UserRepositoryJPA();






    @FXML
    private ImageView LoginWindowLogoImage;

    @FXML
    private PasswordField LoginWindowPasswordInputWindow;


    @FXML
    private Label LoginWindowStatusMessage;

    @FXML
    private TextField LoginWindowUserInputWindow = new TextField();


    @FXML
    private Button LoginWindowLoginButton;

    @FXML
    private void initialize(URL url, ResourceBundle rb){
        LoginWindowLogoImage.setImage(new Image("/logo.png"));
        LoginWindowStatusMessage.setText("");
    }

    @FXML
    void LoginWindowLoginButtonOnPress(ActionEvent event) throws IOException {

        String userInputID = LoginWindowUserInputWindow.getText();
        String userInputPassword = LoginWindowPasswordInputWindow.getText();

        if(StringCheck.ValidString(userInputID,2) && !(StringCheck.ValidString(userInputPassword,0))) {


            Optional<User> user = userRepository.readByLoginDataOnlyID(Integer.parseInt(userInputID));


            if (Optional.ofNullable(user).isEmpty()) {
                LoginWindowStatusMessage.setText("ID/password combination is wrong.");

            } else {

                String string = user.get().getPassword();
                if (BCrypt.checkpw(userInputPassword,string))
                {
                    LoadedData.setLoginID(user.get().getUserID());
                    LoadedData.refreshUserData();
                    switchToMainWindow(event);
                }
                else {
                    LoginWindowStatusMessage.setText("ID/password combination is wrong.");
                }

            }
        }
        else {
            LoginWindowStatusMessage.setText("Invalid Input.");
        }


    }




}
