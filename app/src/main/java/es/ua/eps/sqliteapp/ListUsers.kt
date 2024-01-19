package es.ua.eps.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.sqliteapp.databinding.ActivityListUsersBinding

class ListUsers : AppCompatActivity() {
    private lateinit var bindings: ActivityListUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityListUsersBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            closeButton.setOnClickListener {
                finish()
            }
        }
    }
}