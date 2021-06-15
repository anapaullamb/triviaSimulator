package com.paula.ana.supertriviasimulator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.controllers.GameController
import com.paula.ana.supertriviasimulator.dao.CategoriaDAO
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria
import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import kotlinx.android.synthetic.main.item_categoria.view.*

class CategoriaAdapter():  RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {
    private val dao = CategoriaDAO()
    private var categoria = mutableListOf<Categoria>()
    init {
      dao.getAll { categoriaRetorno ->
          categoria = categoriaRetorno.data?.categorias.toMutableList();
          notifyDataSetChanged()
        }
    }

    override fun getItemCount() = categoria.size

    override fun getItemViewType(position: Int): Int {
        val categoriaa = categoria[position]
        return if (categoriaa.name ==GameController.category.name) {
            R.layout.item_categoria_selecionada
        } else {
            R.layout.item_categoria
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(viewType, parent, false)
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categoria[position]
        holder.fillView(categoria)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun fillView(categoria: Categoria){
                itemView.lbName.text = categoria.name;
                itemView.setOnClickListener {
                    GameController.category = categoria
                    notifyItemChanged(position)

                    Log.i("Teste1", "entrou: "+GameController.category.name)
               }
        }
    }
}