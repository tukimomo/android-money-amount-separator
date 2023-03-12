package com.tukimomo.moneyamountseparator.moneyamounttextinput;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MoneyAmountInputTextWatcher implements TextWatcher {
    private final EditText currencyInput;

    public MoneyAmountInputTextWatcher(EditText currencyInput){
        this.currencyInput = currencyInput;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        String formattedMoneyAmount = MoneyAmountFormatter.format(String.valueOf(editable));
        updateDisplayedAmount(editable, formattedMoneyAmount);
    }

    private void updateDisplayedAmount(Editable editable, String displayedAmount) {
        currencyInput.removeTextChangedListener(this);
        editable.replace(0, editable.length(), displayedAmount);
        currencyInput.addTextChangedListener(this);
    }
}
