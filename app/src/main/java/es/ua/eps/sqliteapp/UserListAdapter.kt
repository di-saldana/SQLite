package es.ua.eps.sqliteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private var users: List<User> = ArrayList()

    fun addItems(items: List<User>){
        this.users = items
        notifyDataSetChanged()
    }

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var username = view.findViewById<TextView>(R.id.username)
        private var email = view.findViewById<TextView>(R.id.email)

        fun bindView(user : User){
            username.text = user.username
            email.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
    )

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bindView(user)
    }

}


