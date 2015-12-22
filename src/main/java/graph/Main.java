package graph;

import java.util.Scanner;

/**
 * Created by user on 2015-12-22.
 */
public class Main {
    public static void main(String[]args){
        boolean loop = true;
        int menuSelect = 0;
        Scanner input = new Scanner(System.in);
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<String>();
        while(loop) {
            menuSelect = 0;
            System.out.println("1. add Vertex");
            System.out.println("2. add Edge");
            System.out.println("3. Calculate");
            System.out.print("What do you want to do? : ");
            menuSelect = input.nextInt();
            input.nextLine();
            if(menuSelect == 1){            //add Vertex
                System.out.print("Please Write new Vertex name : ");
                String vertex = input.nextLine();
                graph.addVertex(vertex);
                System.out.println("\n\n\n\n\n\n");
            }
            else if(menuSelect == 2){      //add Edge
                String from, to;
                int pay;
                System.out.print("Write name who needs to pay : ");
                from = input.nextLine();
                System.out.print("Write name who needs to get money : ");
                to = input.nextLine();
                System.out.print("Write amount that needs to be paid : ");
                pay = input.nextInt();
                WeightedEdge<String> edge = new WeightedEdge<String>(from, to, pay);
                graph.addEdge(edge);
                System.out.println("\n\n\n");
            }
            else if(menuSelect == 3){      //Calculate

                loop = false;
            }
            else{                          //Error
                System.out.println("\nMenu number is not found.\n\n\n\n");
            }
        }

        System.out.println("Thanks for Excute.\nThis Program is run on JDK 1.8\nDeveloper : Jaebeom Yu, Wonjun Lee\n");
    }


}
