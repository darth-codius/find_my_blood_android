package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.RequestAdapter
import live.adabe.findmyblood.databinding.FragmentSentRequestBinding
import live.adabe.findmyblood.utils.Preferences
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
        binding.apply {
            Glide.with(requireActivity()).load(Preferences(requireActivity()).getImage())
                .into(sentRequestImageHolder)
                .onLoadFailed(requireContext().getDrawable(R.drawable.ic_profile))

            sentOpenNav.setOnClickListener {
                val navDrawer: DrawerLayout = requireActivity().findViewById(R.id.drawerLayout)
                navDrawer.openDrawer(GravityCompat.START)
            }
        }


        return binding.root

    }


}