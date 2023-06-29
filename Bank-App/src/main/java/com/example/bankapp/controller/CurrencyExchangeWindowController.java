package com.example.bankapp.controller;

import com.example.bankapp.model.BankAppCurrency;
import com.example.bankapp.model.Wallet;
import com.example.bankapp.repository.TransactionRepositoryJPA;
import com.example.bankapp.repository.UserRepositoryJPA;
import com.example.bankapp.repository.WalletRepositoryJPA;
import com.example.bankapp.utility.ExchangeCurrency;
import com.example.bankapp.utility.StringCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.math.BigDecimal;

import static com.example.bankapp.model.LoadedData.getLoginID;
import static com.example.bankapp.model.LoadedData.refreshUserData;

public class CurrencyExchangeWindowController extends BaseController{


    static WalletRepositoryJPA walletRepository = new WalletRepositoryJPA();


    @FXML
    private ChoiceBox<BankAppCurrency> CurrencyExchangeBaseCurrency = new ChoiceBox<BankAppCurrency>();

    @FXML
    private Button CheckButton;

    @FXML
    private Label CheckButtonLabel;

    @FXML
    private Label CurrencyExchangeBaseValueChange;

    @FXML
    private Label CurrencyExchangeBaseWalletBalance;

    @FXML
    private Label CurrencyExchangeBaseWalletCurrency;

    @FXML
    private Button CurrencyExchangeCancelButton;

    @FXML
    private Button CurrencyExchangeConfirmButton;

    @FXML
    private Label CurrencyExchangeTargetValueChange;

    @FXML
    private Label CurrencyExchangeTargetWalletBalance;

    @FXML
    private Label CurrencyExchangeTargetWalletCurrency;

    @FXML
    private TextField CurrencyExchangeUserExchangeInputValue;

    @FXML
    private Label CurrencyExchangeWindowExchangeRateDisplay;

    //+
    @FXML
    private Label NewBalanceText;

    @FXML
    private ChoiceBox<BankAppCurrency> CurrencyExchangeWindowTargetCurrency = new ChoiceBox<BankAppCurrency>();

    @FXML
    private void initialize(){
        CurrencyExchangeBaseCurrency.getItems().setAll(BankAppCurrency.values());
        CurrencyExchangeWindowTargetCurrency.getItems().setAll(BankAppCurrency.values());
        CurrencyExchangeBaseCurrency.setValue(BankAppCurrency.EUR);
        CurrencyExchangeWindowTargetCurrency.setValue(BankAppCurrency.EUR);


        CurrencyExchangeConfirmButton.setDisable(true);
        NewBalanceText.setText("New Balance: ");
        NewBalanceText.setVisible(false);
        CheckButtonLabel.setText("");
        CurrencyExchangeBaseValueChange.setText("");
        CurrencyExchangeBaseWalletBalance.setText("");
        CurrencyExchangeBaseWalletCurrency.setText("");
        CurrencyExchangeTargetValueChange.setText("");
        CurrencyExchangeWindowExchangeRateDisplay.setText("");
        CurrencyExchangeTargetWalletBalance.setText("");
        CurrencyExchangeTargetWalletCurrency.setText("");
    }

    @FXML
    void CheckButtonOnPress(ActionEvent event) throws IOException {

        String exchangeValue = CurrencyExchangeUserExchangeInputValue.getText();
        BankAppCurrency baseCurrency = CurrencyExchangeBaseCurrency.getValue();
        BankAppCurrency targetCurrency = CurrencyExchangeWindowTargetCurrency.getValue();

        if (StringCheck.ValidString(exchangeValue, 3)&& baseCurrency!=targetCurrency){

            exchangeValue = exchangeValue.replace(',','.');
            BigDecimal exchangeValueBigDecimal = new BigDecimal(exchangeValue);
            Wallet baseWallet = walletRepository.readByIDandCurrency(getLoginID(),baseCurrency);
            Wallet targetWallet = walletRepository.readByIDandCurrency(getLoginID(),targetCurrency);

            BigDecimal diff = baseWallet.getWalletValue().subtract(exchangeValueBigDecimal);

            if (diff.compareTo(BigDecimal.ZERO)>=0){

                //Calculations + Display:

                BigDecimal rate = ExchangeCurrency.toTargetRateJSON(CurrencyExchangeBaseCurrency.getValue(),CurrencyExchangeWindowTargetCurrency.getValue());
                BigDecimal targetWalletAddedValue = exchangeValueBigDecimal.multiply(rate);
                BigDecimal baseWalletDifference = exchangeValueBigDecimal.multiply(new BigDecimal("-1"));
                BigDecimal baseWalletBeforeExchange = baseWallet.getWalletValue();
                BigDecimal baseWalletAfterExchange = baseWalletBeforeExchange.subtract(exchangeValueBigDecimal);
                BigDecimal targetWalletBeforeExchange = targetWallet.getWalletValue();
                BigDecimal targetWalletAfterExchange = targetWalletBeforeExchange.add(targetWalletAddedValue);

                NewBalanceText.setVisible(true);
                CurrencyExchangeBaseValueChange.setText(String.valueOf(baseWalletDifference)+" ");
                CurrencyExchangeBaseWalletBalance.setText(String.valueOf(baseWalletAfterExchange)+" ");
                CurrencyExchangeBaseWalletCurrency.setText(baseCurrency.toString()+" ");
                CurrencyExchangeTargetValueChange.setText("+"+String.valueOf(targetWalletAddedValue)+" ");
                CurrencyExchangeWindowExchangeRateDisplay.setText("Current exchange rate for "+baseCurrency+" to "+targetCurrency+": "+String.valueOf(rate)+" ");
                CurrencyExchangeTargetWalletBalance.setText(String.valueOf(targetWalletAfterExchange)+" ");
                CurrencyExchangeTargetWalletCurrency.setText(targetCurrency.toString()+" ");
                CurrencyExchangeConfirmButton.setDisable(false);




            } else {
                CheckButtonLabel.setText("Value exceeds wallet limit.");
            }

        }
        else {
            CheckButtonLabel.setText("Invalid Input.");
        }

    }

