package ragavi.com.retrofit25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WineAdapter (
    private val list: List<Wine>
    ) : RecyclerView.Adapter<WineAdapter.WineViewHolder>() {

        class WineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgWine: ImageView = view.findViewById(R.id.imgWine)
            val txtName: TextView = view.findViewById(R.id.txtWineName)
            val txtWinery: TextView = view.findViewById(R.id.txtWinery)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wine, parent, false)
            return WineViewHolder(view)
        }

        override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
            val wine = list[position]

            holder.txtName.text = wine.wine
            holder.txtWinery.text = wine.winery

            Glide.with(holder.itemView.context)
                .load(wine.image)           // ← URL de la imagen
                .placeholder(android.R.drawable.ic_menu_gallery)  // imagen mientras carga
                .error(android.R.drawable.ic_menu_close_clear_cancel) // imagen si falla
                .into(holder.imgWine)
        }

        override fun getItemCount() = list.size
    }
