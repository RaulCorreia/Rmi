package server;

import java.rmi.RemoteException;
import java.util.Map;

import modelo.Const;
import modelo.ListProduto;
import modelo.Usuario;

public class ImplLoja implements Loja{
	
	private Map<String, Usuario> usuarios;
	private ListProduto lista;
	private int typeUserLogged;
	
	public ImplLoja(Map<String, Usuario> usuarios, ListProduto lista) {
		
		this.usuarios = usuarios;
		this.lista = lista;
	}
	
	
	
	@Override
	public int login(String user, String password) {
		
		
		for (Object key : usuarios.keySet()) { 
			
			if(key.toString().equalsIgnoreCase(user)) {
				Usuario usuario = usuarios.get(user);
				
				
				if(usuario.getSenha().equals(password)) {
					
					this.typeUserLogged = usuario.getRole();
					
					String logado = "";
					if(this.typeUserLogged == Const.FUNCIONARIO)
						logado = "Funcionario logado com sucesso!";
					else
						logado = "Cliente logado com sucesso!";
					
					return this.typeUserLogged;
				} else {
					return -1;
				}
			}
				
		}
		
		
		return -2;
	}
	
	
	@Override
	public String menuFunc(){
		
		
		String menu = 	"Digite o numero do menu\n" +
						"Opções:\n" +
						"1- Adicionar Produto\n" +
				    	"2- Apagar Produto\n" +
				    	"3- Listar Produtos\n" +
				    	"4- Pesquisar por nome\n" +
				    	"5- Pesquisar por codigo\n" +
				    	"6- Alterar Produto\n" +
				    	"7- Exibir Quantidade de produtos\n" +
				    	"8- Comprar produto\n" +
				    	"9- Sair\n" +
				    	">> ";
	
		return menu;
	}
	
	
	@Override
	public String menuUser(){
		
		
		String menu = 	"Digite o numero do menu\n" +
						"Opções:\n" +
				    	"3- Listar Produtos\n" +
				    	"4- Pesquisar por nome\n" +
				    	"5- Pesquisar por codigo\n" +
				    	"7- Exibir Quantidade de produtos\n" +
				    	"8- Comprar produto\n" +
				    	"9- Sair\n" +
				    	">> ";
	
		return menu;
	}
	

	@Override
	public String adicionarProduto(String id, String nome, String preco, String tipo, String extra){
		
		return "Correto";
	}
	
	@Override
	public String apagarProd(String nome){
		
		return "Correto";
	}



}
