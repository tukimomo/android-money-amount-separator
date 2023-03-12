package com.tukimomo.moneyamountseparator.moneyamounttextinput;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class MoneyAmountTextInput extends TextInputEditText {
    public MoneyAmountTextInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.addTextChangedListener(new MoneyAmountInputTextWatcher(this));
        this.setFilters(new InputFilter[]{new MoneyAmountInputFilter()});
    }
}
