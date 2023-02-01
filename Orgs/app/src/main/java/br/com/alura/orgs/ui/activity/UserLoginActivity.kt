package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityUserLoginBinding
import br.com.alura.orgs.extensions.goTo
import br.com.alura.orgs.extensions.toast
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.usuarioLogadoPreference
import kotlinx.coroutines.launch

class UserLoginActivity: AppCompatActivity() {

    private lateinit var bindingLogin: ActivityUserLoginBinding


    private val usuarioDao by lazy {
        val db = AppDataBase.instance(this)
        db.usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)
        val buttonLogin = bindingLogin.buttonUserLogin
        val buttonCad = bindingLogin.buttonUserCadastro
        configuraBotaoEntrar(buttonLogin)
        buttonCad.setOnClickListener {
            this.goTo(CadastroUserActivity::class.java)
        }
    }

    private fun configuraBotaoEntrar(buttonLogin: Button) {
        buttonLogin.setOnClickListener {
            val usernameInput = bindingLogin.userIdEditText
            val passwordInput = bindingLogin.userPasswordEditText
            val userName = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            autentica(userName, password)

        }
    }

    private fun autentica(userName: String, password: String) {
        //cria a corroutine
        lifecycleScope.launch {
            //procura pelo usuário no banco de dados, se for nulo, tem o elvis operator para exibir
            //um toast, se não for, salva o dado username no dataStore que é uma extensions de context
            usuarioDao.autentica(userName, password)?.let {
                dataStore.edit { preferences ->
                    preferences[usuarioLogadoPreference] = it.username
                }
                goTo(ListaProdutoActivity::class.java)
                finish()
            } ?: toast("Falha na autenticação.")
        }
    }


}