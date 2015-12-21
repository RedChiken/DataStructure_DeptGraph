import java.util.HashMap;

class DeptGraph {
    private final HashMap<String, HashMap<String, Integer>> dept;

    DeptGraph() {
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
        
    }
}