package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityFormProdutoBinding
import br.com.alura.orgs.extensions.tryImageLoader
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal


class FormProdutoActivity : AppCompatActivity() {
    private lateinit var bindingProduto: ActivityFormProdutoBinding

    private var url: String? = null
    private var produtoId = 0L
    private val produtoDao by lazy{
        val db = AppDataBase.instance(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingProduto = ActivityFormProdutoBinding.inflate(layoutInflater)
        setContentView(bindingProduto.root)
        title = "Cadastrar Produto"


        bindingProduto.activityFormProductImage.setOnClickListener {
            FormImageDialog(this).show(url) { imagem ->
                url = imagem
                bindingProduto.activityFormProductImage.tryImageLoader(url)
            }
        }
        configuraBotaoSalvar()

        tentaCarregarProduto()

        }

    override fun onResume() {
        super.onResume()
        produtoDao.findById(produtoId)?.let {
            preencheCampos(it)
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }


    private fun preencheCampos(produto: Produto) {
        setTitle("Editar Produto")
        url = produto.image
        bindingProduto.activityFormProductImage.tryImageLoader(produto.image)
        bindingProduto.nameInputEditText.setText(produto.nome)
        bindingProduto.descInputEditText.setText(produto.descricao)
        bindingProduto.itemValueInputEditText.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val button = bindingProduto.botaoSalvar
        var db = AppDataBase.instance(this)

        val produtoDao = db.produtoDao()


        button.setOnClickListener {

            criaProduto()
            val produtoNovo = criaProduto()
            produtoDao.salva(produtoNovo)

            finish()
        }
    }

    private fun criaProduto(): Produto {
        //bindings
        val nomeInput = bindingProduto.nameInputEditText
        val descricaoInput = bindingProduto.descInputEditText
        val valorInput = bindingProduto.itemValueInputEditText


        //get values
        val nome = nomeInput.text.toString()
        val descricao = descricaoInput.text.toString()
        val valorText = valorInput.text.toString()
        val valorConvertido =
            if (valorText.isBlank()) BigDecimal.ZERO else BigDecimal(valorText)
        return Produto( id = produtoId, nome = nome, descricao = descricao, valor = valorConvertido, image = url)
    }


}