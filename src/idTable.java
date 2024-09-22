import java.util.HashMap;

public class idTable {
    private HashMap<String, Integer> idMap;
    private int adressCount;
   public idTable() {
       idMap = new HashMap<>();
       adressCount = 0;
   }
   public void add(String id){
       if(!idMap.containsKey(id)){
           idMap.put(id, adressCount++);
       }
   }
   public int getAdresse(String id){
       return idMap.getOrDefault(id, -1);
   }

    @Override
    public String toString() {
        return idMap.toString();
    }
}
