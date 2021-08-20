package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentSignUpBinding
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.viewmodels.AuthViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        //viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.apply {

            btnRegister.setOnClickListener {
                addNewHospital()
            }

            tvLogin.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }

        return binding.root
    }

    private fun addNewHospital() {
        binding.apply {
            val nameOfHospital = textInputHospital.editText?.text.toString()
            val email = textInputEmail.editText?.text.toString()
            val password = textInputPassword.editText?.text.toString()
            val confirmPassword = textInputConfirmPassword.editText?.text.toString()

            if (inputCheck(nameOfHospital, email, password, confirmPassword)) {
                val signUpRequest = SignUpRequest(nameOfHospital, email, password, confirmPassword)

                //viewModel.signUpHospital(signUpRequest)
            } else {
                Toast.makeText(requireActivity(), "Please fill out all fields", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun inputCheck(
        nameOfHospital: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return nameOfHospital.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}