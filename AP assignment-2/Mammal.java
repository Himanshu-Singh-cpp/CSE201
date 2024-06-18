public class Mammal extends Animal{
    public Mammal(String name, String type, String sound){
        super(name, type, sound);
    }
    
    public String toString(){
        return "Mammal" + super.getName();
    }
}
