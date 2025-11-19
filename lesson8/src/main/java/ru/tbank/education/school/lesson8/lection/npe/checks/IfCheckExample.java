package ru.tbank.education.school.lesson8.lection.npe.checks;

class City { String name; }
class Address { City city; }
class Customer { Address address; }
class Order { Customer customer; }

public class IfCheckExample {
    public static void main(String[] args) {
        Order order = new Order();
        int cityNameLen = 0;

        if (order != null) {
            Customer customer = order.customer;
            if (customer != null) {
                Address address = customer.address;
                if (address != null) {
                    City city = address.city;
                    if (city != null) {
                        String name = city.name;
                        if (name != null) {
                            cityNameLen = name.length();
                        }
                    }
                }
            }
        }

        System.out.println("City name length: " + cityNameLen);
    }
}
