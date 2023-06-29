package com.example.bankapp.controller;

import com.example.bankapp.model.BankAppCurrency;
import com.example.bankapp.model.LoadedData;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.model.Wallet;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import java.io.IOException;

import static com.example.bankapp.model.LoadedData.*;

public class TransactionHistoryWindowController extends BaseController{

    Iterator<Transaction> transactionIterator = getLoadedTransactionHistoryList().listIterator();



    @FXML
    private Button TransactionHistoryReturnButton;

    @FXML
    private TableView<Transaction> TransactionTableView;

    @FXML
    private TableColumn<Transaction, BankAppCurrency> columnCurrency;

    @FXML
    private TableColumn<Transaction, String> columnReceiver;

    @FXML
    private TableColumn<Transaction, String> columnSender;

    @FXML
    private TableColumn<Transaction, LocalDate> columnTransactionDate;

    @FXML
    private TableColumn<Transaction, Long> columnTransactionID;

    @FXML
    private TableColumn<Transaction, BigDecimal> columnValue;

    @FXML
    public void initialize(){


        ObservableList<Transaction> observableTransactionList = FXCollections.observableList(getLoadedTransactionHistoryList());
        TransactionTableView.setItems(observableTransactionList);

        while (transactionIterator.hasNext())
        {
            Transaction transaction = transactionIterator.next();
            columnTransactionID.setCellValueFactory(data -> new SimpleObjectProperty<Long>(data.getValue().getTransactionID()));
            columnCurrency.setCellValueFactory(data -> new SimpleObjectProperty<BankAppCurrency>(data.getValue().getCurrency()));
            columnReceiver.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue().getReceiver().getFirstName()+" "+data.getValue().getReceiver().getLastName()));
            columnSender.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue().getSender().getFirstName()+" "+data.getValue().getSender().getLastName()));
            columnTransactionDate.setCellValueFactory(data -> new SimpleObjectProperty<LocalDate>(data.getValue().getDateOfTransaction()));
            columnValue.setCellValueFactory(data -> new SimpleObjectProperty<BigDecimal>(data.getValue().getValue()));
        }




    }

    @FXML
    void TransactionHistoryReturnButtonOnPress(ActionEvent event) throws IOException {

        refreshUserData();
        switchToMainWindow(event);


    }

}
