import java.util.Scanner;

abstract public class Animal {
    private String name;
    private String type; // could do integer type for easier access
    private String sound;
    private String history;

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Animal(String name, String type, String sound) {
        this.name = name;
        this.type = type;
        this.sound = sound;
        history = "this animal was rescued from the jungle.";
    }

    // prints the list of animals
    public static void print_animals() {
        int index = 0;
        System.out.println("");
        for (Animal animal : Zoo.getAnimals()) {
            System.out.println((++index) + ". " + animal.getName());
        }
        if (index == 0)
            System.out.println("There are currently no animals");
        System.out.println("");
    }

    // print the sound made by animal when you feed it
    public void feed() {
        System.out.println(name + " is making sound " + sound);
    }

    // print the history of animal
    public void read() {
        System.out.println("The history of " + name + " is " + history);
    }

    // allow admin to add animal to the zoo
    public static void add_animal(Scanner sc) {
        System.out.print("Enter Animal Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Animal Species: ");
        String type = sc.nextLine();
        if (type.equals("mammal") || type.equals("reptile") || type.equals("amphibian")) {
            System.out.print("Enter Animal Sound: ");
            String sound = sc.nextLine();
            // error checking to be done if the type of animal exits
            if(type.equals("mammal")){
                Zoo.getAnimals().add(new Mammal(name, type, sound));
            }
            else if(type.equals("reptile")){
                Zoo.getAnimals().add(new Reptile(name, type, sound));
            }
            else if(type.equals("amphibian")){
                Zoo.getAnimals().add(new Amphibian(name, type, sound));
            }
            System.out.println("Animal added to Zoo");
        } else {
            System.out.println("Invalid type entered");
        }
        Menu.set_menu("admin");
    }

    // allow admin to update animal details
    public static void update_animal(Scanner sc) {
        Animal.print_animals();
        System.out.print("Enter the animal to update details: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getAnimals().size()) {
            Menu.print_menu("modify_animal");
            System.out.print("Enter your choice: ");
            int action = sc.nextInt();
            sc.nextLine();
            if (action == 1) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                Zoo.getAnimals().get(index - 1).setName(name);
                System.out.println("Name changed successfully");
            } else if (action == 2) {
                System.out.print("Enter new sound: ");
                String sound = sc.nextLine();
                Zoo.getAnimals().get(index - 1).setSound(sound);
                System.out.println("Sound changed successfully");
            } else if (action == 3) {
                System.out.print("Enter new history: ");
                String history = sc.nextLine();
                Zoo.getAnimals().get(index - 1).setHistory(history);
                System.out.println("History changed successfully");
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

    // allow admin to remove animal from the zoo
    public static void remove_animal(Scanner sc) {
        Animal.print_animals();
        System.out.print("Enter the animal to remove: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index > 0 && index <= Zoo.getAnimals().size()) {
            Zoo.getAnimals().remove(index - 1);
            System.out.println("Animal removed from Zoo");
        } else {
            System.out.println("Invalid input");
        }
        Menu.set_menu("admin");
    }

    // allow visitor to visit animal
    public static void visit_animal(Scanner sc) {
        Visitor person=Zoo.getPerson();
        if (person.getMembership() == 0) {
            System.out.println("Please buy membership first");
        } else {
            Animal.print_animals();
            System.out.print("Enter your choice: ");
            int index = sc.nextInt();
            sc.nextLine();
            if (index > 0 && index <= Zoo.getAnimals().size()) {
                Menu.print_menu("visit_animal");
                System.out.print("Enter your choice: ");
                int action = sc.nextInt();
                sc.nextLine();
                if (action == 1) {
                    Zoo.getAnimals().get(index - 1).feed();
                } else if (action == 2) {
                    Zoo.getAnimals().get(index - 1).read();
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    abstract public String toString();

}
