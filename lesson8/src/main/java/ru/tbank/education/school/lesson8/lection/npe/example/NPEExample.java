package ru.tbank.education.school.lesson8.lection.npe.example;


class Address {
    String city;
}

class Customer {
    Address address;
}

public class NPEExample {
    public static void main(String[] args) {
        Customer c = new Customer();
        // address не инициализирован, равен null
        System.out.println(c.address.city.length()); // java.lang.NullPointerException
    }
}
