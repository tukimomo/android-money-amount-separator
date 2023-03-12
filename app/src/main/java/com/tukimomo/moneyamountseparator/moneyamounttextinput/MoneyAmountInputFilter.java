package com.tukimomo.moneyamountseparator.moneyamounttextinput;

import android.text.InputFilter;
import android.text.Spanned;

import org.apache.commons.lang3.StringUtils;

public class MoneyAmountInputFilter implements InputFilter {
    public static final String ZERO_DOT = "0.";
    public static final String ZERO = "0";
    public static final String DOT_SYMBOL = ".";

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String inputString = String.valueOf(source);
        String currentModifiedString = String.valueOf(dest);
        if(StringUtils.equals(inputString, DOT_SYMBOL)) {
            if(StringUtils.isBlank(currentModifiedString)){
                return ZERO_DOT;
            } else {
                if (StringUtils.contains(currentModifiedString, DOT_SYMBOL)){
                    return StringUtils.EMPTY;
                }
            }
        }
        if (StringUtils.equals(inputString, ZERO)){
            return ZERO;
        }
        return null;
    }
}
