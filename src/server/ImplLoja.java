package server;

import java.util.Map;

import modelo.Alimento;
import modelo.Eletronico;
import modelo.ListProduto;
import modelo.Carrinho;
import modelo.Produto;
import modelo.Roupa;
import modelo.Usuario;

public class ImplLoja implements Loja{
	
	private Map<String, Usuario> usuarios;
	private ListProduto lista;
	private Carrinho carrinho;
	private int typeUserLogged;
	
	public ImplLoja(Map<String, Usuario> usuarios, ListProduto lista) {
		
		this.usuarios = usuarios;
		this.lista = lista;
		this.carrinho = new Carrinho();
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
		
		if(!id.isEmpty() && !nome.isEmpty() && !preco.isEmpty() && !tipo.isEmpty() && !extra.isEmpty()) {
			
			Produto novo;
			
			if(tipo.equalsIgnoreCase("Alimento")) {
				novo = new Alimento(id, nome, preco, extra);
			} else if(tipo.equalsIgnoreCase("Eletronico")) {
				novo = new Eletronico(id, nome, preco, extra);
			} else {
				novo = new Roupa(id, nome, preco, extra);
			}
			
			this.lista.setItem(novo);
			
			return "Produto adicionado";
		}
		
		return "Todos os itens devem ser preenchidos";
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
				} else if(result.getTipo().equalsIgnoreCase("Roupa")) {
					Roupa produto = (Roupa) result;
					produto.setTamanho(extra);
				} else {
					return "Tipo não identificado";
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
		
		Produto result = this.lista.getItem(nome);
    	String retorno = "Produto inexistente na lista de itens";
    	
    	if(result != null) {
    		retorno = this.carrinho.addItem(result);
    		this.lista.delete(result.getNome());
    	}
		
		
		return retorno;
	}
	
	@Override
	public String showCarrinho() {
		return this.carrinho.getList();
	}
	
	@Override
	public String removeCarrinho(String nome) {
		
		Produto removido = this.carrinho.removerItem(nome);
		
		if(removido != null) {
			this.lista.setItem(removido);
			
			return "Item removido do carrinho";
		}
		
		return "Item não encontrado no carrinho";
	}
	
	@Override
	public String finalizarCompra() {
		return this.carrinho.finalizarCompra();
	}
}
