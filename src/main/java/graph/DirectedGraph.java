import java.util.HashMap;

class DirectedGraph {
    private final HashMap<String, HashMap<String, Integer>> dept;

    DirectedGraph() {
        dept = new HashMap<>();
    }

    void addDept(String from, String to, int amount) {
        dept.computeIfPresent(from, (str, stringIntegerHashMap) -> {
            stringIntegerHashMap.computeIfPresent(to, (str1, integer) -> integer + amount);
            stringIntegerHashMap.putIfAbsent(to, amount);
            return stringIntegerHashMap;
        });
        HashMap<String, Integer> newDeptRelation = new HashMap<>();
        newDeptRelation.put(to, amount);
        dept.putIfAbsent(from, newDeptRelation);
    }

    void addDept(DeptRelation deptRelation) {
        addDept(deptRelation.getFrom(), deptRelation.getTo(), deptRelation.getAmount());
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        dept.forEach((s, stringIntegerHashMap) -> {
            buffer.append("From : " + s + '\n');
            stringIntegerHashMap.forEach((s1, integer) -> {
                buffer.append('\t' + s1 + " : " + integer + '\n'));
            });
        });

        return buffer.toString();
    }
}