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
import live.adabe.findmyblood.databinding.FragmentLoginBinding
import live.adabe.findmyblood.models.network.login.LoginRequest
import live.adabe.findmyblood.viewmodels.AuthViewModel
import live.adabe.findmyblood.viewmodels.ViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        factory = ViewModelFactory(requireActivity(), 1)
        viewModel = ViewModelProvider(requireActivity(), factory)[AuthViewModel::class.java]

        binding.apply {

            loginSubmitButton.setOnClickListener {
                loginHospital()
            }

            goToRegister.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }

        viewModel.isLoginSuccessful.observe(viewLifecycleOwner, { isSuccessful ->
            if (isSuccessful) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                makeToast("Login failed! \nPlease try again")
            }
        })

        return binding.root
    }

    private fun loginHospital() {
        binding.apply {
            val email = textInputLoginEmail.editText?.text.toString()
            val password = textInputLoginPassword.editText?.text.toString()

            if (inputCheck(email, password)) {
                val loginRequest = LoginRequest(email, password)

                viewModel.loginHospital(loginRequest)
            } else {
                makeToast("Please fill out all fields")
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG)
            .show()
    }

    private fun inputCheck(
        email: String,
        password: String,
    ): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}