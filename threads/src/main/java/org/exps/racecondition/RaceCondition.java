package org.exps.racecondition;

public class RaceCondition {
    public static void main(String[     ] args) {
        Account acc = new Account(20);
        Client father = new Client("Father", acc);
        Client mother = new Client("Mother", acc);
        Client daughter = new Client("Daughter", acc);
        Client son = new Client("Son", acc);

        father.start();
        mother.start();
        son.start();
        daughter.start();
    }
}
