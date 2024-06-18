import java.util.Scanner;

public class Deal implements Offer {
    private double discount;
    private int tickets;
    private String description;

    public double getDiscount() {
        return discount;
    }

    public int getTickets() {
        return tickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deal(double discount, int tickets, String description) {
        this.discount = discount;
        this.tickets = tickets;
        this.description = description;
    }

    public Deal(double discount, int tickets) {
        this.discount = discount;
        this.tickets = tickets;
        description = "Buy " + tickets + " tickets and get " + discount + "% off";
    }

    // print the list of deals
    public static int print_deals() {
        int index = 0;
        for (Deal deal : Zoo.getDeals()) {
            System.out.println(++index + ". " + deal.getDescription());
        }
        System.out.println("");
        if (index == 0)
            System.out.println("There are currently no attractions");
        return index;
    }

    // allows admin to add deal
    public static void add_deal(Scanner sc) {
        System.out.print("Enter the no. of tickets required: ");
        int tickets = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the discount applicable: ");
        double discount = sc.nextInt();
        sc.nextLine();
        Zoo.getDeals().add(new Deal(discount, tickets));
    }

    // allows admin to remove deal
    public static void remove_deal(Scanner sc) {
        if (Deal.print_deals() > 0) {
            System.out.print("Enter the deal to remove: ");
            int index = sc.nextInt();
            sc.nextLine();
            if (index > 0 && index <= Zoo.getDeals().size()) {
                Zoo.getDeals().remove(index - 1);
                System.out.println("Deal removed successfully");
            } else {
                System.out.println("Invalid input");
            }
        }
        Menu.set_menu("admin");
    }
}