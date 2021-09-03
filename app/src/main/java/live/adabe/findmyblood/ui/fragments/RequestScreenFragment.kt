package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import live.adabe.findmyblood.R
import live.adabe.findmyblood.adapters.BloodSearchAdapter
import live.adabe.findmyblood.databinding.FragmentRequestScreenBinding
import live.adabe.findmyblood.models.network.SearchRequest
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class RequestScreenFragment : Fragment() {
    private lateinit var binding: FragmentRequestScreenBinding
    private lateinit var bloodAdapter: BloodSearchAdapter
    private lateinit var viewModel: MainViewModel

    private var bloodGroup = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRequestScreenBinding.inflate(inflater, container, false)
        bloodAdapter = BloodSearchAdapter(listOf()) {
            Toast.makeText(requireContext(), "You Clicked ${it.hospital.name}", Toast.LENGTH_LONG)
                .show()
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

            searchBloodBtn.setOnClickListener {
                if (bloodGroup.isNotEmpty()) {
                    viewModel.searchBlood(SearchRequest(bloodGroup))
                }
            }

            rvRequestScreen.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bloodAdapter
            }

        }
    }

    private fun observeData() {
        viewModel.run {
            bloodSearchLiveData.observe(viewLifecycleOwner, {
                bloodAdapter.data = it
                bloodAdapter.notifyDataSetChanged()
            })
        }
    }
}