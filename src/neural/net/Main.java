package neural.net;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Main extends Thread{

    public static final Random r = new Random();
    public ArrayList<Double[]> 
            input = new ArrayList<Double[]>(),
            output = new ArrayList<Double[]>();
    public int inputs, outputs;
    Run configurations;
    
    public Main(Run r) {
        super(Long.toString(r.getId()));
        configurations = r;
    }
    private Main(){};
    
    @Override
    public void run(){
        List<Double> generationList;
        long seed;
        Double [] compare;
        int expectedValue;
        int nfound;
        System bob;
        
        /**
         * BEGIN TRAINING...
         */
        loadFile(configurations.getTrain());
        bob = System.createFromList(configurations.getShape());
        bob.setConf(configurations);
        generationList = configurations.getTrainError();
        for(int j = 0; j < configurations.getGenerations(); j++) {
            seed = r.nextLong();
            // Below code shuffles the input and output in the exact same
            // manner because the two seeds are the same. 
            Collections.shuffle(input, new Random(seed + j));
            Collections.shuffle(output, new Random(seed + j));
            nfound = 0;
            for(int i = 0; i < input.size(); i++) {
                compare = bob.run(input.get(i));
                expectedValue = max(output.get(i));
                nfound += max(compare) == expectedValue? 1 : 0;
                bob.train(output.get(i));
            }
            generationList.add( (double) nfound / (double) input.size());
        }
        input.clear();
        output.clear();
        /**
         * BEGIN TESTING...
         */
        loadFile(configurations.getTest());
        bob = System.createFromList(configurations.getShape());
        bob.setConf(configurations);
        nfound = 0;
        for(int i = 0; i < input.size(); i++) {
            compare = bob.run(input.get(i));
            expectedValue = max(output.get(i));
            nfound += max(compare) == expectedValue? 1 : 0;
        }
        configurations.setTestError(nfound / (double) input.size());
        
        
        // BELOW CODE DONE FOR THREADING PURPOSES.
        // I FELT BAD WRITING IT.
        Gson gson = new Gson();
        File f = new File("output/"+configurations.getId()+".json"); f.delete();
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(gson.toJson(configurations));
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Find maximum value in array
     * @param in a Double array to look through
     * @return the index of the largest value;
     */
    private int max (Double[] in) {
        Double max = -2.0;
        int index = -1;
        for (int i = 0; i < in.length; i++) {
            if (max < in[i]){
                max = in[i];
                index = i;
            }
        }
        return index;
    }
    
    private void loadFile (String file){
        Scanner s = null;
        try {
            s = new Scanner(new File(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        inputs = Integer.parseInt(s.next());
        outputs = Integer.parseInt(s.next());
        StringTokenizer token;
        ArrayList<Double> temp = new ArrayList<Double>();
        
        while (s.hasNextLine()) {
            token = new StringTokenizer(s.nextLine(), ",\t\r\n");
            if (token.countTokens() != inputs + outputs) {
                continue;
            }
            for(int i = 0; i < inputs; i++) {
                temp.add(Double.parseDouble(token.nextToken()));
            }
            input.add(temp.toArray(new Double[0]));
            temp.clear();
            for(int i = 0; i < outputs; i++) {
                temp.add(Double.parseDouble(token.nextToken()));
            }
            output.add(temp.toArray(new Double[0]));
            temp.clear();
        }
        s.close();
    } 
    
    public static void o(Object ... o) {
        for(Object i : o) {
            java.lang.System.out.print(i+", ");
        }
        java.lang.System.out.println();
    }
    public static void o(Object o) {
        java.lang.System.out.println(o);
    }
}
