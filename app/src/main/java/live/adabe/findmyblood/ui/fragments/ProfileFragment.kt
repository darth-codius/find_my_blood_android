package live.adabe.findmyblood.ui.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import live.adabe.findmyblood.databinding.FragmentProfileBinding
import live.adabe.findmyblood.models.network.BloodRequest
import live.adabe.findmyblood.models.network.UpdateRequest
import live.adabe.findmyblood.ui.fragments.ktx.InputStreamRequestBody
import live.adabe.findmyblood.utils.Status
import live.adabe.findmyblood.viewmodels.AuthViewModel
import live.adabe.findmyblood.viewmodels.MainViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var mainViewModel: MainViewModel
    private var isEditing = false
    private var isBloodEditing = false
    private val REQUEST_IMAGE_OPEN = 100
    private var imageURI: Uri? = null


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

        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity(), 2)
        )[MainViewModel::class.java]

        initViews()
        return binding.root
    }

    private fun enableDetailsInputs() {
        binding.run {
            profileAddressInput.isEnabled = isEditing
            profileMotoInput.isEnabled = isEditing
            profileNameInput.isEnabled = isEditing
            profilePhoneInput.isEnabled = isEditing
            profileStateInput.isEnabled = isEditing
        }
    }

    private fun enableBloodInput() {
        binding.run {
            profileBloodAPositive.isEnabled = isBloodEditing
            profileBloodANegative.isEnabled = isBloodEditing
            profileBloodBPositive.isEnabled = isBloodEditing
            profileBloodBNegative.isEnabled = isBloodEditing
            profileBloodAbPositive.isEnabled = isBloodEditing
            profileBloodAbNegative.isEnabled = isBloodEditing
            profileBloodOPositive.isEnabled = isBloodEditing
            profileBloodONegative.isEnabled = isBloodEditing
        }
    }

    private fun initViews() {
        binding.apply {
            editPersonalDetailsButton.setOnClickListener {
                isEditing = true
                enableDetailsInputs()
            }

            editBloodImage.setOnClickListener {
                isBloodEditing = true
                enableBloodInput()
            }

            savePersonalDetailsButton.setOnClickListener {
                val requestBodyBuilder: MultipartBody.Builder = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)

                isEditing = false
                enableDetailsInputs()

                if( imageURI != null){
                    val image = InputStreamRequestBody(
                        "multipart/form-data".toMediaTypeOrNull(),
                        requireActivity().contentResolver,
                        imageURI!!
                    )

                    requestBodyBuilder.addFormDataPart("logo", "logo", image)
                    profileStateInput.text.let {
                        if (it.isNotEmpty()){
                            requestBodyBuilder.addFormDataPart("state", it.toString())
                        }
                    }

                    profileAddressInput.text.let{
                        if (it.isNotEmpty()){
                            requestBodyBuilder.addFormDataPart("address", it.toString())
                        }
                    }

                    profileMotoInput.text.let {
                        if (it.isNotEmpty()){
                            requestBodyBuilder.addFormDataPart("motto", it.toString())
                        }
                    }

                    profilePhoneInput.text.let {
                        if (it.isNotEmpty()){
                            requestBodyBuilder.addFormDataPart("phoneNumber", it.toString())
                        }
                    }
                    val requestbody: MultipartBody = requestBodyBuilder.build()
                    viewModel.updateHospital(requestbody)
                }else{
                    Toast.makeText(requireContext(), "Please add image!", Toast.LENGTH_LONG).show()
                }
            }

            saveBloodImage.setOnClickListener {
                isBloodEditing = false
                enableBloodInput()
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "A+",
                        profileBloodAPositive.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "A-",
                        profileBloodANegative.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "B+",
                        profileBloodBPositive.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "B-",
                        profileBloodBNegative.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "AB+",
                        profileBloodAbPositive.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "AB-",
                        profileBloodAbNegative.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "O+",
                        profileBloodOPositive.text.toString().toInt()
                    )
                )
                mainViewModel.addBlood(
                    BloodRequest(
                        bloodGroup = "O-",
                        profileBloodONegative.text.toString().toInt()
                    )
                )
            }

            viewModel.updateResponseResource.observe(viewLifecycleOwner, { hospitalResource ->
                if (hospitalResource.status == Status.SUCCESS) {
                    binding.run {
                        profileNameInput.setText(hospitalResource.data?.name.toString())
                        profileMotoInput.setText(hospitalResource.data?.motto.toString())
                        profileAddressInput.setText(hospitalResource.data?.address.toString())
                        profilePhoneInput.setText(hospitalResource.data?.phoneNumber.toString())
                    }
                }
            })

            uploadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    addCategory(Intent.CATEGORY_OPENABLE)
                }
                // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
                startActivityForResult(intent, REQUEST_IMAGE_OPEN)
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            data.data?.apply {
                imageURI = this
                Glide.with(requireContext())
                    .load(this)
                    .into(binding.profileImageHolder)
            }
        }else Toast.makeText(requireContext(), "Failed to get image!", Toast.LENGTH_LONG ).show()
    }

}