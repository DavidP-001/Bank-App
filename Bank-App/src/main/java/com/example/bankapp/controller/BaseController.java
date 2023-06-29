package com.example.bankapp.controller;

import com.example.bankapp.BankAppMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class BaseController {
    private Scene scene;
    private Stage stage;


    //Methods for Window switching:
    public void switchToLoginWindow(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(BankAppMain.class.getResource("LoginWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMainWindow(ActionEvent event) throws IOException{

//        root = FXMLLoader.load((getClass().getClassLoader().getResource("MainWindow.fxml")));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

//        Test:
//        System.out.println(BankAppMain.class.getResource("MainWindow.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(BankAppMain.class.getResource("MainWindow.fxml"));

        //??????:
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToCurrencyExchangeWindow(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(BankAppMain.class.getResource("CurrencyExchangeWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNewTransactionWindow(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(BankAppMain.class.getResource("NewTransactionWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransactionHistoryWindow(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(BankAppMain.class.getResource("TransactionHistoryWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setScene(scene);
        stage.show();
    }


}
