package feature.map.utilities;

/**
 *  The Utilities class
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class U {
    
    /**
     * Outputs on standard out.
     * @param o 
     */
    public static void o (Object o) {
        System.out.println(o);
    }
    
    /**
     * Outputs array on standard out
     * @param o 
     */
    public static void o (Object [] a) {
        System.out.print("[ ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+", ");
        }
        System.out.println("]");
    }
    
    /**
     * Outputs on standard out.
     * @param o 
     */
    public static void e (Object o) {
        System.err.println(o);
    }
    
    /**
     * Outputs array on standard out
     * @param o 
     */
    public static void e (Object [] a) {
        System.err.print("[ ");
        for (int i = 0; i < a.length; i++) {
            System.err.print(a[i]+", ");
        }
        System.err.println("]");
    }
    
    /**
     * Limits the value to between 0 and 255
     * @param d
     *      the value to limit.
     * @return 
     */
    public static int l (double d) {
        int D = (int) (d * 255);
        
        D = (D < 0)? 0 : D;
        D = (D > 255)? 255 : D;
        
        return (int)D;
    }
    
    /**
     * Limits the value to between 0 and 1
     * @param d
     *      the value to limit.
     * @return 
     */
    public static double l1 (double d) {
        double D = d / 255.0;
        
        D = (D < 0)? 0 : D;
        D = (D > 255)? 255 : D;
        
        return D;
    }
}
