package com.github.fsimoncelli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AmountUtils {
    private static final Logger LOG = LoggerFactory.getLogger(AmountUtils.class);
    public static final char DEFAULT_DECIMAL_SEPARATOR = '.';

    private AmountUtils() {
    }

    public static String convertAmountDecimalPlacesWithoutSeparator(String initialAmount, Integer originalDecimalPlaces, Integer newDecimalPlaces) {
        String newAmount = "";
        if (StringUtils.hasText(initialAmount)) {
            String decSep = Character.toString('.');
            newAmount = convertAmountWithDecimalPlaces(initialAmount.replace(decSep, ""), originalDecimalPlaces, newDecimalPlaces);
            newAmount = newAmount.replace(decSep, "");
            LOG.debug("input amount: {} output amount {}", initialAmount, newAmount);
        }

        return newAmount;
    }

    public static String convertAmountWithDecimalPlaces(String initialAmount, Integer originalDecimalPlaces, Integer newDecimalPlaces) {
        return convertAmountWithDecimalPlaces(initialAmount, originalDecimalPlaces, newDecimalPlaces, '.');
    }

    public static String convertAmountWithDecimalPlaces(String initialAmount, Integer originalDecimalPlaces, Integer newDecimalPlaces, char decimalSeparator) {
        String newAmount = "";
        if (StringUtils.hasText(initialAmount)) {
            BigDecimal totalAmountBigDecimal = BigDecimal.valueOf(Long.parseLong(initialAmount), originalDecimalPlaces.intValue());
            DecimalFormat df = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(decimalSeparator);
            df.setMaximumFractionDigits(newDecimalPlaces.intValue());
            df.setMinimumFractionDigits(newDecimalPlaces.intValue());
            df.setDecimalFormatSymbols(symbols);
            df.setGroupingUsed(false);
            df.setRoundingMode(RoundingMode.HALF_UP);
            newAmount = df.format(totalAmountBigDecimal);
            LOG.debug("input amount: {} output amount {}", initialAmount, newAmount);
        }

        return newAmount;
    }
}
