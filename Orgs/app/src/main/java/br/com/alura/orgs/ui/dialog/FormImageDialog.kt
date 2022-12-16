package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.ActivityFormImagemBinding
import br.com.alura.orgs.extensions.tryImageLoader

class FormImageDialog(val context: Context) {
    fun show(urlPadrao: String? = null, whenImageLoad: (image: String) -> Unit){
        ActivityFormImagemBinding.inflate(LayoutInflater.from(context)).apply {

            urlPadrao?.let{
                activityFormImageImage.tryImageLoader(it)
                activityFormImagemUrlInputEditText.setText(it)
            }

            activityFormImageButton.setOnClickListener {
                val url = activityFormImagemUrlInputEditText.text.toString()
                activityFormImageImage.tryImageLoader(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") {_, _ ->
                    val url = activityFormImagemUrlInputEditText.text.toString()
                    whenImageLoad(url)

                }
                .setNegativeButton("Cancelar") {_, _ ->

                }
                .show()



        }




    }
}