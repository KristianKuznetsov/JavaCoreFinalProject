package crs.jcfpapp.services;

import java.util.Objects;

public class Record {
    String inAccount;
    String outAccount;
    String file;
    double amount;

    public Record() {
        this.inAccount = "00000-00000";
        this.outAccount = "00000-00000";
        this.file = "nan";
        this.amount = 0;
    }

    public Record(String inAccount, String outAccount, String file, double amount) {
        this.inAccount = inAccount;
        this.outAccount = outAccount;
        this.file = file;
        this.amount = amount;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    public String getOutAccount() {
        return outAccount;
    }

    public void setOutAccount(String outAccount) {
        this.outAccount = outAccount;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Double.compare(amount, record.amount) == 0 && Objects.equals(inAccount, record.inAccount) && Objects.equals(outAccount, record.outAccount) && Objects.equals(file, record.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inAccount, outAccount, file, amount);
    }

    @Override
    public String toString() {
        return "Record{" +
                "inAccount='" + inAccount + '\'' +
                ", outAccount='" + outAccount + '\'' +
                ", file='" + file + '\'' +
                ", amount=" + amount +
                '}';
    }
}
