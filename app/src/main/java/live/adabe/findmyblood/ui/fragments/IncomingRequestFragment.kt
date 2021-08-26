package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentIncomingRequestBinding

class IncomingRequestFragment : Fragment() {
    private lateinit var binding: FragmentIncomingRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIncomingRequestBinding.inflate(inflater, container, false)



        return binding.root
    }
}