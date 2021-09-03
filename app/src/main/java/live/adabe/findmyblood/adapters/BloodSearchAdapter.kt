package live.adabe.findmyblood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.adabe.findmyblood.databinding.RequestScreenItemBinding
import live.adabe.findmyblood.models.network.search.DataSearch

class BloodSearchAdapter(var data: List<DataSearch>, val click: (DataSearch) -> Unit) :
    RecyclerView.Adapter<BloodSearchAdapter.BloodSearchViewHolder>() {
    inner class BloodSearchViewHolder(val binding: RequestScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataSearch) {
            binding.apply {
                itemHospitalName.text = data.hospital.name
                itemAddress.text = "${data.hospital.address}, ${data.hospital.state}"
//                Glide.with(root).load(data.hospital.)
                itemUnits.text = "${data.units} Units"
                itemBloodGroup.text = data.bloodGroup

                root.setOnClickListener {
                    click(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodSearchViewHolder {
        val binding =
            RequestScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BloodSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BloodSearchViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}