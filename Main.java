package CodePackage;

import java.util.*;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String sd[]) {
        System.out.println("           WELCOME TO CODE SNIPPET           ");
        System.out.println("---------------------------------------------");
        System.out.println();
        int select1 = 1;
        while (select1 == 1) 
        {
            System.out.println("Enter 1 for Login, 2 for Register, 0 for Exit");
            select1 = scan.nextInt();
            if (select1 == 1)
                Control.login();
            else if (select1 == 2)
            {
                Control.register();
                select1=1;
            }
        }
    }
}