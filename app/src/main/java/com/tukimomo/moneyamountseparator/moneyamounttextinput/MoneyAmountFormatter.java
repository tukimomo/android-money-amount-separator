package com.tukimomo.moneyamountseparator.moneyamounttextinput;

import android.text.Editable;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyAmountFormatter {
    public static final String NUMBER_PATTER_WITH_ONLY_WHOLE_PART = "#,###";
    public static final String NUMBER_PATTERN_WITHOUT_FRACTIONS = "#,###.";
    public static final String DECIMAL_POINT_REGEX = "\\.";
    public static final String DECIMAL_NUMBER_REGEX = "\\d+\\.([0-9]+)";
    public static final String DECIMAL_POINT_SYMBOL = ".";

    static String format(@NonNull Editable editable) {
        String normalizedInput = String.valueOf(editable).replaceAll("[,-]", "");
        String inputText = StringUtils.defaultIfEmpty(normalizedInput, "0");
        if(StringUtils.endsWith(inputText, DECIMAL_POINT_SYMBOL)){
            return MoneyAmountFormatter.format(inputText, MoneyAmountFormatter.NUMBER_PATTERN_WITHOUT_FRACTIONS);
        }
        if(matchedPattern(inputText, DECIMAL_NUMBER_REGEX)){
            String[] splitDecimalNumber = inputText.split(DECIMAL_POINT_REGEX);
            String fractionPart = splitDecimalNumber[1];
            String formattedWholeNumber = format(splitDecimalNumber[0], NUMBER_PATTER_WITH_ONLY_WHOLE_PART);
            return formattedWholeNumber.concat(DECIMAL_POINT_SYMBOL).concat(fractionPart);
        }
        return MoneyAmountFormatter.format(inputText, MoneyAmountFormatter.NUMBER_PATTER_WITH_ONLY_WHOLE_PART);
    }

    static String format(String inputText, String pattern){
        DecimalFormatSymbols symbols = getDecimalFormatSymbols();
        DecimalFormat format = getDecimalFormat(pattern, symbols);
        return format.format(Double.parseDouble(inputText));
    }

    static boolean matchedPattern(String inputText, String patternString){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputText);
        return matcher.find();
    }

    @NonNull
    private static DecimalFormat getDecimalFormat(String pattern, DecimalFormatSymbols symbols) {
        DecimalFormat format = new DecimalFormat(pattern);
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    @NonNull
    private static DecimalFormatSymbols getDecimalFormatSymbols() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        return symbols;
    }
}
