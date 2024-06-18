import java.util.*;

public class Visitor {
    private String name;
    private int age;
    private String phone;
    private int balance;
    private String email;
    private String password;
    private int membership;
    // 0- not bought
    // 1-basic 20
    // 2-premium 50
    private String feedback;

    private ArrayList<Attraction> attractions = new ArrayList<Attraction>();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public int getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getMembership() {
        return membership;
    }
    
    public String getFeedback() {
        return feedback;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setAttractions(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Visitor(String name, int age, String phone, int balance, String email, String password) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.balance = balance;
        this.email = email;
        this.password = password;
        membership = 0;
    }

    // helper function
    private void buy_membership(int membership, Discount discount) {

        double basic_cost = 20;
        double premium_cost = 50;
        if (discount.getCategory().equals("MINOR")) {
            if (age > 18) {
                System.out.println("You are not eligible for this");
                return;
            }
        }
        if (discount.getCategory().equals("SENIOR")) {
            if (age < 60) {
                System.out.println("You are not eligible for this");
                return;
            }
        }
        basic_cost *= (1 - discount.getDiscount() / 100);
        premium_cost *= (1 - discount.getDiscount() / 100);
        boolean bought = false;
        if (membership == 1) {
            if (balance >= basic_cost) {
                balance -= basic_cost;
                Admin.updateTotal_revenue(basic_cost);
                bought = true;
            }
        } else if (membership == 2) {
            if (balance >= premium_cost) {
                balance -= premium_cost;
                Admin.updateTotal_revenue(premium_cost);
                bought = true;
            }
        } else {
            System.out.println("Invalid input for membership");
            return;
        }
        if (bought) {
            this.membership = membership;
            System.out.println("Membership bought successfully. Your balance is now " + balance);
        } else {
            System.out.println("Insufficient balance");
        }

    }

    // ticket bought by visitor to attraction
    public void buy_attraction(Attraction attraction, int num_ticket, Discount discount) {
        double deal = 0;
        for (Deal d : Zoo.getDeals()) {
            if (d.getTickets() == num_ticket) {
                deal = d.getDiscount();
                break;
            }
        }
        if (discount.getCategory().equals("MINOR")) {
            if (age > 18) {
                System.out.println("You are not eligible for this");
                return;
            }
        }
        if (discount.getCategory().equals("SENIOR")) {
            if (age < 60) {
                System.out.println("You are not eligible for this");
                return;
            }
        }
        deal = deal + discount.getDiscount() - deal * discount.getDiscount() / 100;
        if (balance >= (attraction.getPrice(deal) * num_ticket)) {
            for (int i = 0; i < num_ticket; i++)
                attractions.add(attraction);
            balance -= (attraction.getPrice(deal) * num_ticket);
            Admin.updateTotal_revenue(attraction.getPrice(deal) * num_ticket);
            System.out
                    .println("The ticket for " + attraction.getName() + " was purchased successfully. Your balance is now " + balance);
        } else {
            System.out.println("Not enough balance to buy ticket");
        }
    }

    // visitor visits attraction
    public void visit_attraction(Attraction attraction) {
        if (!attraction.isOpen()) {
            System.out.println("Attaction is currently closed");
            return;
        }
        if (membership == 2 || attractions.contains(attraction)) {
            if (membership == 1) {
                attractions.remove(attraction);
                attraction.increase_visitors();
                System.out.println("1 Ticket Used.");
            }
            System.out.println("Thanks for visting " + attraction.getName() + ".Hope you enjoyed the attraction.");
        } else {
            System.out.println("Ticket not available. Basic Members need to buy separate tickets for the attractions.");
        }
    }

    // when a new registor wants to registor
    public static void registor_visitor(Scanner sc) {
        System.out.print("Enter Visitor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Visitor Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Visitor Phone Number: ");
        String phone = sc.nextLine();
        System.out.print("Enter Visitor Balance: ");
        int balance = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Visitor Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Visitor Password: ");
        String password = sc.nextLine();
        Zoo.getVisitors().put(email, new Visitor(name, age, phone, balance, email, password));
        Menu.set_menu("visitor_login");
        System.out.println("\nRegistration is Successful.");
    }

    // visitor login
    public static void login_vistor(Scanner sc) {
        System.out.print("Enter Visitor Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Visitor Password: ");
        String password = sc.nextLine();
        if (Zoo.getVisitors().containsKey(email) && Zoo.getVisitors().get(email).getPassword().equals(password)) {
            Menu.set_menu("visitor");
            Zoo.setPerson(Zoo.getVisitors().get(email));
            System.out.println("\nLogin Successful.");
        } else {
            System.out.println("Invalid Username or password");
        }
    }

    // visitor feedback seen by admin
    public static void print_visitor_feedback() {
        int count = 0;
        for (Visitor v : Zoo.getVisitors().values()) {
            if (v.getFeedback() != null) {
                System.out.println(++count + ". " + v.getFeedback());
            }
        }
        if (count == 0)
            System.out.println("No feedback yet.");
    }

    // explore attractions by vistors
    public static void explore_attractions(Scanner sc) {
        Attraction.print_attractions();
        System.out.print("Enter your choice: ");
        int attraction_index = sc.nextInt();
        sc.nextLine();
        System.out.println("Attraction Details:");
        System.out.println(Zoo.getAttractions().get(attraction_index - 1).getDescription());
    }

    // allow visitor to buy membership
    public static void buy_membership(Scanner sc) {
        Menu.print_menu("membership");
        System.out.print("Enter Your Choice: ");
        int membership = sc.nextInt();
        sc.nextLine();
        Discount.print_discounts();
        System.out.print("Enter Your Choice: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getDiscounts().size()) {
            Zoo.getPerson().buy_membership(membership, Zoo.getDiscounts().get(index - 1));
        } else {
            System.out.println("Invalid input");
        }
    }

    // allow visitor to buy tickets to the attractions
    public static void buy_tickets(Scanner sc) {
        Visitor person = Zoo.getPerson();

        if (person.getMembership() == 0) {
            System.out.println("Please buy membership first");
        } else if (person.getMembership() == 2) {
            System.out.println("You have premium membership no need to buy tickets");
        } else {
            System.out.println("Buy Tickets:");
            System.out.println("Select an Attraction to Buy a Ticket");
            int count = Attraction.print_attractions();
            if (count > 0) {
                System.out.print("Enter your choice: ");
                int index = sc.nextInt();
                sc.nextLine();
                if (index > 0 && index <= count) {
                    System.out.print("Enter the number of tickets: ");
                    int num_ticket = sc.nextInt();
                    sc.nextLine();
                    Discount.print_discounts();
                    System.out.print("Enter your choice: ");
                    int dis_index = sc.nextInt();
                    sc.nextLine();
                    if (index > 0 && index <= Zoo.getDiscounts().size()) {
                        person.buy_attraction(Zoo.getAttractions().get(index - 1), num_ticket,
                                Zoo.getDiscounts().get(dis_index - 1));
                    } else {
                        System.out.println("Invalid option");
                    }
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
