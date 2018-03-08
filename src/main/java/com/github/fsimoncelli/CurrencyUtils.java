package com.github.fsimoncelli;

import java.util.Currency;
import java.util.Iterator;

public class CurrencyUtils {
    private CurrencyUtils() {
    }

    public static Currency getCurrencyObjectFromCurrencyNumericCode(Integer currencyNumericCode) {
        Currency currency = null;
        if (currencyNumericCode != null) {
            Iterator i$ = Currency.getAvailableCurrencies().iterator();

            while(i$.hasNext()) {
                Currency cur = (Currency)i$.next();
                if (cur.getNumericCode() == currencyNumericCode.intValue()) {
                    currency = cur;
                    break;
                }
            }
        }

        return currency;
    }

    public static Currency getCurrencyObjectFromStringCurrencyCode(String currencyCode) {
        Currency currency = null;
        if (currencyCode != null) {
            currency = Currency.getInstance(currencyCode);
        }

        return currency;
    }

    public static String getCurrencyCodeFromNumericCode(Integer currencyNumericCode) {
        String currencyCode = null;
        Currency currency = getCurrencyObjectFromCurrencyNumericCode(currencyNumericCode);
        if (currency != null) {
            currencyCode = currency.getCurrencyCode();
        }

        return currencyCode;
    }

    public static Integer getCurrencyNumericCodeFromStringCurrencyCode(String currencyCode) {
        Integer currencyNumericCode = null;
        Currency currencyObject = getCurrencyObjectFromStringCurrencyCode(currencyCode);
        if (currencyObject != null) {
            currencyNumericCode = currencyObject.getNumericCode();
        }

        return currencyNumericCode;
    }

    public static Integer getCurrencyDefaultFractionDigitsFromCurrencyCode(String currencyCode) {
        Integer currencyDefaultFractionDigits = null;
        Currency currency = getCurrencyObjectFromStringCurrencyCode(currencyCode);
        if (currency != null) {
            currencyDefaultFractionDigits = currency.getDefaultFractionDigits();
        }

        return currencyDefaultFractionDigits;
    }
}
