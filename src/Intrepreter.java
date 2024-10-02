import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Intrepreter {
private ArrayList<Integer> memory;
private idTable idTable;
private final int LOAD =0;
private final int LOADI =1;
private final int STORE =2;
private int index=0;
Parser parser;
Lexer lexer;
    public Intrepreter() {
        this.memory = new ArrayList<>();
        this.idTable = new idTable();
    }
    public ArrayList<Integer> intrepreterProgram(ArrayList<Integer>byteCode){
        int value=0;
        for (int i=0;i < byteCode.size();i++){
            memory.add(i,0);
        }
        while(index<byteCode.size()-1){
            if(byteCode.get(index)!=STORE){
                if(byteCode.get(index)==LOADI){
index++;
                    value+=byteCode.get(index);

                }if(byteCode.get(index)==LOAD){
index++;
                    value+=memory.get(byteCode.get(index));

                }
            }else if(byteCode.get(index)==STORE){
index++;
                memory.set(byteCode.get(index),value );
                value=0;

            }
            index++;
        }
        return memory;
    }

    @Override
    public String toString() {
        return memory.toString();
    }
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,4,2,0,0,0,1,4,2,1));
        System.out.println(list.toString());
        Intrepreter it = new Intrepreter();
       System.out.println(it.intrepreterProgram(list));
    }
}

