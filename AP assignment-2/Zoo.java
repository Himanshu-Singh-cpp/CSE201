import java.util.*;

public class Zoo {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Attraction> attractions = new ArrayList<Attraction>();
    private static ArrayList<Animal> animals = new ArrayList<Animal>();
    private static ArrayList<Discount> discounts = new ArrayList<Discount>();
    private static Hashtable<String, Visitor> visitors = new Hashtable<String, Visitor>();
    private static Visitor person;
    private static ArrayList<Deal> deals = new ArrayList<Deal>();

    public static ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public static Hashtable<String, Visitor> getVisitors() {
        return visitors;
    }

    public static Visitor getPerson() {
        return person;
    }

    public static ArrayList<Deal> getDeals() {
        return deals;
    }

    public static void setAttractions(ArrayList<Attraction> attractions) {
        Zoo.attractions = attractions;
    }

    public static void setAnimals(ArrayList<Animal> animals) {
        Zoo.animals = animals;
    }

    public static void setDiscounts(ArrayList<Discount> discounts) {
        Zoo.discounts = discounts;
    }

    public static void setVisitors(Hashtable<String, Visitor> visitors) {
        Zoo.visitors = visitors;
    }

    public static void setPerson(Visitor person) {
        Zoo.person = person;
    }

    public static void setDeals(ArrayList<Deal> deals) {
        Zoo.deals = deals;
    }

    // calls the corresponding function as required
    public static void execute(int input) {
        if (Menu.get_menu().equals("start")) {
            if (input == 1) {
                Admin.login(sc);
            } else if (input == 2) {
                Menu.set_menu("visitor_login");
            } else if (input == 3) {
                Deal.print_deals();
            } else if (input == 4) {
                System.out.println("Thank you for visiting!");
                System.exit(0);
            } else {
                System.out.println("Invalid input");
            }
        }

        else if (Menu.get_menu().equals("admin")) {
            if (input == 1) {
                Menu.set_menu("attraction");
            } else if (input == 2) {
                Menu.set_menu("animal");
            } else if (input == 3) {
                Attraction.schedule_events(sc);
            } else if (input == 4) {
                Menu.set_menu("discount");
            } else if (input == 5) {
                Menu.set_menu("special_deals");
            } else if (input == 6) {
                Admin.print_statistics();
            } else if (input == 7) {
                Visitor.print_visitor_feedback();
            } else if (input == 8) {
                Menu.set_menu("start");
                System.out.println("Logged out");
            } else {
                System.out.println("Invalid Input");
            }
        }

        else if (Menu.get_menu().equals("attraction")) {
            if (input == 1) {
                Attraction.add_attraction(sc);
            } else if (input == 2) {
                Attraction.print_attractions();
                Menu.set_menu("admin");
            } else if (input == 3) {
                Attraction.modify_attraction(sc);
            } else if (input == 4) {
                Attraction.remove_attraction(sc);
            } else if (input == 5) {
                Menu.set_menu("admin");
            } else {
                System.out.println("Invalid input");
            }
        }

        else if (Menu.get_menu().equals("animal")) {
            if (input == 1) {
                Animal.add_animal(sc);
            } else if (input == 2) {
                Animal.update_animal(sc);
            } else if (input == 3) {
                Animal.remove_animal(sc);
            } else if (input == 4) {
                Menu.set_menu("admin");
            } else {
                System.out.println("Invalid input");
            }
        }

        else if (Menu.get_menu().equals("discount")) {

            if (input == 1) {
                Discount.add_discount(sc);
            } else if (input == 2) {
                Discount.modify_discount(sc);
            } else if (input == 3) {
                Discount.remove_discount(sc);
            } else if (input == 4) {
                Menu.set_menu("admin");
            } else {
                System.out.println("Inavlid input");
            }

        }

        else if (Menu.get_menu().equals("special_deals")) {
            if (input == 1) {
                Deal.add_deal(sc);
            } else if (input == 2) {
                Deal.remove_deal(sc);
            } else if (input == 3) {
                Menu.set_menu("admin");
            } else {
                System.out.println("Invalid input");
            }
        }

        else if (Menu.get_menu().equals("visitor_login")) {
            if (input == 1) {
                Visitor.registor_visitor(sc);
            } else if (input == 2) {
                Visitor.login_vistor(sc);
            } else {
                System.out.println("Invalid Input");
            }
        }

        else if (Menu.get_menu().equals("visitor")) {
            if (input == 1) {
                Menu.set_menu("explore_zoo");
            } else if (input == 2) {
                Visitor.buy_membership(sc);
            } else if (input == 3) {
                Visitor.buy_tickets(sc);
            } else if (input == 4) {
                Discount.print_discounts();
            } else if (input == 5) {
                Deal.print_deals();
            } else if (input == 6) {
                Animal.visit_animal(sc);
            } else if (input == 7) {
                if (person.getMembership() == 0) {
                    System.out.println("Please buy membership first");
                } else {
                    int count = Attraction.print_attractions();
                    if (count > 0) {
                        System.out.print("Enter your choice: ");
                        int index = sc.nextInt();
                        sc.nextLine();
                        if (index > 0 && index <= count) {
                            person.visit_attraction(attractions.get(index - 1));
                        } else {
                            System.out.println("Invalid attraction");
                        }
                    }
                }
            } else if (input == 8) {
                System.out.print("Enter your feedback (max 200 characters): ");
                person.setFeedback(sc.nextLine());
                System.out.println("Thank you for your feedback");
            } else if (input == 9) {
                Menu.set_menu("start");
            }
        }

        else if (Menu.get_menu().equals("explore_zoo")) {
            if (input == 1) {
                Visitor.explore_attractions(sc);
            } else if (input == 2) {
                Animal.print_animals();
            } else if (input == 3) {
                Menu.set_menu("visitor");
            } else {
                System.out.println("Invalid Input");
            }
        }

        else {
            System.out.println("An error occurred");
        }

    }

    public static void main(String[] args) {
        discounts.add(new Discount("NONE", 0));
        discounts.add(new Discount("MINOR", 10));
        discounts.add(new Discount("SENIOR", 20));

        deals.add(new Deal(10, 2));
        deals.add(new Deal(20, 3));

        animals.add(new Mammal("Lion", "mammal", "roar"));
        animals.add(new Mammal("Tiger", "mammal", "roarr"));
        animals.add(new Amphibian("frog", "amphibian", "croack"));
        animals.add(new Amphibian("salamander", "amphibian", "crack"));
        animals.add(new Reptile("turtle", "reptile", "crackeles"));
        animals.add(new Reptile("crocodile", "reptile", "grunt"));

        attractions.add(new Attraction("Jungle Safari", "Wild number of animals", 10));
        attractions.add(new Attraction("Botanical Garden", "Huge Variety of plants", 15));
        attractions.add(new Attraction("Dinosour Show", "Coming from the jurrasic time", 12));

        System.out.println("Welcome to ZOOtopia!");
        while (true) {
            Menu.print_menu();
            System.out.print("Enter your choice: ");
            int input = sc.nextInt();
            sc.nextLine();
            execute(input);
        }
    }
}
