package modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Carrinho {

	private Map<String, Produto> carrinho;
	
	public Carrinho() {
		carrinho = new HashMap<>();
	}
	
	public String addItem(Produto novo) {
		
		this.carrinho.put(novo.getNome().toLowerCase(), novo);
		
		return "Item adicionado ao carrinho";
	}
	
	public Produto removerItem(String nome) {
		
		if(checkExist(nome)) {
			
			Produto removido = this.carrinho.get(nome);
			this.carrinho.remove(nome.toLowerCase());
			
			return removido;
			
		} else {
			return null;
		}
		
	}
	
	public String getList() {
		
		String listString = "Itens no carrinho:\n";
		listString += "Id  Nome\tTipo\t\tValor\tExtra\n";
		
		SortedSet<String> keys = new TreeSet<>(carrinho.keySet());
		
		if(keys.size() > 0) {
			for (Object key : keys) { 
				Produto objeto = carrinho.get(key); 
				listString += objeto.toString() + "\n";
			}
			
			listString += "\nFinalizar compra (S), continuar comprando (N) ou remover produto do carrinho (R)?";
		} else {
			listString += "Sem produtos no carrinho, pressione (N) para continuar comprando";
		}
		
		return listString;
	}
	
	public String finalizarCompra() {
		
		this.carrinho.clear();
		return "Compra finalizada";
		
	}
	
	public boolean checkExist(String nome) {
		
		boolean exist = false;
		
		for (Object key : carrinho.keySet()) { 
			
			if(key.toString().equalsIgnoreCase(nome))
				exist = true;
			
		}
		
		return exist;
	}
	
}
