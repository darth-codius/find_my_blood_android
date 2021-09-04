package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentHomeBinding
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val factory = ViewModelFactory(requireActivity(), 2)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        binding.btnMakeRequest.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_requestScreenFragment)
        }

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.apply {
            homeHospitalName.text = Preferences(requireActivity()).getHospitalName()

            viewModel.bloodLiveData.observe(viewLifecycleOwner, { blood ->
                for (item in blood) {
                    when (item.bloodGroup) {
                        "A+" -> bloodGroupAPositive.text = item.units.toString()
                        "A-" -> bloodGroupANegative.text = item.units.toString()
                        "B+" -> bloodGroupBPositive.text = item.units.toString()
                        "B-" -> bloodGroupBNegative.text = item.units.toString()
                        "AB+" -> bloodGroupABPositive.text = item.units.toString()
                        "AB-" -> bloodGroupABNegative.text = item.units.toString()
                        "O+" -> bloodGroupOPositive.text = item.units.toString()
                        "O-" -> bloodGroupONegative.text = item.units.toString()
                    }
                }
            })
            viewModel.hospitalInfoLiveData.observe(viewLifecycleOwner, { hospital ->
                hospital?.let {

                }
            })
            ivLogo.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            Glide.with(requireActivity()).load(Preferences(requireActivity()).getImage())
                .into(ivLogo)
                .onLoadFailed(requireContext().getDrawable(R.drawable.ic_profile))
        }

    }
}