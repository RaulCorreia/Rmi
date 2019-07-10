package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Loja extends Remote {  
	

	   int login(String user, String password) throws RemoteException;
	   
	   String menuFunc() throws RemoteException;
	   String menuUser() throws RemoteException;
	
	   String adicionarProduto(String id, String nome, String preco, String tipo, String extra) throws RemoteException;  
	   
	   String apagarProd(String nome) throws RemoteException;
	   
} 