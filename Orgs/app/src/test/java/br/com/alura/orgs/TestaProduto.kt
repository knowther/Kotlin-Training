package br.com.alura.orgs

import br.com.alura.orgs.model.Produto
import junit.framework.TestCase.*
import org.junit.Test
import java.math.BigDecimal

class TestaProduto {

    @Test
    fun ProdutoValorValido(){
        val produtoValido = Produto(nome = "Banana", descricao = "Banana Prata", valor = BigDecimal(6.99))
        assertTrue(produtoValido.valorEValido)
    }

    @Test
    fun ProdutoValorInvalidoMenorQue0(){
        val produtoInvalido = Produto(nome = "Banana", descricao = "Banana Anã", valor = BigDecimal(-1))
        assertFalse(produtoInvalido.valorEValido)

    }

    @Test
    fun produtoValorInvalidoIgual0(){
        val produtoInvalido = Produto(nome = "Maça", descricao = "Maçã Comum", valor = BigDecimal(0))
        assertFalse(produtoInvalido.valorEValido)
    }



}