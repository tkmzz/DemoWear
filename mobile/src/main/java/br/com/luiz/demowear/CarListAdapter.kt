package br.com.luiz.demowear

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.luiz.shared.Car
import kotlinx.android.synthetic.main.adapter_car.view.*


class CarListAdapter(
        private val carros: MutableList<Car>,
        private val callback: Callback?
) : RecyclerView.Adapter<CarListAdapter.CarroViewHolder>() {

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val carro = carros[position]
        holder.itemView.tvModel.text = carro.model
        holder.itemView.tvManufactor.text = carro.manufactor
        holder.itemView.setOnClickListener {
            callback?.carroClicked(carro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_car, parent, false)
        return CarroViewHolder(view)
    }

    override fun getItemCount() = carros.size

    inner class CarroViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface Callback {
        fun carroClicked(carro: Car)
    }
}
