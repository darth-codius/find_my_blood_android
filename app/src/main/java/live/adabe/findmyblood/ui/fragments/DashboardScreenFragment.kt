package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.IncomingAdapter
import live.adabe.findmyblood.adapters.RequestAdapter
import live.adabe.findmyblood.databinding.FragmentDashboardScreenBinding
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class DashboardScreenFragment : Fragment() {
    private lateinit var binding: FragmentDashboardScreenBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var incomingAdapter: IncomingAdapter
    private lateinit var sentAdapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 2)
        )[MainViewModel::class.java]
        incomingAdapter = IncomingAdapter(listOf())
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
            sentRequestRv.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = sentAdapter
            }

            incomingRequestRv.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = incomingAdapter
            }
            Glide.with(requireActivity()).load(Preferences(requireActivity()).getImage())
                .into(dashboardImageHolder)
                .onLoadFailed(requireContext().getDrawable(R.drawable.ic_profile))
        }

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.run {
            incomingRequestLiveData.observe(viewLifecycleOwner, { data ->
                data?.let {
                    if (it.isNotEmpty() && it.size >= 2) {
                        incomingAdapter.requests = it.subList(0, 2)
                        binding.incomingRequestRv.adapter?.notifyDataSetChanged()
                    } else {
                        incomingAdapter.requests = it
                        binding.incomingRequestRv.adapter?.notifyDataSetChanged()
                    }
                }

            })
            sentRequestsLiveData.observe(viewLifecycleOwner, { data ->
                data?.let {
                    if (it.isNotEmpty() && it.size >= 2) {
                        sentAdapter.requests = it.subList(0, 2)
                        binding.sentRequestRv.adapter?.notifyDataSetChanged()
                    } else {
                        sentAdapter.requests = it
                        binding.sentRequestRv.adapter?.notifyDataSetChanged()
                    }
                }
            })
        }
    }
}