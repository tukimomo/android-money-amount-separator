package com.tukimomo.moneyamountseparator.moneyamounttextinput;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class MoneyAmountFormatterTest {
    @Test
    public void isANumberString_givenAStringThatIsNotANumber_shouldReturnFalse(){
        String testString = "abc";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertFalse(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsAIntegerNumber_shouldReturnTrue(){
        String testString = "1";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsAIntegerNumberThatHasMoreThanOneDigit_shouldReturnTrue(){
        String testString = "100";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsANegativeIntegerNumber_shouldReturnTrue(){
        String testString = "-100";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsADecimalNumber_shouldReturnTrue(){
        String testString = "1000.05";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsASeparatedFormattedDecimalNumber_shouldReturnTrue(){
        String testString = "1,000.05";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsANegativeDecimalNumber_shouldReturnTrue(){
        String testString = "-1000.05";
        boolean actual = MoneyAmountFormatter.isANumberString(testString);
        assertTrue(actual);
    }

    @Test
    public void isANumberString_givenAStringThatIsNull_shouldReturnFalse(){
        assertFalse(MoneyAmountFormatter.isANumberString(null));
    }

    @Test
    public void isANumberString_givenAStringThatIsEmpty_shouldReturnFalse(){
        assertFalse(MoneyAmountFormatter.isANumberString(StringUtils.EMPTY));
    }

    @Test
    public void isANumberString_givenAStringThatContainsNumbersAndEndsWithADot_shouldReturnTrue(){
        String testString = "10.";
        assertTrue(MoneyAmountFormatter.isANumberString(testString));
    }

    @Test
    public void normalize_givenAStringContainsComma_shouldReturnACorrespondingStringWithoutComma(){
        String testString = "1,000";
        String expect = "1000";
        String actual = MoneyAmountFormatter.normalize(testString);
        assertTrue(StringUtils.equals(actual, expect));
    }

    @Test
    public void format_whenGivenANumberString_shouldReturnFormattedString(){
        String testString ="1000.05";
        String expected = "1,000.05";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(actual, expected);
    }

    @Test
    public void format_whenGivenAnAlreadyFormattedNumberString_shouldReturnTheSameFormattedString(){
        String testString ="1,000.05";
        String expected = "1,000.05";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(actual, expected);
    }

    @Test
    public void format_whenGivenNumberStringThatStartsWithAZeroAndDot_shouldReturnTheSameFormattedString(){
        String testString ="0.";
        String expected = "0.";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(actual, expected);
    }

    @Test
    public void format_whenGivenAnEmptyString_shouldReturnEmptyString(){
        String testString = "";
        String expected = "";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(expected, actual);
    }

    @Test
    public void format_whenGivenAnNegativeIntegerNumberString_shouldReturnTheFormattedString(){
        String testString = "-1000";
        String expected = "-1,000";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(expected, actual);
    }

    @Test
    public void format_whenGivenAnNegativeDecimalNumberString_shouldReturnTheFormattedString(){
        String testString = "-1000.05";
        String expected = "-1,000.05";
        String actual = MoneyAmountFormatter.format(testString);
        assertEquals(expected, actual);
    }
}