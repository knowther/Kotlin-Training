package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityUserCadastroBinding
import br.com.alura.orgs.extensions.toast
import br.com.alura.orgs.model.Usuario
import kotlinx.coroutines.launch

class CadastroUserActivity: AppCompatActivity() {

    private lateinit var bindingCadastro: ActivityUserCadastroBinding

    private val usuarioDao by lazy {
        val db = AppDataBase.instance(this)
        db.usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCadastro = ActivityUserCadastroBinding.inflate(layoutInflater)
        setContentView(bindingCadastro.root)
        val buttonCad = bindingCadastro.buttonUserCadastro

        buttonCad.setOnClickListener {
            cadastra()
        }
    }

    private fun cadastra() {
        lifecycleScope.launch {
            try {
                usuarioDao.salva(criarUsuario())
                finish()
            } catch (e: Exception) {
                Log.e("TAG", "cadastra ln:35", e)
                toast("Ocorreu um erro ao cadastrar o usuário.")
            }
        }
    }

    private fun criarUsuario(): Usuario{
        val usernameInput = bindingCadastro.userIdEditText
        val nameInput = bindingCadastro.userNameEditText
        val passwordInput = bindingCadastro.userPasswordEditText


        //get values
        val username = usernameInput.text.toString()
        val name = nameInput.text.toString()
        val password = passwordInput.text.toString()

        return Usuario(username = username, nome =  name, password = password)
    }

}