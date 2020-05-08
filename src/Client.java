import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            Tax stub = (Tax) registry.lookup("Tax");
            HashMap<Double, Integer> response = stub.calculateTax(Double.parseDouble(args[1]), Double.parseDouble(args[2]));

            for(Map.Entry<Double, Integer> entry : response.entrySet()){
                String tax_result = String.format("%.2f", entry.getKey());

                System.out.println("Before Applying Tax:\n" +
                        "Salary: $"+ args[1] + "\n" +
                        "Tax Rate: "+ args[2] + "\n\n" +
                        "After Applying Tax using Bracket " + entry.getValue() + ":\n"+
                        "Salary: $" + tax_result);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
