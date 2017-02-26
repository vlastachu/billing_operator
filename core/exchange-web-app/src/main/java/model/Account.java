package model;

public class Account {
    private final String phoneNumber;
    private final int money;

    public Account(String phoneNumber, int money) {
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (money != account.money) return false;
        return phoneNumber != null ? phoneNumber.equals(account.phoneNumber) : account.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = phoneNumber != null ? phoneNumber.hashCode() : 0;
        result = 31 * result + money;
        return result;
    }
}
