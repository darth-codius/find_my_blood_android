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
import live.adabe.findmyblood.adapters.IncomingAdapter
import live.adabe.findmyblood.adapters.RequestAdapter
import live.adabe.findmyblood.databinding.FragmentIncomingRequestBinding
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class IncomingRequestFragment : Fragment() {
    private lateinit var binding: FragmentIncomingRequestBinding
    private lateinit var requestAdapter: IncomingAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIncomingRequestBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 2)
        )[MainViewModel::class.java]
        requestAdapter = IncomingAdapter(listOf())
        binding.rvIncomingRequest.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }

        viewModel.incomingRequestLiveData.observe(viewLifecycleOwner, { data ->
            data?.let {
                requestAdapter.requests = it
                requestAdapter.notifyDataSetChanged()
            }
        })
        binding.apply {
            Glide.with(requireActivity()).load(Preferences(requireActivity()).getImage())
                .into(incomingRequestProfileImage)
                .onLoadFailed(requireContext().getDrawable(R.drawable.ic_profile))
            incomingOpenNav.setOnClickListener {
                val navDrawer: DrawerLayout = requireActivity().findViewById(R.id.drawerLayout)
                navDrawer.openDrawer(GravityCompat.START)
            }
        }



        return binding.root
    }
}