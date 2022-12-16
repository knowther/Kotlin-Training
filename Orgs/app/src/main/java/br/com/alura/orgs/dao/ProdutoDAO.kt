package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Produto

class ProdutoDAO {

    fun addProduto(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto>{
        return produtos.toList();

    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }

}