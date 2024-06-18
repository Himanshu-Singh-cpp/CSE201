import java.util.Scanner;

public class Admin {
    private static String username = "admin";
    private static String password = "admin123";
    private static double total_revenue = 0;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static double getTotal_revenue() {
        return total_revenue;
    }

    public static void setUsername(String username) {
        Admin.username = username;
    }

    public static void setPassword(String password) {
        Admin.password = password;
    }

    public static void setTotal_revenue(double total_revenue) {
        Admin.total_revenue = total_revenue;
    }

    public static void setTotal_revenue(int total_revenue) {
        Admin.total_revenue = total_revenue;
    }

    public static void updateTotal_revenue(double money) {
        total_revenue += money;
    }

    public static void updateTotal_revenue(int money) {
        total_revenue += money;
    }

    // login for admin
    public static void login(Scanner sc) {
        System.out.print("Enter Admin username: ");
        String username = sc.next();
        sc.nextLine();
        System.out.print("Enter Admin password: ");
        String password = sc.next();
        sc.nextLine();
        if (Admin.username.equals(username) && Admin.password.equals(password)) {
            Menu.set_menu("admin");
            System.out.println("\nLogged in as Admin.");
        } else {
            System.out.println("Invalid Username or password");
        }

    }

    // print statics related to the zoo
    public static void print_statistics() {
        System.out.println("Visitor Statistics: ");
        System.out.println("- Total Visitors: " + Zoo.getVisitors().size());
        System.out.println("- Total Revenue: Rs." + total_revenue);
        // most popular attraction
        int max = 0;
        int index = 0;
        for (Attraction att : Zoo.getAttractions()) {
            if (att.getNum_visitors() > max) {
                max = att.getNum_visitors();
                index = Zoo.getAttractions().indexOf(att);
            }
        }
        System.out.println("- Most Popular Attraction: " + Zoo.getAttractions().get(index));
    }

    // exit the program
    public static void exit() {
        System.out.println("Logged out.");
        System.exit(0);
    }

}
