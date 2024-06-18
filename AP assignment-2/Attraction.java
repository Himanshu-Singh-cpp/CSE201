import java.util.Scanner;

public class Attraction {
    private String name;
    private String description;
    private int price;
    private boolean open;
    private int num_visitors;
    private static int num_attraction=3;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getPrice(double discount) {
        return (int) (price * (1 - discount / 100));
    }

    public boolean isOpen() {
        return open;
    }

    public int getNum_visitors() {
        return num_visitors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setNum_visitors(int num_visitors) {
        this.num_visitors = num_visitors;
    }

    public Attraction(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        open = true;
        num_visitors=0;
    }

    public String toString() {
        if (open)
            return name + " (Rs." + price + ") is open";
        else
            return name + " (Rs." + price + ") is closed";
    }

    // increase the number of visitor that have visited the attraction
    public void increase_visitors(){
        num_visitors++;
    }

    // print the list of attractions in the zoo
    public static int print_attractions() {
        int index = 0;
        for (Attraction att : Zoo.getAttractions()) {
            System.out.println((++index) + ". " + att);
        }
        System.out.println("");
        if (index == 0)
            System.out.println("There are currently no attractions");
        return index;
    }

    // allow admin to modify attraction
    public static void modify_attraction(Scanner sc) {
        Attraction.print_attractions();
        System.out.print("Enter the attraction to modify: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getAttractions().size()) {
            Menu.print_menu("modify_attraction");
            System.out.print("Enter your choice: ");
            int action = sc.nextInt();
            sc.nextLine();
            if (action == 1) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                Zoo.getAttractions().get(index - 1).setName(name);
                System.out.println("Name changed successfully");
            } else if (action == 2) {
                System.out.print("Enter new description: ");
                String description = sc.nextLine();
                Zoo.getAttractions().get(index - 1).setDescription(description);
            } else if (action == 3) {
                System.out.print("Enter new price: ");
                int price = sc.nextInt();
                sc.nextLine();
                Zoo.getAttractions().get(index - 1).setPrice(price);
            } else if (action == 4) {
                System.out.print("Enter new open status(True/False): ");
                boolean open = sc.nextBoolean();
                sc.nextLine();
                Zoo.getAttractions().get(index - 1).setOpen(open);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

    // allow admin to add attraction
    public static void add_attraction(Scanner sc) {
        System.out.print("Enter Attraction Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Attraction Description: ");
        String description = sc.nextLine();
        System.out.print("Enter Attraction Price: ");
        int price = sc.nextInt();
        sc.nextLine();
        Zoo.getAttractions().add(new Attraction(name, description, price));
        System.out.println("Attraction added successfully with ID " +(++num_attraction));
        Menu.set_menu("admin");
    }

    // allow admin to remove attraction
    public static void remove_attraction(Scanner sc) {
        Attraction.print_attractions();
        System.out.print("Enter the attraction to remove: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getAttractions().size()) {
            Zoo.getAttractions().remove(index - 1);
            System.out.println("Attraction removed successfully");
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

    // allow admin to schedule events
    public static void schedule_events(Scanner sc) {
        Attraction.print_attractions();
        System.out.print("Enter the attraction to modify: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getAttractions().size()) {
            Menu.print_menu("events");
            System.out.print("Enter your choice: ");
            int action = sc.nextInt();
            sc.nextLine();
            if (action == 1) {
                System.out.print("Enter new price: ");
                int price = sc.nextInt();
                sc.nextLine();
                Zoo.getAttractions().get(index - 1).setPrice(price);
            } else if (action == 2) {
                System.out.print("Enter new open status(True/False): ");
                boolean open = sc.nextBoolean();
                sc.nextLine();
                Zoo.getAttractions().get(index - 1).setOpen(open);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

}
