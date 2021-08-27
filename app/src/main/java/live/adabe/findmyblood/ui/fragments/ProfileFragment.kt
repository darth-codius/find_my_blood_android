package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentProfileBinding
import live.adabe.findmyblood.models.network.UpdateRequest
import live.adabe.findmyblood.utils.Status
import live.adabe.findmyblood.viewmodels.AuthViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: AuthViewModel

    private var isEditing = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 1)
        )[AuthViewModel::class.java]
        initViews()
        return binding.root
    }

    private fun enableDetailsInputs() {
        binding.run {
            profileAddressInput.isEnabled = isEditing
            profileEmailInput.isEnabled = isEditing
            profileNameInput.isEnabled = isEditing
            profilePhoneInput.isEnabled = isEditing
            profileMottoInput.isEnabled = isEditing
        }
    }

    private fun initViews() {
        binding.apply {
            editPersonalDetailsButton.setOnClickListener {
                isEditing = true
                enableDetailsInputs()
            }

            savePersonalDetailsButton.setOnClickListener {
                isEditing = false
                enableDetailsInputs()
                val updateRequest = UpdateRequest(
                    phoneNumber = profilePhoneInput.text.toString(),
                    address = profileAddressInput.text.toString(),
                    motto = profileMottoInput.text.toString(),
                    state = "Delta",
                    name = profileNameInput.text.toString()
                )
                viewModel.updateHospital(updateRequest)
            }

            viewModel.updateResponseResource.observe(viewLifecycleOwner, { hospitalResource ->
                if (hospitalResource.status == Status.SUCCESS) {
                    binding.run {
                        profileNameInput.setText(hospitalResource.data?.name.toString())
                        profileMottoInput.setText(hospitalResource.data?.motto.toString())
                        profileAddressInput.setText(hospitalResource.data?.address.toString())
                        profilePhoneInput.setText(hospitalResource.data?.phoneNumber.toString())
                    }
                }
            })
        }
    }

}