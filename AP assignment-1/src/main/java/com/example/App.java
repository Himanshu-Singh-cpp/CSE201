package com.example;


import java.util.Scanner;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;



public class App {
    private static Hashtable<String,Member> mem_dict=new Hashtable<String,Member>();
    private static Hashtable<Integer,Book> book_dict=new Hashtable<Integer,Book>();
    private static Scanner sc = new Scanner(System.in);
    private static Member member;

    static class Menu{
        private static int page; // 0 - main page, 1 - librarian, 2 - member
        public Menu(){
            System.out.println("Welcome to the Library Portal");
            page=0;
        }

        public static int get_page(){
            return page;
        }

        public static void set_page(int page_num){
            page=page_num;
        }

    }

    static class Librarian{
        private static int book_ID=1;
        private static int num_members=1;
        public static void register_member(String name,int age,String phone_number){
            Member new_member=new Member(name,age,phone_number);
            mem_dict.put(phone_number,new_member);
            System.out.println("Member registered successfully!");
            System.out.println("Member ID: "+num_members++);
        }

        public static void remove_member(String phone_number){
            mem_dict.remove(phone_number);
            System.out.println("Member removed successfully!");
        }

        public static void add_book(String book_title,String author,int copies){
            for(int i=0;i<copies;i++){
                Book new_book=new Book(book_title,author,book_ID);
                book_dict.put(book_ID++,new_book);
            }
            System.out.println("Book added successfully!");
        }
        public static void remove_book(int book_ID){
            if(book_dict.containsKey(book_ID) && book_dict.get(book_ID).available){
                book_dict.remove(book_ID);
                System.out.println("Book removed successfully!");
            }
            else System.out.println("Book not found!");
        }
        public static void view_all_members(){
            if (mem_dict.size()==0) System.out.println("No members registered!");                                 
            else{
                for(Map.Entry<String,Member> entry:mem_dict.entrySet()) {
                    int current_fine=entry.getValue().fine;
                    for(Book book:entry.getValue().hash_set){
                        current_fine+=book.current_fine();
                    }
                    System.out.println("\nMember ID: "+entry.getKey()+"\nName: "+entry.getValue().name+"\nAge: "+entry.getValue().age+"\nPhone number: "+entry.getValue().phone_number+"\nFine: "+current_fine);
                }
            }
        
        
        }
        public static void view_all_books(){
            if(book_dict.size()==0){
                System.out.println("No books in the library");
                return;
            }
            System.out.println("Books in libarary are: ");
            for(Map.Entry<Integer,Book> entry:book_dict.entrySet()){
                System.out.println("\nBook ID: "+entry.getKey()+"\nBook title: "+entry.getValue().book_title+"\nAuthor: "+entry.getValue().author+"\n");
            }
        }

        public static void view_all_books_available(){
            if(book_dict.size()==0){
                System.out.println("No books available in the library");
                return;
            }
            System.out.println("Books available in libarary are: ");
            for(Map.Entry<Integer,Book> entry:book_dict.entrySet()) if (entry.getValue().available==true) System.out.println("\nBook ID: "+entry.getKey()+"\nBook title: "+entry.getValue().book_title+"\nAuthor: "+entry.getValue().author+"\n");
        }
    }

    static class Book{
        private String book_title;
        private String author;
        private int book_ID;
        private boolean available;
        private long time_start;
        private long time_end;
        public Book(String book_title,String author,int book_ID){
            this.book_title=book_title;
            this.author=author;
            this.book_ID=book_ID;
            this.available=true;
        }
        public int current_fine(){
            time_end=System.currentTimeMillis();
            if ((time_end-time_start)/1000<10) return 0;
            else return (int)((time_end-time_start)/1000-10)*3;
        }
    }

    static class Member{

        private String name;
        private int age;
        private String phone_number;
        private int fine;
        private Set<Book> hash_set;
        public Member(String name,int age,String phone_number){
            this.name=name;
            this.age=age;
            this.phone_number=phone_number;
            this.fine=0;
            this.hash_set=new HashSet<Book>();
        }

        public void my_books(){
            if (hash_set.size()==0){
                System.out.println("no books issued");
                return;
            }
            for(Book book:hash_set){
                System.out.println("\nBook ID: "+book.book_ID+"\nBook title: "+book.book_title+"\nAuthor: "+book.author+"\n");
            }
        }

        public boolean eligible_to_issue_book(){
            if (hash_set.size()==2){
                System.out.println("already issued 2 books");
                return false;
            }
            int current_fine=fine;
            for(Book book:hash_set){
                current_fine+=book.current_fine();
            }
            if(current_fine>0){
                System.out.println("you have to pay the fine first of amount: "+current_fine);
                return false;
            }
            return true;
        }

