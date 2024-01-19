package es.ua.eps.sqliteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.sqliteapp.databinding.ActivityUserManagementBinding

class UserManagement : AppCompatActivity() {
    private lateinit var bindings: ActivityUserManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUserManagementBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            newUserButton.setOnClickListener{
                val intent = Intent(this@UserManagement, NewUser::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            updateUserButton.setOnClickListener{

            }

            deleteUserButton.setOnClickListener{

            }

            listUserButton.setOnClickListener{

            }

            backButton.setOnClickListener {
                finish()
            }
        }
    }
}