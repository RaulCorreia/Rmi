package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import modelo.Alimento;
import modelo.Const;
import modelo.Eletronico;
import modelo.ListProduto;
import modelo.Roupa;
import modelo.Usuario;

public class ServerLoja {
	
	
	public static void main(String args[]) {
		
		System.setProperty("java.security.policy", "java.policy");
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
	    
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		
		
		try {
			
			//------------ USUARIOS
			Map<String, Usuario> usuarios = new HashMap<>();
			Usuario func = new Usuario(Const.FUNCIONARIO, "func", "123");
	        Usuario cliente = new Usuario(Const.CLIENTE, "cliente", "123");
	        
	        usuarios.put(func.getUsuario(), func);
	        usuarios.put(cliente.getUsuario(), cliente);
	        //---------------
	        
	        //-------------- PRODUTOS
	        ListProduto lista = new ListProduto();
	        
	        // ID, Nome, Tipo, Preco
	        Eletronico e1 = new Eletronico("1", "Tv", "1000,00", "220");
	        Eletronico e2 = new Eletronico("2", "Celular", "1000,00", "220");
	        Eletronico e3 = new Eletronico("3", "Som", "250,00", "220");
	        Eletronico e4 = new Eletronico("4", "Computador", "1500,00", "220");
	        
	        Alimento a1 = new Alimento("5", "Arroz", "5,00", "1");
	        Alimento a2 = new Alimento("6", "Feij„o", "8,00", "1");
	        Alimento a3 = new Alimento("7", "Farinha", "2,00", "1");
	        Alimento a4 = new Alimento("8", "Leite", "2,50", "0,5");
	        
	        Roupa r1 = new Roupa("9", "Camisa", "100,00", "M");
	        Roupa r2 = new Roupa("10", "CalÁa", "150,00", "40");
	        Roupa r3 = new Roupa("11", "Meia", "15,00", "M");
	        Roupa r4 = new Roupa("12", "Cueca", "25,00", "G");
	        
	        lista.setItem(e1);
	        lista.setItem(e2);
	        lista.setItem(e3);
	        lista.setItem(e4);
	        lista.setItem(a1);
	        lista.setItem(a2);
	        lista.setItem(a3);
	        lista.setItem(a4);
	        lista.setItem(r1);
	        lista.setItem(r2);
	        lista.setItem(r3);
	        lista.setItem(r4);
	        //---------------------
			
			//criar objeto servidor
			ImplLoja refObjetoRemoto = new ImplLoja(usuarios, lista);
			
			Loja skeleton = (Loja) UnicastRemoteObject.exportObject(refObjetoRemoto, 0);

			
			LocateRegistry.createRegistry(20002); 
			
			
			Registry registro = LocateRegistry.getRegistry(20002);
			
			/* O m√©todo bind √© ent√£o chamado no stub do registro para vincular 
			 * o stub do objeto remoto ao nome "Loja" no registro.*/
			
			registro.bind("Loja", skeleton);

			System.err.println("Servidor pronto");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	
}
