import java.util.ArrayList;

public class Instructions {
    String recipeName;
    ArrayList<String> instructions;
    int count = 0;

    public Instructions(String recipeName, ArrayList<String> instructions){
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

//    public String readEntireInstruction(){
//        String entireInstruction = "";
//        int n = instructions.size();
//
//        for (int i = 0; i < n; i++){
//            entireInstruction += String.valueOf(i + 1) + ". " + instructions.get(i);
//
//            if (i != n - 1)	entireInstruction += "\n";
//        }
//
//        return entireInstruction;
//    }
//
//    public String readInstructionStep(){
//        String step = "";
//        int n = instructions.size();
//        if (count < n){
//            step += String.valueOf(count + 1) + ". " + instructions.get(count);
//
//            count++;
//        }
//
//        if (count == n)	resetStepCount();
//
//        return step;
//    }
//
//    public void resetStepCount() {
//        count = 0;
//    }
}
