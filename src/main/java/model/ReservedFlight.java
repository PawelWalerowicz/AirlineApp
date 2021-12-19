package model;

import java.util.Objects;

public class ReservedFlight {
    Flight flight;
    Account account;
    int amount;

    public ReservedFlight(Flight flight, Account account, int amount) {
        this.flight = flight;
        this.account = account;
        this.amount = amount;
    }


    private Price getTotalPrice() {
        return new Price(flight.getPrice().getPrice()*amount);
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedFlight that = (ReservedFlight) o;
        return amount == that.amount && Objects.equals(flight, that.flight) && Objects.equals(account, that.account);
    }

    @Override
    public String toString() {
        if(amount==1) {
            return flight.toString()+" (one ticket reserved).\n";
        } else {
            return flight.toString()
                    + " (" + getTotalPrice()
                    + " for " + getAmount() + " tickets).\n";
        }

    }
}
