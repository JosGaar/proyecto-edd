package entities;

public class Client {
    
    public static void main(String[] args)
    {
        Program programa = new Program();
        programa.run();
        
        // Reserva Natural
        NatureReserveManager reserve = new NatureReserveManager();
        
        // Visitantes
        Visitor visitor1 = new Visitor("Ambato", "0984011952", "1303753618", "Josue", "Garcia");
        Visitor visitor2 = new Visitor("Ambato", "0974512356", "1599213834", "Byron", "David");
        Visitor visitor3 = new Visitor("Ambato", "0935578345", "1921308418", "Carlos", "Daniel");
        
        reserve.addVisitor(visitor1);
        reserve.addVisitor(visitor2);
        reserve.addVisitor(visitor3);

        
        
        
    }
}
