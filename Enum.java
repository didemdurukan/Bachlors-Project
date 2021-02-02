package com.example.test2;

public class Enum {

    public enum House {
        CHOOSE("Choose from below"),RENT("Rent"),MORTAGE("Mortage"),NONE("None"),OWN("Own"),OTHER("Other");
        private String value_house;
        private House(String value){ this.value_house = value; }
        @Override public String toString() {
            return value_house;
        }
    };

    public enum Purpose {
        CHOOSE("Choose from below"),CREDIT_CARD("Credit Card"),DEPT_CONSIDILATION("Dept Considilation"),EDUCATIONAL("Educational"),HOME_IMPROVMENT("Home Improvment"),HOUSE("House"),MAJOR_PURPOSES("Major Purposes"),MEDICAL("Medical"),MOVING("Moving"),OTHER("Other"),RENEWABLE_ENERGY("Renewable Energy"),SMALL_BUSINESS("Small Business"),VACATION("Vacation"),WEDDING("Wedding");
        private String value_purpose;
        private Purpose(String value){ this.value_purpose = value; }
        @Override public String toString() {
            return value_purpose;
        }

    };
    public enum EmploymentLen {
        CHOOSE("Choose from below"),TENYEARSANDMORE("10 Years and More"),TWOYEARS("2 Years"),THREEYEARS("3 Years"),FOURYEARS("4 Years"),FIVEYEARS("5 Years"),SIXYEARS("6 Years"),SEVENYEARS("7 Years"),EIGHTYEARS("8 Years"),NINEYEARS("9 Years"),ONEANDLESS("1 Years and Less");
        private String value_employment;
        private EmploymentLen(String value){this.value_employment = value;}
        @Override public String toString() {
            return value_employment;
        }
    };
}
