public class Menu {
    private static String menu = "start";
    // start // admin // attraction // animal // discount
    // visitor_login // visitor

    public Menu() {
        menu = "start";
    }

    public static void set_menu(String menu_name) {
        menu = menu_name;
    }

    public static String get_menu() {
        return menu;
    }

    // prints the corresponding menu
    public static void print_menu() {
        System.out.println("");
        if (Menu.get_menu().equals("start")) {
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("4. Exit");

        } else if (Menu.get_menu().equals("admin")) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");

        } else if (Menu.get_menu().equals("attraction")) {
            System.out.println("Manage Attractions:");
            System.out.println("1. Add Attraction");
            System.out.println("2. View Attractions");
            System.out.println("3. Modify Attraction");
            System.out.println("4. Remove Attraction");
            System.out.println("5. Exit");

        } else if (Menu.get_menu().equals("animal")) {
            System.out.println("Manage Animals:");
            System.out.println("1. Add Animal");
            System.out.println("2. Update Animal Details");
            System.out.println("3. Remove Animal");
            System.out.println("4. Exit");

        } else if (Menu.get_menu().equals("discount")) {
            System.out.println("Set Discounts:");
            System.out.println("1. Add Discount");
            System.out.println("2. Modify Discount");
            System.out.println("3. Remove Discount");
            System.out.println("4. Exit");

        } else if (Menu.get_menu().equals("special_deals")) {
            System.out.println("Manage Special Deals");
            System.out.println("1. Add Special Deal");
            System.out.println("2. Remove Special Deal");
            System.out.println("3. Exit");

        } else if (Menu.get_menu().equals("visitor_login")) {
            System.out.println("1. Registor");
            System.out.println("2. Login");

        } else if (Menu.get_menu().equals("visitor")) {
            System.out.println("Visitor Menu:");
            System.out.println("1. Explore the Zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View Special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Log Out");
        } else if (Menu.get_menu().equals("explore_zoo")) {
            System.out.println("Explore the Zoo:");
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Exit");

        }
        System.out.println("");
    }

    // print the temporary menu according to the current menu
    public static void print_menu(String temp) {
        System.out.println("");
        if (temp.equals("modify_attraction")) {
            System.out.println("Modify Attraction:");
            System.out.println("1. Modify Name");
            System.out.println("2. Modify Description");
            System.out.println("3. Modify Price");
            System.out.println("4. Modify Opening status");
            System.out.println("5. Exit");
        } else if (temp.equals("modify_animal")) {
            System.out.println("Update Animal details:");
            System.out.println("1. Update animal name");
            System.out.println("2. Update animal sound");
            System.out.println("3. Update animal history");
        } else if (temp.equals("membership")) {
            System.out.println("Buy Membership:");
            System.out.println("1. Basic Membership");
            System.out.println("2. Premium Membership");
        } else if (temp.equals("events")) {
            System.out.println("Schedule Events:");
            System.out.println("1. Change price");
            System.out.println("2. Change open status");
        } else if (temp.equals("modify_discount")) {
            System.out.println("Modify Discount:");
            System.out.println("1. Modify Category");
            System.out.println("2. Modify Percentage");

        } else if (temp.equals("visit_animal")) {
            System.out.println("1. Fead the animal");
            System.out.println("2. Read about the animal");
        }
        System.out.println("");
    }
}