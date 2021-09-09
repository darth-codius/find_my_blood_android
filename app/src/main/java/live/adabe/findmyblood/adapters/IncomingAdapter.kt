package live.adabe.findmyblood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.adabe.findmyblood.databinding.RequestItemBinding
import live.adabe.findmyblood.models.network.request.ReceivedRequest
import live.adabe.findmyblood.models.network.request.Request

class IncomingAdapter(var requests: List<ReceivedRequest>) :
    RecyclerView.Adapter<IncomingAdapter.IncomingRequestViewHolder>() {
    inner class IncomingRequestViewHolder(private val binding: RequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(request: ReceivedRequest) {
            binding.apply {
                hospitalName.text = request.requestingHospital
                bloodGroup.text = request.blood
                unit.text = request.requestedUnits.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomingRequestViewHolder {
        val binding = RequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncomingRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncomingRequestViewHolder, position: Int) {
        holder.bind(requests[position])
    }

    override fun getItemCount(): Int {
        return requests.size
    }
}