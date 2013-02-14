package neural.net;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Prep {
    
    
    
    public static void main (String [] args) throws Exception {
        new File("digitdata.txt").delete();
        
        Scanner f;
        BufferedWriter fnew = new BufferedWriter(new FileWriter(new File("digitdata.txt")));
        fnew.write("64 10");
        fnew.newLine();
        
        
        for (int i = 0; i < 10; i++) {
            f = new Scanner(new File("data/digit_train_"+i+".txt"));
            
            while(f.hasNextLine()) {
                fnew.write(f.nextLine().trim()+",");
                for (int j = 0; j < 10; j++) {
                    fnew.write(((j==i)?"1":"-1") + ((j!=9)?",":"") );
                }
                fnew.newLine();
            }
            
            f.close();
        }
        
        fnew.close();
    }
}
