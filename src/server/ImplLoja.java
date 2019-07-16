package server;

import java.util.Map;

import modelo.Alimento;
import modelo.Eletronico;
import modelo.ListProduto;
import modelo.Produto;
import modelo.Roupa;
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
				    	"8- Adicionar Produto ao Carrinho\n" +
				    	"9- Exibir Carrinho\n" +
				    	"10- Sair\n"+
				    	">> ";
	
		return menu;
	}
	
	
	@Override
	public String menuUser(){
		
		
		String menu = 	"Digite o numero do menu\n" +
						"Opções:\n" +
				    	"1- Listar Produtos\n" +
				    	"2- Pesquisar por nome\n" +
				    	"3- Pesquisar por codigo\n" +
				    	"4- Exibir Quantidade de produtos\n" +
				    	"5- Adicionar Produto ao Carrinho\n" +
				    	"6- Exibir Carrinho\n" +
				    	"7- Sair\n" +
				    	">> ";
	
		return menu;
	}
	

	@Override
	public String adicionarProduto(String id, String nome, String preco, String tipo, String extra){
		
		return "Correto";
	}
	
	@Override
	public String apagarProd(String nome){
		
		boolean result = this.lista.deleteFromList(nome);
    	String mensagem = "";
    	
    	if(result) {
    		mensagem = "Produto Deletado";
    	} else {
    		 mensagem = "Item não existe ou não esta mais disponivel";
    	}
    	
    	return mensagem;
	}

	
	@Override
	public String listarProd(){
		return this.lista.getList();
	}

	
	@Override
	public String buscarNome(String nome){
		
		String cabecalho = "Id  Nome\tTipo\t\tValor\tExtra\n";
		Produto result = this.lista.getItem(nome);
    	
    	if(result != null)
    		return cabecalho + result.toString();
    	else
    		return "Produto não encontrado";
	}
	
	
	@Override
	public String buscarId(String id){
		
		String cabecalho = "Id  Nome\tTipo\t\tValor\tExtra\n";
		Produto result = this.lista.getItemByCod(id);
    	
    	if(result != null)
    		return cabecalho + result.toString();
    	else
    		return "Produto não encontrado";
	}

	
	@Override
	public String atualizar(String nomeAtual, String id, String nome, String preco, String tipo, String extra) {
		
		
		if(!nomeAtual.isEmpty() && !id.isEmpty() && !nome.isEmpty() && !preco.isEmpty() && !tipo.isEmpty() && !extra.isEmpty()) {
		
			Produto result = this.lista.getItem(nomeAtual);
			
			
			if(result != null) {
				
				result.setCodigo(id);
				result.setNome(nome);
				result.setPreco(preco);
				result.setTipo(tipo);
				
				if(result.getTipo().equalsIgnoreCase("Alimento")) {
					Alimento produto = (Alimento) result;
					produto.setKilo(extra);
				} else if(result.getTipo().equalsIgnoreCase("Eletronico")){
					Eletronico produto = (Eletronico) result;
					produto.setVoltagem(extra);
				} else {
					Roupa produto = (Roupa) result;
					produto.setTamanho(extra);
				}
				
	    		return "Produto Atualizado\n" + result.toString();
			} else {
	    		return "Produto não encontrado";
			}
		}
		
		
		return "Todos as informações devem ser preenchidas...";
	}
	
	
	@Override
	public String quantidade() {
		return "Atualmente temos disponivel " + this.lista.size() + " produtos.";
	}
	
	@Override
	public String addCarrinho(String nome) {
		return "Atualmente temos disponivel " + this.lista.size() + " produtos.";
	}
	
	@Override
	public String showCarrinho() {
		return "Atualmente temos disponivel " + this.lista.size() + " produtos.";
	}
	
	@Override
	public String removeCarrinho(String nome) {
		return "Atualmente temos disponivel " + this.lista.size() + " produtos.";
	}
	
	@Override
	public String finalizarCompra() {
		return "Atualmente temos disponivel " + this.lista.size() + " produtos.";
	}
}
