import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Intrepreter {
    private final ArrayList<Integer> memory;
    private static final int LOAD = 0;
    private static final int LOADI = 1;
    private static final int STORE = 2;
    private int memoryInt;
    private int memoryId;
    private int memoryValue;
    private int index;

    public Intrepreter() throws IOException {
        memory = new ArrayList<>();
        index = 0;
        memoryInt = 0;
        memoryId = 0;


        for (int i = 0; i < 10; i++) {
            memory.add(0);
        }
    }

    public void interpreterProgram(ArrayList<Integer> bytecode) {
        while (index < bytecode.size()) {
            int opcode = bytecode.get(index);
            switch (opcode) {
                case LOAD:
                    load(bytecode);
                    break;
                case LOADI:
                    loadi(bytecode);
                    break;
                case STORE:
                    store(bytecode);
                    break;
                default:
                    index++;
                    break;
            }
        }
    }

    public void load(ArrayList<Integer> bytecode) {
        index++;
        memoryId=0;
        if (index < bytecode.size()) {
            while(bytecode.get(index)!=STORE ) {
                if(bytecode.get(index-1)==LOAD) {
                    int memAddress = bytecode.get(index);
                    memoryValue += memory.get(index);
                }
            index++;}
        }
    }

    public void loadi(ArrayList<Integer> bytecode) {
        index++;
        memoryInt =0;
        if (index < bytecode.size()) {
            while(bytecode.get(index)!=STORE ) {

            int value = bytecode.get(index);
            memoryValue += value;
            index++;}
        }
    }

    public void store(ArrayList<Integer> bytecode) {
        index++;
       int  temp = memoryInt+memoryId;
        if (index < bytecode.size()) {
            int memAddress = bytecode.get(index);
            memory.set(memAddress, memoryValue);
            index++;
        }
        memoryValue =0;
    }

    public String toString() {
        return memory.toString();
    }

    public static void main(String[] args) throws IOException {
        // Sample bytecode to test
        ArrayList<Integer> bytecode = new ArrayList<>();
        bytecode.add(1);bytecode.add(83);bytecode.add(0);bytecode.add(1);bytecode.add(2);bytecode.add(0);
        Intrepreter intrepreter = new Intrepreter();
        intrepreter.interpreterProgram(bytecode);
        System.out.println(intrepreter);
    }
}
