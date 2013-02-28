package neural.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Main {

    public static final Random r = new Random();
    public ArrayList<Double[]> 
            input = new ArrayList<Double[]>(),
            output = new ArrayList<Double[]>();
    public int inputs, outputs;
    Run configurations;
    
    public Main(Run r) {
        configurations = r;
    }
    
    public void run() throws FileNotFoundException {
        loadFile(configurations.getTrain());
        System bob;
        bob = new System(inputs, 40, outputs);
        bob.setConf(configurations);
        
        long seed = r.nextLong();
        Double [] compare;
        // Below variables used to find max value of the output.
        int expectedValue;
        int nfound;
        for(int j = 0; j < 100; j++) {
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
            o(nfound/(double)input.size());
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
    
    private void loadFile (String file) throws FileNotFoundException {
        Scanner s = new Scanner(new File(file));
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
