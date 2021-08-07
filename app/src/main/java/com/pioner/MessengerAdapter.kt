import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pioner.Message
import com.pioner.R

class MessengerAdapter(private val exerciseslist: ArrayList<Message>) : RecyclerView.Adapter<MessengerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = exerciseslist[position]
        holder.user.text = currentitem.user
        holder.msg.text = currentitem.msg
    }

    override fun getItemCount(): Int {
        return exerciseslist.size
    }

    @SuppressLint("ResourceType")
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val user : TextView = itemView.findViewById(R.id.msg_user)
        val msg : TextView = itemView.findViewById(R.id.msg_body)
    }
}