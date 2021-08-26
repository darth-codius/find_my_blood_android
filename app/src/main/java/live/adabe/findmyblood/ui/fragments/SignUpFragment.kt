package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentSignUpBinding
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.viewmodels.AuthViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        factory = ViewModelFactory(requireActivity(), 1)
        viewModel = ViewModelProvider(requireActivity(), factory)[AuthViewModel::class.java]

        binding.apply {
            btnRegister.setOnClickListener {
                addNewHospital()
            }

            tvLogin.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }

        /*Observes liveData and
           checks if signUp is successful and navigates to login page
         */
        viewModel.isSignUpSuccessful.observe(viewLifecycleOwner, { isSuccessful ->
            if (isSuccessful) {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                makeToast("Sign up Failed! \nPlease try again")
            }
        })

        return binding.root
    }

    //Function for adding new hospital
    private fun addNewHospital() {
        binding.apply {
            val nameOfHospital = textInputHospital.editText?.text.toString()
            val email = textInputEmail.editText?.text.toString()
            val password = textInputPassword.editText?.text.toString()
            val confirmPassword = textInputConfirmPassword.editText?.text.toString()

            //InputCheck function called to check if the textFields are empty
            if (inputCheck(nameOfHospital, email, password, confirmPassword)) {
                val signUpRequest = SignUpRequest(nameOfHospital, email, password, confirmPassword)

                //Adds new hospital
                viewModel.signUpHospital(signUpRequest)
            } else {
                makeToast("Please fill out all fields")
            }
        }
    }

    //Function for making toast message
    private fun makeToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG)
            .show()
    }

    //This function checks if the textFields are empty
    private fun inputCheck(
        nameOfHospital: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return nameOfHospital.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}