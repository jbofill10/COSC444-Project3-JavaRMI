import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server implements Tax {

    public static void main(String[] args) {
        try{
            Server server = new Server();
            Tax stub = (Tax) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Tax", stub);

            System.err.println("Server ready");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public HashMap<Double, Integer> calculateTax(double salary, double taxRate) {

        HashMap<Double, Integer> entry = new HashMap<>();

        int bracket = getBracket(taxRate*100);
        double taxedSalary = salary - (taxRate*salary);
        entry.put(taxedSalary, bracket);

        return entry;
    }

    private int getBracket(double taxRate){

        if(taxRate <= 5) return 1;

        else if(taxRate >= 6 && taxRate <= 10) return 2;

        else if(taxRate >= 11 && taxRate <= 15) return 3;

        else if(taxRate >= 16 && taxRate <= 20) return 4;

        else if(taxRate > 20) return 5;

        else return 1;

    }


}
