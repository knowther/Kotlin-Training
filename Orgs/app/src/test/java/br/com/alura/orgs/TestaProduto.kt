package br.com.alura.orgs

import br.com.alura.orgs.model.Produto
import junit.framework.TestCase.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeFalse
import org.junit.Test
import java.math.BigDecimal

class ProdutoTests {

    @Test
    fun `Given ProdutoValorValido`(){
        val produtoValido = Produto(nome = "Banana", descricao = "Banana Prata", valor = BigDecimal(6.99))
//        assertTrue("Produto com valor válido", produtoValido.valorEValido)
        produtoValido.valorEValido shouldBe true
    }

    @Test
    fun Should_false_when_ProdutoValorInvalidoMenorQue0(){
        val produtoInvalido = Produto(nome = "Banana", descricao = "Banana Anã", valor = BigDecimal(-1))
//        assertFalse("Produto com valor negativo",produtoInvalido.valorEValido)
        produtoInvalido.valorEValido shouldBe false
    }

    @Test
    fun Should_false_when_produtoValorInvalidoIgual0(){
        val produtoInvalido = Produto(nome = "Maça", descricao = "Maçã Comum", valor = BigDecimal(0))
//        assertFalse("produto com valor igual a 0",produtoInvalido.valorEValido)
        produtoInvalido.valorEValido.shouldBeFalse()
    }



}