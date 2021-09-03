package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.navigation.fragment.findNavController
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.RequestAdapter
import live.adabe.findmyblood.databinding.FragmentDashboardScreenBinding

class DashboardScreenFragment : Fragment() {
    private lateinit var binding: FragmentDashboardScreenBinding
    private lateinit var incomingAdapter: RequestAdapter
    private lateinit var sentAdapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardScreenBinding.inflate(inflater, container, false)

        incomingAdapter = RequestAdapter(listOf())
        sentAdapter = RequestAdapter(listOf())
        binding.apply {
            sentRequestSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_dashboardScreenFragment_to_sentRequestFragment)
            }

            incomingRequestSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_dashboardScreenFragment_to_incomingRequestFragment)
            }

            dashboardMakeRequestButton.setOnClickListener {
                findNavController().navigate(R.id.action_dashboardScreenFragment_to_requestScreenFragment)
            }

        }

        return binding.root
    }
}