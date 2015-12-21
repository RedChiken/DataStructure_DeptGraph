class DeptRelation implements Comparable {
    String from, to;
    int amount;

    DeptRelation(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "DeptRelation{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}