package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentLandingBinding
import live.adabe.findmyblood.utils.Preferences

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater, container, false)

        binding.apply {
            registerButton.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_landingFragment_to_signUpFragment)
            }

            loginButton.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_landingFragment_to_loginFragment)
            }
        }
        if (Preferences(requireActivity()).getIsLoggedIn()) findNavController().navigate(R.id.action_landingFragment_to_homeFragment)
        return binding.root
    }
}