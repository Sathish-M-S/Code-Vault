package CodePackage;

import java.util.Scanner;

public class Control 
{
    private String name="",password="";
    static Scanner scan= new Scanner(System.in);
    static Database db=new Database();
    static Control s=new Control();


    public static String getName()
    {
        
        return s.name;
    }
    public static void setName(String st)
    {
        s.name=st;
    }
    public static String getPassword()
    {
        return s.password;
    }
    public static void setPassword(String st)
    {
        s.password=st;
    }


    public static void login()
    {
        int select2=0;
        while(select2==0)
        {
            System.out.println("Enter your Name : ");
            setName(scan.next());
            // String name1=scan.next();
            System.out.println("Enter your Password: ");
            // String pass1=scan.next();
            setPassword(scan.next());
            if(db.check(getName(),getPassword()))
            {
                int select3=0;
                while(select3==0)
                {
                    System.out.println("Enter 1 for index page, 2 for Add a Snippet,3 for delete a snippet ,0 for Exit");
                    int load=scan.nextInt();
                    if(load==1)
                    {
                        db.index(getName());                        
                    }
                    else if(load==2)
                    {
                        db.index(getName());
                        System.out.println("Enter Title of your Snippet");
                        String t=scan.next();
                        System.out.println("Enter your Snippet");
                        scan.nextLine();
                        String snip=scan.nextLine();
                        System.out.println(db.addSnip(t,snip,getName()));

                    }
                    else if(load==3)
                    {
                        db.index(getName());
                        System.out.println("Enter Snippet Id to delete Snippet");
                        int id=scan.nextInt();
                        db.delete(getName(),id);
                    }
                }
                select2=1;
            }
            else 
            {
                System.out.println("Enter a valid userName and password");
            }
        }
    }

    public static void register()
    {
        int select4=0;
        while(select4==0)
        {
            System.out.println("Enter your UserName :");
            setName(scan.next());
            if(db.unique(getName()))
            {
                System.out.println("Enter your password: ");
                setPassword(scan.next());
                System.out.println(db.adduser(getName(), getPassword()));
                select4=1;
            }
            else
            {
                System.out.println("UserName taken Choose Another");
            }
            login();
        }
    }

}
