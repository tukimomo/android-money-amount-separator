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
    public static final String DECIMAL_NUMBER_REGEX = "(-)*(\\d+)((\\.)(\\d+))*";
    public static final String DECIMAL_NUMBER_WITH_DOT_SYMBOL_AT_THE_END_REGEX = "(-)*(\\d+)(\\.)+";
    public static final String DECIMAL_POINT_SYMBOL = ".";

    public static String format(String testString) {
        String normalizedString = normalize(testString);
        if(isANumberString(normalizedString)){
            if (normalizedString.endsWith(DECIMAL_POINT_SYMBOL)){
                return format(normalizedString, NUMBER_PATTERN_WITHOUT_FRACTIONS);
            } else if(!normalizedString.endsWith(DECIMAL_POINT_SYMBOL) && normalizedString.contains(DECIMAL_POINT_SYMBOL)){
                return formatDecimalNumber(normalizedString);
            } else {
                return format(normalizedString, NUMBER_PATTER_WITH_ONLY_WHOLE_PART);
            }
        } else if (normalizedString.startsWith("-")){
            return "-";
        }
        return StringUtils.EMPTY;
    }

    @NonNull
    static String formatDecimalNumber(String normalizedString) {
        String[] splitNumber = normalizedString.split(DECIMAL_POINT_REGEX);
        String wholePart = format(splitNumber[0], NUMBER_PATTER_WITH_ONLY_WHOLE_PART);
        String fractionPart = splitNumber[1];
        return wholePart.concat(DECIMAL_POINT_SYMBOL).concat(fractionPart);
    }

    static String format(String inputText, String pattern){
        DecimalFormatSymbols symbols = getDecimalFormatSymbols();
        DecimalFormat format = getDecimalFormat(pattern, symbols);
        return format.format(Double.parseDouble(inputText));
    }

    @NonNull
    static DecimalFormat getDecimalFormat(String pattern, DecimalFormatSymbols symbols) {
        DecimalFormat format = new DecimalFormat(pattern);
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    @NonNull
    static DecimalFormatSymbols getDecimalFormatSymbols() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        return symbols;
    }

    static boolean isANumberString(String inputString) {
        if(StringUtils.isBlank(inputString)){
            return false;
        }
        return normalize(inputString).matches(DECIMAL_NUMBER_REGEX) ||
                normalize(inputString).matches(DECIMAL_NUMBER_WITH_DOT_SYMBOL_AT_THE_END_REGEX);
    }

    static String normalize(String testString) {
        return testString.replaceAll(",", "");
    }
}
