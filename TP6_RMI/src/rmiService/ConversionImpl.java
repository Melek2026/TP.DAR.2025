package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConversionImpl extends UnicastRemoteObject implements IConversion {
    public ConversionImpl() throws RemoteException {
        super();
    }

    @Override
    public double convertirMontant(double mt) throws RemoteException {
        return mt * 3.36; // Exemple : 1 euro = 3,36 dinars
    }
}