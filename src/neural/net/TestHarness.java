package neural.net;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Evan
 */
public class TestHarness {
    //<editor-fold defaultstate="collapsed" desc="Generate all config files">
    public static void generate () {
        Run r;
        long id = 0;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw;
        Main.o("Generating Config Files...");
        for(int variant = 0; variant < 2; variant++) {
            for(int activation = 0; activation < 2; activation++){
                for(double momentum = 0; momentum <= 1; momentum+=0.1) {
                    for(double learnRate = 0.1; learnRate <= 1; learnRate+=0.1) {
                        for(int shape = 10; shape < 100; shape+= 10) {
                            for(int generations = 200; generations <= 200; generations+=10) {
                                r = new Run();
                                switch(variant) {
                                    case 0: r.setVariant("Vanilla"); break;
                                    case 1: continue;
                                        //r.setVariant("QProp"); break;
                                }
                                switch(activation){
                                    case 0: r.setActivation("Sigmoid");
                                    r.setTrain("sigmoid-train.txt");
                                    r.setTest ("sigmoid-test.txt"); break;
                                    case 1: r.setActivation("Tanh");
                                    r.setTrain("tanh-train.txt");
                                    r.setTest ("tanh-test.txt"); break;
                                }
                                r.setId(id);
                                r.setMomentum(momentum);
                                r.setLearningRate(learnRate);
                                r.setGenerations(generations);
                                r.setShape(Arrays.asList(new Integer[]{64,shape,10}));
                                try {
                                    fw = new FileWriter("configs/"+(id++)+".json");
                                    fw.write(gson.toJson(r).replaceAll("\n", "\r\n"));
                                    fw.close();
                                } catch(Exception e) {}
                                
                                if(id%5 == 0) {
                                   java.lang.System.out.print(".");
                                   if(id%100==0){
                                       java.lang.System.out.println();
                                   }
                                }
                            }
                        }
                    }
                }
            }
        }
        java.lang.System.out.println("\n"+id);
    }
    //</editor-fold>

    public static void main (String[] args) throws FileNotFoundException, IOException, InterruptedException {
        generate();
        int c = 0;
        int end = 1000000;
        if (args.length == 2) {
            c = Integer.parseInt(args[0]); 
            end = Integer.parseInt(args[1]);
        }        
        File f;
        Gson gson = new Gson();
        Run temp;
        ArrayList<Thread> active = new ArrayList<Thread>(10);
        Thread cThread;
        while(c <= end){
            f = new File("configs/"+(c)+".json");
            if(!f.exists()){ break; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            temp = gson.fromJson(br, Run.class);
            br.close();
            while(true) {
                if (active.size() < 10) {
                    Main.o(temp);
                    cThread = new Main(temp);
                    active.add(cThread);
                    cThread.start();
                    break;
                }
                for(Thread t : active){
                    if(!t.isAlive()) {
                        Main.o("Completed "+t.getName());
                        active.remove(t);
                    }
                }
                Thread.sleep(1000);
            }
            c++;
        }
        for(Thread t : active) {
            t.join();
        }
        
    }
}
