package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.BloodSearchAdapter
import live.adabe.findmyblood.databinding.FragmentRequestScreenBinding
import live.adabe.findmyblood.models.network.SearchRequest
import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class RequestScreenFragment : Fragment() {
    private lateinit var binding: FragmentRequestScreenBinding
    private lateinit var bloodAdapter: BloodSearchAdapter
    private lateinit var viewModel: MainViewModel

    private var bloodGroup = ""
    private var units = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRequestScreenBinding.inflate(inflater, container, false)
        bloodAdapter = BloodSearchAdapter(listOf()) {
            makeBloodRequest(it.hospital.id, it.id)
        }
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 2)
        )[MainViewModel::class.java]
        initViews()
        observeData()


        return binding.root
    }

    private fun initViews() {
        binding.apply {
            spBloodGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    bloodGroup = adapterView?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            etUnitEntries.editText?.addTextChangedListener { s ->
                if (s?.isNotEmpty()!!) units = s.toString().toInt() ?: 0
            }

            searchBloodBtn.setOnClickListener {
                if (bloodGroup.isNotEmpty()) {
                    viewModel.searchBlood(SearchRequest(bloodGroup))
                }
            }

            rvRequestScreen.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bloodAdapter
            }

            requestOpenNav.setOnClickListener {
                val navDrawer: DrawerLayout = requireActivity().findViewById(R.id.drawerLayout)
                navDrawer.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun observeData() {
        viewModel.run {
            bloodSearchLiveData.observe(viewLifecycleOwner, {
                bloodAdapter.data = it
                bloodAdapter.notifyDataSetChanged()
            })
            isRequestSuccessfulLiveData.observe(viewLifecycleOwner, { isSuccessful ->
                if (isSuccessful) {
                    findNavController().navigate(R.id.action_requestScreenFragment_to_dashboardScreenFragment)
                    isRequestSuccessfulLiveData.postValue(!isSuccessful)
                }
            })
        }
    }

    private fun makeBloodRequest(id: String, bloodId: String) {
        if (bloodGroup.isNotEmpty() && units > 0) {
            val request = BloodRequest(bloodId, bloodGroup, units)
            viewModel.makeBloodRequest(request, id)
        }
    }
}