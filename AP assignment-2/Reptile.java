public class Reptile extends Animal{
    public Reptile(String name, String type, String sound){
        super(name, type, sound);
    }
    
    public String toString(){
        return "Reptile" + super.getName();
    }
}