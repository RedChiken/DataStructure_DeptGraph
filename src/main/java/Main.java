import graph.DirectedWeightedGraph;
import graph.WeightedEdge;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean loop = true;
        int menuSelect;
        Scanner input = new Scanner(System.in);
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        while (loop) {
            System.out.println("1. add Vertex");
            System.out.println("2. add Edge");
            System.out.println("3. Calculate");
            System.out.print("What do you want to do? : ");

            try{
                menuSelect = input.nextInt();
                input.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("You need to write number\n\n\n");
                input.nextLine();
                continue;
            }


            if (menuSelect == 1) {            //add Vertex
                System.out.print("Please Write new Vertex name : ");
                String vertex = input.nextLine();
                graph.addVertex(vertex);
                System.out.println("\n\n\n\n\n\n");
            } else if (menuSelect == 2) {      //add Edge
                String from, to;
                int pay;
                System.out.print("Write name who needs to pay : ");
                from = input.nextLine();
                System.out.print("Write name who needs to get money : ");
                to = input.nextLine();
                try {
                    System.out.print("Write amount that needs to be paid : ");
                    pay = input.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("You need to write a number.\n\n\n");
                    continue;
                }
                WeightedEdge<String> edge = new WeightedEdge<>(from, to, pay);
                graph.addEdge(edge);
                System.out.println("\n\n\n");
            } else if (menuSelect == 3) {      //Calculate
                dept.Optimizer opt = new dept.Optimizer(graph);
                opt.simplify();
                opt.fragmentize();
                opt.regenerate();
                loop = false;
            } else {                          //Error
                System.out.println("\nMenu number is not found.\n\n\n\n");
            }
        }

        System.out.println("Thanks for Excute.\nThis Program is run on JDK 1.8\nDeveloper : Jaebeom Yu, Wonjun Lee\n");
    }


}
