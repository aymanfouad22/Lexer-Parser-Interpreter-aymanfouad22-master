import java.util.HashMap;

public class idTable {
    private HashMap<String, Integer> idMap;
    private int adresseCount;
   public idTable() {
       idMap = new HashMap<>();
       adresseCount = 0;
   }
   public void add(String id){
       if(!idMap.containsKey(id)){
           idMap.put(id, adresseCount++);
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
