package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.RequestAdapter
import live.adabe.findmyblood.databinding.FragmentSentRequestBinding
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class SentRequestFragment : Fragment() {

    private lateinit var binding: FragmentSentRequestBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var requestAdapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSentRequestBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 2)
        )[MainViewModel::class.java]
        requestAdapter = RequestAdapter(listOf())

        binding.rvSentRequest.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }

        viewModel.sentRequestsLiveData.observe(viewLifecycleOwner, { data ->
            data?.let {
                requestAdapter.requests = it
                requestAdapter.notifyDataSetChanged()
            }
        })



        return binding.root

    }


}