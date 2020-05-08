import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public interface Tax extends Remote {
    HashMap<Double, Integer> calculateTax(double salary, double taxRate) throws RemoteException;
}