    @FXML
    void CurrencyExchangeConfirmButtonOnPress(ActionEvent event) throws IOException {
        String exchangeValue = CurrencyExchangeUserExchangeInputValue.getText();
        BankAppCurrency baseCurrency = CurrencyExchangeBaseCurrency.getValue();
        BankAppCurrency targetCurrency = CurrencyExchangeWindowTargetCurrency.getValue();

        if (StringCheck.ValidString(exchangeValue, 3) && baseCurrency!=targetCurrency){

            exchangeValue = exchangeValue.replace(',','.');
            BigDecimal exchangeValueBigDecimal = new BigDecimal(exchangeValue);
            Wallet baseWallet = walletRepository.readByIDandCurrency(getLoginID(),baseCurrency);
            Wallet targetWallet = walletRepository.readByIDandCurrency(getLoginID(),targetCurrency);

            BigDecimal diff = baseWallet.getWalletValue().subtract(exchangeValueBigDecimal);

            if (diff.compareTo(BigDecimal.ZERO)>=0){

                //Calculations + DB changes:

                BigDecimal rate = ExchangeCurrency.toTargetRateJSON(CurrencyExchangeBaseCurrency.getValue(),CurrencyExchangeWindowTargetCurrency.getValue());
                BigDecimal targetWalletAddedValue = exchangeValueBigDecimal.multiply(rate);
                BigDecimal baseWalletDifference = exchangeValueBigDecimal.multiply(new BigDecimal("-1"));
                BigDecimal baseWalletBeforeExchange = baseWallet.getWalletValue();
                BigDecimal baseWalletAfterExchange = baseWalletBeforeExchange.subtract(exchangeValueBigDecimal);
                BigDecimal targetWalletBeforeExchange = targetWallet.getWalletValue();
                BigDecimal targetWalletAfterExchange = targetWalletBeforeExchange.add(targetWalletAddedValue);

                baseWallet.setWalletValue(baseWalletAfterExchange);
                targetWallet.setWalletValue(targetWalletAfterExchange);

                walletRepository.update(baseWallet);
                walletRepository.update(targetWallet);


                refreshUserData();
                switchToMainWindow(event);




            } else {
                CheckButtonLabel.setText("Value exceeds wallet limit.");
            }

        }
        else {
            CheckButtonLabel.setText("Invalid Input.");
        }



    }

    @FXML
    void CurrencyExchangeCancelButtonOnPress(ActionEvent event) throws IOException {

        refreshUserData();
        switchToMainWindow(event);


    }


    @FXML
    void ExchangeInputClick(MouseEvent event) {

        if (!CurrencyExchangeConfirmButton.isDisable())
        {
            CurrencyExchangeConfirmButton.setDisable(true);
        }

    }


    @FXML
    void baseCurrencyChange(MouseEvent event) {

        if (!CurrencyExchangeConfirmButton.isDisable())
        {
            CurrencyExchangeConfirmButton.setDisable(true);
        }

    }

    @FXML
    void targetCurrencyChange(MouseEvent event) {

        if (!CurrencyExchangeConfirmButton.isDisable())
        {
            CurrencyExchangeConfirmButton.setDisable(true);
        }

    }

}
