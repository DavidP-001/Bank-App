package com.example.bankapp.controller;

import com.example.bankapp.model.LoadedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.bankapp.model.LoadedData.*;

public class MainWindowController extends BaseController {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Date date = new Date();

    @FXML
    private Button MainWindowCurrencyExchangeButton;

    @FXML
    private Label MainWindowDateLabel;

    @FXML
    private ImageView MainWindowLogoImage;

    @FXML
    private Label MainWindowMainBalance = new Label("Test");

    @FXML
    private Label MainWindowMainBalanceCurrency;

    @FXML
    private Button MainWindowNewTransactionButton;

    @FXML
    private Label MainWindowSideBalance1Currency;

    @FXML
    private Label MainWindowSideBalance1Value;

    @FXML
    private Label MainWindowSideBalance2Currency;

    @FXML
    private Label MainWindowSideBalance2Value;

    @FXML
    private Label MainWindowSideBalance3Currency;

    @FXML
    private Label MainWindowSideBalance3Value;

    @FXML
    private Label MainWindowSideBalance4Currency;

    @FXML
    private Label MainWindowSideBalance4Value;

    @FXML
    private Label MainWindowSideBalance5Currency;

    @FXML
    private Label MainWindowSideBalance5Value;

    @FXML
    private Button MainWindowTransactionHistory;

    @FXML
    private Label MainWindowUserName = new Label("Test") ;

    @FXML
    public void initialize(){
        MainWindowUserName.setText(loadedUser.get().getFirstName().toString());
        MainWindowMainBalance.setText(getLoadedWallets().get(0).getWalletValue().toString());
        MainWindowMainBalanceCurrency.setText(" "+getLoadedWallets().get(0).getCurrency().toString());

        MainWindowSideBalance1Value.setText(getLoadedWallets().get(1).getWalletValue().toString());
        MainWindowSideBalance1Currency.setText(" "+getLoadedWallets().get(1).getCurrency().toString());

        MainWindowSideBalance2Value.setText(getLoadedWallets().get(2).getWalletValue().toString());
        MainWindowSideBalance2Currency.setText(" "+getLoadedWallets().get(2).getCurrency().toString());

        MainWindowSideBalance3Value.setText(getLoadedWallets().get(3).getWalletValue().toString());
        MainWindowSideBalance3Currency.setText(" "+getLoadedWallets().get(3).getCurrency().toString());

        MainWindowSideBalance4Value.setText(getLoadedWallets().get(4).getWalletValue().toString());
        MainWindowSideBalance4Currency.setText(" "+getLoadedWallets().get(4).getCurrency().toString());

        MainWindowSideBalance5Value.setText(getLoadedWallets().get(5).getWalletValue().toString());
        MainWindowSideBalance5Currency.setText(" "+getLoadedWallets().get(5).getCurrency().toString());

        MainWindowDateLabel.setText(dateFormat.format(date));
    }



    @FXML
    void MainWindowNewTransactionButtonOnPress(ActionEvent event) throws IOException {

        switchToNewTransactionWindow(event);
        refreshUserData();

    }

    @FXML
    void MainWindowCurrencyExchangeButtonOnPress(ActionEvent event) throws IOException {

        switchToCurrencyExchangeWindow(event);
        refreshUserData();

    }

    @FXML
    void MainWindowTransactionHistoryOnPress(ActionEvent event) throws IOException {

        switchToTransactionHistoryWindow(event);
        refreshUserData();

    }




}
