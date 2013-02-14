package neural.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Main {

    public static Random r;
    public static ArrayList<Double[]> 
            input = new ArrayList<Double[]>(),
            output = new ArrayList<Double[]>();
    static int inputs, outputs;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length>0) {
            r = new Random(Long.parseLong(args[0]));
        } else {
            r = new Random();
        }
        loadFile("data.txt");
        System bob;
        bob = new System(inputs, 3, 5, 7, outputs);
        
        double average;
        for(int j = 0; j < 30; j++) {
            average = 0;
            for(int i = 0; i < input.size(); i++) {
                average += output.get(i)[0] - bob.run(input.get(i))[0];
                bob.train(output.get(i));
            }
            o(average/(double)input.size());
        }
    }
    
    private static void loadFile (String file) throws FileNotFoundException {
        Scanner s = new Scanner(new File(file));
        inputs = Integer.parseInt(s.next());
        outputs = Integer.parseInt(s.next());
        StringTokenizer token;
        ArrayList<Double> temp = new ArrayList<Double>();
        
        while (s.hasNextLine()) {
            token = new StringTokenizer(s.nextLine(), "\t\r\n,");
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
