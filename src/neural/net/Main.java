package neural.net;

import java.util.Random;

/**
 * @author Evan Verworn
 */
public class Main {

    public static Random r;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length>0)
            r = new Random(Long.parseLong(args[0]));
        else
            r = new Random();
        
        System bob = new System(2,3,1);
        for (int i = 0; i < 50; i++) {
            o(bob.run(0.0, 0.0));
            bob.train(0);
            o(bob.run(1.0, 0.0));
            bob.train(1);
            o(bob.run(0.0, 1.0));
            bob.train(1);
            o(bob.run(1.0, 1.0));
            bob.train(1);
        }
        
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
