package es.ua.eps.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.sqliteapp.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    private lateinit var bindings: ActivityUpdateUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUpdateUserBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            updateButton.setOnClickListener{

            }

            closeButton.setOnClickListener {
                finish()
            }
        }
    }
}