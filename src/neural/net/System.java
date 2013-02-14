package neural.net;

import java.util.ArrayList;

/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class System {
    
    
    /**
     * The assembler works like so, if a 3-6-3 neural net is wanted. Then you would
     *  pass in `new System(3,6,3);` into the constructor
     * @param amounts - Integer array of amounts in each grouping. Assumes the
     *  first are input nodes and the last are output nodes.
     */    
    public System(int ... amounts) {
        if (amounts.length < 2) {
            throw new Error("Unable to make a neural net with less than 2 layers.");
        }
        system = new Node[amounts.length][];
        input = new ArrayList<Node>();
        output = new ArrayList<Node>();
        
        for(int i = 0 ; i < amounts.length; i++) {
            system[i] = new Node[amounts[i]];
            for(int j = 0; j < amounts[i]; j++) {
                system[i][j] = new Node();
                if (i==0){
                    system[i][j].NODETYPE = Node.type.INPUT;
                    input.add(system[i][j]);
                } else {
                    for (int k = 0; k < system[i-1].length; k++) {
                        system[i][j].addLink(system[i-1][k]);
                    }                    
                    if (i==amounts.length-1) {
                        system[i][j].NODETYPE = Node.type.OUTPUT;
                        output.add(system[i][j]);
                    } else
                        system[i][j].NODETYPE = Node.type.HIDDEN;        
                }
            }
        }
        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system[i].length; j++) {
                system[i][j].randomize();
                if(i>0) {
                    for(int k = 0; k < system[i-1].length; k++) {
                        system[i-1][k].addOutputLink(system[i][j]);
                    }
                }
            }
        }
    }
        
    public Node [][] system;
    public ArrayList<Node> input, output;
    
    public Double[] run(Double ... input) {
        if(input.length!=system[0].length) {
            throw new Error("Number of inputs does not match number of nodes");
        }
        
        Double[] response = new Double[system[system.length-1].length];
        
        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system[i].length; j++) {
                system[i][j].clear();
            }
        }
        for (int i = 0; i < system[0].length; i++) {
            system[0][i].setInputNode(input[i]);
        }
        for (int i = 0; i < response.length; i++) {
            response[i] = output.get(i).output();
        }
        
        return response;
    }
    
    /**
     * This function assumes that 'run' has been run for the system already.
     * @param expected 
     */
    public void train(Double ... expected){
        for (int i = 0; i < output.size(); i++) {
            output.get(i).setError(expected[i] - output.get(i).output());
        }
        for (int i = system.length-1; i >= 0; i--) {
            for (int j = system[i].length-1; j >= 0; j--) {
                system[i][j].backprop();
            }
        }
        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system[i].length; j++) {
                system[i][j].forwardprop();
            }
        }
    }    
}
