public class Amphibian extends Animal{
    public Amphibian(String name, String type, String sound){
        super(name, type, sound);
    }
    
    public String toString(){
        return "Amphibian" + super.getName();
    }
}
