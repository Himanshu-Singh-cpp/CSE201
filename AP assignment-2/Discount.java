// change description in modify discounts

import java.util.Scanner;

public class Discount implements Offer{
    private String category;
    private double discount;

    public String getCategory() {
        return category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Discount(String category, double discount) {
        this.category = category;
        this.discount = discount;
    }

    // print the list of discounts set by admin
    public static void print_discounts() {
        int index = 0;
        for (Discount discount : Zoo.getDiscounts()) {
            System.out.println((++index) + ". " + discount);
        }
        if (index == 0)
            System.out.println("There are currently no discounts");
        System.out.println("");
    }

    public String toString() {
        return category + (int) discount;
    }

    // allows admin to add discounts
    public static void add_discount(Scanner sc) {
        System.out.print("Enter Discount Category: ");
        String category = sc.nextLine();
        System.out.print("Enter Discount Percentage: ");
        double discount = sc.nextDouble();
        sc.nextLine();
        Zoo.getDiscounts().add(new Discount(category, discount));
        System.out.println("Discount added successfully");
        Menu.set_menu("admin");
    }

    // allows admin to modify discounts
    public static void modify_discount(Scanner sc) {
        Discount.print_discounts();
        System.out.print("Enter your choice: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getDiscounts().size()) {
            Menu.print_menu("modify_discount");
            System.out.print("Enter your choice: ");
            int action = sc.nextInt();
            sc.nextLine();
            if (action == 1) {
                System.out.print("Enter new category: ");
                String category = sc.nextLine();
                Zoo.getDiscounts().get(index - 1).setCategory(category);
                System.out.println("Category changed successfully");
            } else if (action == 2) {
                System.out.print("Enter new discount: ");
                double discount = sc.nextDouble();
                sc.nextLine();
                Zoo.getDiscounts().get(index - 1).setDiscount(discount);
                System.out.println("Discount changed successfully");
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    // allows admin to remove discounts
    public static void remove_discount(Scanner sc) {
        Discount.print_discounts();
        System.out.print("Enter the discount to remove: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getDiscounts().size()) {
            Zoo.getDiscounts().remove(index - 1);
            System.out.println("Discount removed successfully");
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

}
