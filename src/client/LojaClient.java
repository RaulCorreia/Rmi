package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import modelo.Const;
import server.Loja;


public class LojaClient {
	

	private LojaClient() {}

    public static void main(String[] args) {
    	
    	boolean exit = false;
    	boolean exitMenu = false;
    	
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
            
            
            do {
            	
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
					        	case 1:{
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
					        	case 2:{
					        		System.out.print("Digite o nome do produto que deseja apagar:");
					        		String nome = teclado.nextLine();
					        		
					        		System.out.println(">> "+stubObjRemotoCliente.apagarProd(nome));
					        	}
					        		break;
					        		
					        	// Listar
					        	case 3:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.listarProd());
					        		System.out.println("");
					        	}
					        		break;
					        	case 4:{
					        		System.out.println("Digite o nome do produto:");
					        		String nome = teclado.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarNome(nome));
					        		System.out.println("");					        		
					        	}
					        		break;
					        	case 5:{
					        		System.out.println("Digite o id do produto:");
					        		String nome = teclado.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarId(nome));
					        		System.out.println("");
					        	}
					        		break;
					        	case 6:{
					        		System.out.println("Digite o Nome do produto que deseja alterar:");
					        		String nomeAtual = teclado.nextLine();
					        		System.out.println("Digite o id do produto:");
					        		String id = teclado.nextLine();
					        		System.out.println("Digite o nome do produto:");
					        		String nome = teclado.nextLine();
					        		System.out.println("Digite o tipo do produto:");
					        		String tipo = teclado.nextLine();
					        		System.out.println("Digite o preco do produto:");
					        		String preco = teclado.nextLine();
					        		String extra = "";
					        		
					        		if(tipo.equalsIgnoreCase("Alimento")) {
					        			
					        			System.out.println("Digite o Kg do produto:");
						        		extra = teclado.nextLine();
					        			
					        		} else if(tipo.equalsIgnoreCase("Eletronico")){
					        			
					        			System.out.println("Digite a Voltagem do produto:");
						        		extra = teclado.nextLine();
										
									} else {
										
										System.out.println("Digite o Tamanho do produto:");
						        		extra = teclado.nextLine();
										
									}
					        		
					        		if(!extra.isEmpty()) {
						        		System.out.println("");
						        		System.out.println(stubObjRemotoCliente.atualizar(nomeAtual, id, nome, preco, tipo, extra));
						        		System.out.println("");
					        		} else {
					        			System.out.println("Tipo não definido...");
					        		}
					        		
					        		
					        	}
					        		break;
					        	case 7: {
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.quantidade());
					        		System.out.println("");
					        	}
					        		break;
					        	case 8:{
					        		System.out.println("Digite o nome do produto");
					        		String nome = teclado.nextLine();
					        		System.out.println(stubObjRemotoCliente.addCarrinho(nome));
					        		System.out.println("");
					        	}
					        		break;
					        	case 9:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.showCarrinho());
					        		System.out.println("");
					        		System.out.println("Finalizar compra (S), continuar comprando (N) ou remover produto do carrinho (R)?");
					        		String end = teclado.nextLine();
					        		
					        		if(end.equalsIgnoreCase("S")) {
					        			System.out.println(stubObjRemotoCliente.finalizarCompra());
					        		} else if(end.equalsIgnoreCase("R")) {
					        			System.out.println("Digite o nome do produto:");
					        			String nome = teclado.nextLine();
					        			System.out.println(stubObjRemotoCliente.removeCarrinho(nome));
					        		}
					        		
					        		System.out.println("");
					        	}
					        		break;
					        	case 10:{
					        		exitMenu = true;
					        	}
					        		break;
				        	}
			        	
			        	}catch(NumberFormatException e) {
				    		System.out.println("Opção Invalida...");
				    	}
			        	
			        	
			        	
		        	}while(!exitMenu);
		        	
		        	
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
	        
		        
		        System.out.println("Tentar outra vez?\nS ou N?");
		        String opcao = teclado.nextLine();
		        if(opcao.equalsIgnoreCase("n")) {
		        	exit = true;
		        }
	        
            }while(!exit);
            
            
            
            System.out.println("Cliente encerrado...");
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        
        
        teclado.close();
    }
	    
}
