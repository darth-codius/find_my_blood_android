package live.adabe.findmyblood.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import live.adabe.findmyblood.R
import live.adabe.findmyblood.databinding.FragmentRequestScreenBinding

class RequestScreenFragment : Fragment() {
    private lateinit var binding: FragmentRequestScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRequestScreenBinding.inflate(inflater, container, false)

        binding.spBloodGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        return binding.root
    }
}