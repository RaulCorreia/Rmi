package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import modelo.Const;
import server.Loja;


public class LojaClient {
	

	 private LojaClient() {}

    public static void main(String[] args) {
    	
    	System.setProperty("java.security.policy", "java.policy");
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    	
    	Scanner teclado = new Scanner(System.in);
    	
    	System.out.println("Informe o nome/endereco do servidor:");
        String host = teclado.nextLine();
        
        try {
        	
        	
        	Registry registro = LocateRegistry.getRegistry(host, 20002);
            Loja stubObjRemotoCliente = (Loja) registro.lookup("Loja");
            
            
            
            System.out.println("Usuario:");
	        String user = teclado.nextLine();
	        System.out.println("Senha:");
	        String password = teclado.nextLine();
            
	        int result = stubObjRemotoCliente.login(user, password);
	        
	        if(result == Const.FUNCIONARIO) {
	        	
	        	System.out.println("Funcionario logado com sucesso!");
	        	String opcao = "";
	        	do {
	        		String menu = stubObjRemotoCliente.menuFunc();
		        	System.out.println(menu);
		        	opcao = teclado.nextLine();
		        	
		        	try {
		        		
			        	int digito = Integer.parseInt(opcao);
			        	
			        	switch(digito) {
			        	
			        		// Adicionar
				        	case 0:{
				        		System.out.print("Digite o Id do produto:");
				        		String id = teclado.nextLine();
				        		
				        		System.out.print("Digite o nome do produto:");
				        		String nome = teclado.nextLine();
				        		
				        		System.out.print("Digite o preço do produto:");
				        		String preco = teclado.nextLine();
				        		
				        		System.out.print("Digite o tipo do produto: Alimento, Eletronico ou Roupa: ");
				        		String tipo = teclado.nextLine();
				        		String extra = "";
				        		
				        		do {
				        			
					        		if(tipo.equalsIgnoreCase("Alimento")) {
					        			
					        			System.out.print("Digite o kilo do produto:");
						        		extra = teclado.nextLine();
						        		break;
					        			
					        		} else if(tipo.equalsIgnoreCase("Eletronico")) {
					        			
					        			System.out.print("Digite a voltagem do produto:");
						        		extra = teclado.nextLine();
						        		break;
					        			
					        		} else if(tipo.equalsIgnoreCase("Roupa")) {
					        			
					        			System.out.print("Digite o tamanho do produto:");
						        		extra = teclado.nextLine();
						        		break;
					        			
					        		} else {
					        			System.out.println("Tipo invalido");
					        		}
					        		
				        		} while (true);
				        		
				        		
				        		System.out.print(stubObjRemotoCliente.adicionarProduto(id, nome, preco, tipo, extra));
				        		
				        		
				        	}
				        		break;
				        		
				        		
				        	// Apagar
				        	case 1:{
				        		System.out.print("Digite o nome do produto que deseja apagar:");
				        		String nome = teclado.nextLine();
				        		
				        		System.out.print(stubObjRemotoCliente.apagarProd(nome));
				        	}
				        		break;
				        	case 2:
				        		break;
				        	case 3:
				        		break;
				        	case 4:
				        		break;
				        	case 5:
				        		break;
				        	case 6:
				        		break;
				        	case 7:
				        		break;
				        	case 8:
				        		break;
				        	case 9:
				        		break;
			        	}
		        	
		        	}catch(NumberFormatException e) {
			    		System.out.println("Opção Invalida...");
			    	}
		        	
		        	
		        	
	        	}while(!opcao.equalsIgnoreCase("sair"));
	        	
	        	
	        } else if (result == Const.CLIENTE) {
	        	
	        	System.out.println("Cliente logado com sucesso!");
	        	String opcao = "";
	        	
	        	
	        	do {
	        		String menu = stubObjRemotoCliente.menuFunc();
		        	System.out.println(menu);
		        	opcao = teclado.nextLine();
		        	
        			try {
		        		
			        	int digito = Integer.parseInt(opcao);
			        	
			        	switch(digito) {
				        	case 0:
				        		break;
				        	case 1:
				        		break;
				        	case 2:
				        		break;
				        	case 3:
				        		break;
				        	case 4:
				        		break;
				        	case 5:
				        		break;
				        	case 6:
				        		break;
				        	case 7:
				        		break;
				        	case 8:
				        		break;
				        	case 9:
				        		break;
			        	}
		        	
		        	}catch(NumberFormatException e) {
			    		System.out.println("Opção Invalida...");
			    	}
		        	
	        	}while(!opcao.equalsIgnoreCase("sair"));
	        	
	        } else if (result == -1) {
	        	
	        	System.out.println("A senha esta incorreta");
	        	
	        } else {
	        	
	        	System.out.println("Usuario esta incorreto");
	        	
	        }
            
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        
        
        teclado.close();
    }
	    
}
