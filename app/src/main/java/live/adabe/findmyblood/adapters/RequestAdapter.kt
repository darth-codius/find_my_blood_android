package live.adabe.findmyblood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.adabe.findmyblood.databinding.RequestItemBinding
import live.adabe.findmyblood.models.network.request.Request

class RequestAdapter(var requests: List<Request>) :
    RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {
    inner class RequestViewHolder(private val binding: RequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(request: Request) {
            binding.apply {
                hospitalName.text = request.hospital
                bloodGroup.text = request.blood
                unit.text = request.requestedUnits.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = RequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(requests[position])
    }

    override fun getItemCount(): Int {
        return requests.size
    }
}