        public void issue_book(int book_ID,String book_title ){
            boolean present=true;
            if(book_dict.containsKey(book_ID)){
                Book book=book_dict.get(book_ID);
                if (book.book_title.equals(book_title) && (book.available==true)){
                    book.available=false;
                    System.out.println("Book issued successfully!");
                    hash_set.add(book);
                    book.time_start=System.currentTimeMillis();
                }
                else present=false;
            }
            else{
                present=false;
            }
            if (!present) {
                System.out.println("Book not found!");
            }
        }

        public void return_book(int book_ID){
            if (hash_set.size()==0){
                System.out.println("no books issued");
                return;
            }
            boolean present=true;
            if(book_dict.containsKey(book_ID)){
                Book book=book_dict.get(book_ID);
                if (book.available==false){
                    book.available=true;
                    System.out.println("Book returned successfully!");
                    fine+=book.current_fine();
                    hash_set.remove(book);
                }
                else present=false;
            }
            else{
                present=false;
            }
            if (present==false) {
                System.out.println("Book not found!");
            }
        }

        public void pay_fine(){
            int current_fine=0;
            for(Book book:hash_set){
                current_fine+=book.current_fine();
            }
            if(current_fine==0){
                System.out.println("Fine paid successfully, Rs."+fine);
                fine=0;
            }
            else{
                System.out.println("Return the book to pay the fine");
            }
        } 
    }



    public static void print_menu(){
        System.out.println("---------------------------------");
        if(Menu.get_page()==0){
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
        }
        else if(Menu.get_page()==1){
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
        }
        else if(Menu.get_page()==2){
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
        }
        System.out.println("---------------------------------");
    }




    public static void execute(int input){
        if(Menu.get_page()==0){
            if(input==1){
                Menu.set_page(1);
            }
            else if(input==2){
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                System.out.println("Enter phone number: ");
                String phone_number = sc.next();
                sc.nextLine();
                boolean check=true;
                if(mem_dict.containsKey(phone_number)){
                    member = mem_dict.get(phone_number);
                    if (member.name.equals(name)) {
                        Menu.set_page(2);
                    }
                    else check=false;
                
                }
                else{
                    check=false;   
                }
                if (check==false) System.out.println("Invalid Input");
            
            }            
            else if(input==3){
                System.out.println("---------------------------------");
                System.out.println("Thanks for visiting!");
                System.out.println("---------------------------------");
                System.exit(0);
            }
            else{
                System.out.println("Invalid Input");
            }
        }



        else if(Menu.get_page()==1){
            if(input==1){
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter phone number: ");
                String phone_number = sc.next();
                sc.nextLine();
                Librarian.register_member(name,age,phone_number);
            }
            else if(input==2){
                System.out.println("Enter phone number: ");
                String phone_number = sc.next();
                Librarian.remove_member(phone_number);
            }
            else if(input==3){
                System.out.println("Enter book title: ");
                String book_title = sc.nextLine();
                System.out.println("Enter author: ");
                String author = sc.nextLine();
                System.out.println("Enter number of copies: ");
                int copies = sc.nextInt();
                sc.nextLine();
                Librarian.add_book(book_title,author,copies);
            }
            else if(input==4){
                System.out.println("Enter book ID: ");
                int book_ID = sc.nextInt();
                sc.nextLine();
                Librarian.remove_book(book_ID);
            }
            else if(input==5){
                Librarian.view_all_members();
            }
            else if(input==6){
                Librarian.view_all_books();
            }
            else if(input==7){
                Menu.set_page(0);
            }
            else{
                System.out.println("Invalid Input");
            }
        }



        else if(Menu.get_page()==2){
            if (input==1){
                Librarian.view_all_books_available();
            }
            else if (input==2){
                member.my_books();
            
            }
            else if (input==3){
                if(!member.eligible_to_issue_book()) return;
                Librarian.view_all_books_available();
                System.out.println("Enter the book ID: ");
                int book_ID=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the book title: ");
                String book_title=sc.nextLine();
                member.issue_book(book_ID,book_title);
            }
            else if (input==4){
                System.out.println("Books issued by you: ");
                member.my_books();
                System.out.println("Enter the book ID: ");
                int book_ID=sc.nextInt();
                sc.nextLine();
                member.return_book(book_ID);
            
            }
            else if (input==5){
                member.pay_fine();
            }

            else if(input==6){
                Menu.set_page(0);
            }
            else{
                System.out.println("Invalid Input");
            }
        }
        
    }


    public static void main(String[] args){
        System.out.println("Library Portal Initialized...");
        while(true){
            print_menu();
            int input = sc.nextInt();
            sc.nextLine();
            execute(input);
        }
    }
}