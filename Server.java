

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RemoteInterface {

    public static int MAX = 4;

    private static final long serialVersionUID = 1L;


    protected Server() throws RemoteException {

        super();

    }

    public int[][] multiplyBlock(int A[][], int B[][]) throws RemoteException {
      System.out.println("Running multiplyBlock in server.");
        int C[][] = new int[MAX][MAX];
        C[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        C[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        C[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        C[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
        return C;
    }

    public int[][] addBlock(int A[][], int B[][]) throws RemoteException {
      System.out.println("Running addBlock in server.");

        int C[][] = new int[MAX][MAX];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static void main(String[] args) {

        try {
          Registry registry = LocateRegistry.getRegistry();
            registry.rebind("MyServer", new Server());
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }
}
