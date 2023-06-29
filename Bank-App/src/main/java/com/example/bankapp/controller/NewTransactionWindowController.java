package com.example.bankapp.controller;

import com.example.bankapp.model.BankAppCurrency;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.model.User;
import com.example.bankapp.model.Wallet;
import com.example.bankapp.repository.TransactionRepositoryJPA;
import com.example.bankapp.repository.UserRepositoryJPA;
import com.example.bankapp.repository.WalletRepositoryJPA;
import com.example.bankapp.utility.StringCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.example.bankapp.model.BankAppCurrency.EUR;
import static com.example.bankapp.model.LoadedData.*;

public class NewTransactionWindowController extends BaseController{

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankAppDBjpa");
    static EntityManager em = emf.createEntityManager();
    static UserRepositoryJPA userRepository = new UserRepositoryJPA();
    static WalletRepositoryJPA walletRepository = new WalletRepositoryJPA();

    static TransactionRepositoryJPA transactionRepository = new TransactionRepositoryJPA();


    @FXML
    private Button NewTransactionWindowCancelButton;

    @FXML
    private Button NewTransactionWindowConfirmButton;

    @FXML
    private TextField NewTransactionWindowReceiverAccountIdInput;

    @FXML
    private TextField NewTransactionWindowReceiverFirstNameInput;

    @FXML
    private TextField NewTransactionWindowReceiverLastnameInput;

    @FXML
    private TextField NewTransactionWindowSenderAccountIdInput;

    @FXML
    private TextField NewTransactionWindowSenderFirstnameInput;

    @FXML
    private TextField NewTransactionWindowSenderLastnameInput;

    @FXML
    private Label NewTransactionWindowMessage;


    @FXML
    private ChoiceBox<BankAppCurrency> NewTransactionWindowChoiceBox = new ChoiceBox<BankAppCurrency>();

    @FXML
    private TextField NewTransactionWindowSenderAccountIdInputValue;

    @FXML
    public void initialize(){

        NewTransactionWindowSenderAccountIdInput.setText(Integer.toString(loadedUser.get().getUserID()));
        NewTransactionWindowSenderFirstnameInput.setText(loadedUser.get().getFirstName());
        NewTransactionWindowSenderLastnameInput.setText(loadedUser.get().getLastName());

        NewTransactionWindowSenderAccountIdInput.setEditable(false);
        NewTransactionWindowSenderAccountIdInput.setDisable(true);
        NewTransactionWindowSenderFirstnameInput.setEditable(false);
        NewTransactionWindowSenderFirstnameInput.setDisable(true);
        NewTransactionWindowSenderLastnameInput.setEditable(false);
        NewTransactionWindowSenderLastnameInput.setDisable(true);

        NewTransactionWindowChoiceBox.getItems().setAll(BankAppCurrency.values());
        NewTransactionWindowChoiceBox.setValue(EUR);
        NewTransactionWindowMessage.setText("");




    }

    @FXML
    void NewTransactionWindowConfirmButtonPress(ActionEvent event) throws IOException {

        String receiverInputID = NewTransactionWindowReceiverAccountIdInput.getText();
        String receiverInputFirstName = NewTransactionWindowReceiverFirstNameInput.getText();
        String receiverInputLastName = NewTransactionWindowReceiverLastnameInput.getText();
        String senderValue = NewTransactionWindowSenderAccountIdInputValue.getText();

        if(StringCheck.ValidString(receiverInputID,2) && StringCheck.ValidString(receiverInputFirstName,1)
        && StringCheck.ValidString(receiverInputLastName,1) && StringCheck.ValidString(senderValue,3))
        {
            senderValue = senderValue.replace(',','.');
            BigDecimal senderValueBigDecimal = new BigDecimal(senderValue);
            Optional<User> sender = userRepository.readByLoginDataOnlyID(loadedUser.get().getUserID());
            Optional<User> receiver = userRepository.readByLoginDataOnlyID(Integer.parseInt(receiverInputID));

            if ((!Optional.ofNullable(receiver).isEmpty() && receiver.get().getFirstName().equals(receiverInputFirstName)
                    && receiver.get().getLastName().equals(receiverInputLastName)))
            {
                Wallet senderWallet = walletRepository.readByIDandCurrency(getLoginID(),NewTransactionWindowChoiceBox.getValue());
                Wallet receiverWallet = walletRepository.readByIDandCurrency(receiver.get().getUserID(),NewTransactionWindowChoiceBox.getValue());

                BigDecimal diff = senderWallet.getWalletValue().subtract(senderValueBigDecimal);

                if (diff.compareTo(BigDecimal.ZERO)>=0){

                    //Update Wallets in DB

                    senderWallet.setWalletValue(diff);
                    receiverWallet.setWalletValue(receiverWallet.getWalletValue().add(senderValueBigDecimal));

                    walletRepository.update(senderWallet);
                    walletRepository.update(receiverWallet);

                    //Create new Transaction after exchange:

                    Transaction transaction = new Transaction(LocalDate.now(),senderValueBigDecimal,NewTransactionWindowChoiceBox.getValue());
                    transaction.setSender(sender.get());
                    transaction.setReceiver(receiver.get());

                    transactionRepository.insert(transaction);

                    refreshUserData();
                    switchToMainWindow(event);



                }
                else {

                    NewTransactionWindowMessage.setText("Value exceeds wallet limit.");
                }
            }
            else {
                NewTransactionWindowMessage.setText("Receiver Account does not exist.");
            }



        }
        else {
            NewTransactionWindowMessage.setText("Invalid Input.");
        }

    }



    @FXML
    void NewTransactionWindowCancelButtonOnPress(ActionEvent event) throws IOException {

        refreshUserData();
        switchToMainWindow(event);


    }

}
