package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.alura.orgs.dao.ProdutoDAO
import br.com.alura.orgs.databinding.ActivityFormProdutoBinding
import br.com.alura.orgs.extensions.tryImageLoader
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal


class FormProdutoActivity : AppCompatActivity() {
    private lateinit var bindingProduto: ActivityFormProdutoBinding

    private var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingProduto = ActivityFormProdutoBinding.inflate(layoutInflater)
        setContentView(bindingProduto.root)
        title = "Cadastrar Produto"
        val button = bindingProduto.botaoSalvar

        bindingProduto.activityFormProductImage.setOnClickListener{
            FormImageDialog(this).show(url){
                imagem -> url = imagem
                bindingProduto.activityFormProductImage.tryImageLoader(url)
            }
        }



        button.setOnClickListener {

            //bindings
            val nomeInput = bindingProduto.nameInputEditText
            val descricaoInput = bindingProduto.descInputEditText
            val valorInput = bindingProduto.itemValueInputEditText



            //get values
            val nome = nomeInput.text.toString()
            val descricao = descricaoInput.text.toString()
            val valorText = valorInput.text.toString()
            val valorConvertido = if(valorText.isBlank()) BigDecimal.ZERO else BigDecimal(valorText)


            val produtoNovo = Produto(
                nome = nome,
                descricao = descricao,
                valor = valorConvertido,
                image = url
            )

            Log.i("FormProdutoAct", "onCreate: ${produtoNovo}")

           val dao = ProdutoDAO()
            dao.addProduto(produtoNovo)
            Log.i("FormProdutoACT", "onCreate: ${dao.buscaTodos()}")
            finish()
        }


    }



}