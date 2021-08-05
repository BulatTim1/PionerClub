package com.pioner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pioner.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccessRecoveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccessRecoveryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_access_recovery, container, false)
        val return_btn : Button = root.findViewById(R.id.return_log_recovery_btn)
        val continue_btn : Button = root.findViewById(R.id.continue_recovery_email_btn)
        // Inflate the layout for this fragment
        return_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.login_host, LoginFragment())
                .commit()
        }
        continue_btn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.login_host, CodeRecoveryFragment())
                .commit()
        }
        return inflater.inflate(R.layout.fragment_access_recovery, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccessRecoveryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccessRecoveryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}