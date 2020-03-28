package com.volkodav4ik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    private static final String DATE_FORMATTTER = "dd.MM.yyyy";
    private static final String BEGIN_DATE = "01.12.2008";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMATTTER);
    private static final Scanner SCANNER = new Scanner(System.in);

    static {
        DATE_FORMAT.setLenient(true);
    }

    public static void main(String[] args) {

        System.out.println("Please, enter date in format DD.MM.YYYY:");
        String responseDate = SCANNER.nextLine();
        LocalDate ld = LocalDate.parse(responseDate, DateTimeFormatter.ofPattern(DATE_FORMATTTER));
        LocalDate today = LocalDate.now();
        LocalDate firstDate = LocalDate.parse(BEGIN_DATE, DateTimeFormatter.ofPattern(DATE_FORMATTTER));
        if (!dateIsValid(responseDate)) {
            throw new IllegalArgumentException("You use wrong date format or wrong date. Please, try again.");
        } else {
            if (ld.isAfter(today)) {
                System.out.println("You entered future date");
                System.exit(0);
            } else {
                if (ld.isBefore(firstDate)) {
                    System.out.println("We haven't saved dave data before " + BEGIN_DATE);
                    System.exit(0);
                }
            }
        }
        String urlWithData = URL + responseDate;
        Response response = HttpUtil.sendRequest(urlWithData);
        Currency currency = currencyFromPb(response.getBody());
        System.out.printf("Exchange course of USD in PrivatBank on date %s:\nSale: %.2f\nPurchase: %.2f",
                responseDate, currency.getSaleDollar(), currency.getPurchaseDollar());
    }

    private static boolean dateIsValid(String date) {
        try {
            return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
        } catch (ParseException ex) {
            return false;
        }
    }

    private static Currency currencyFromPb(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Currency.class);
    }
}