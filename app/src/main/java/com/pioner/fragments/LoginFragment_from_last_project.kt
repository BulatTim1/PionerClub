package com.pioner.fragments
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Patterns
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import com.google.android.gms.tasks.OnCompleteListener
//import com.pioner.R
//import com.google.android.gms.tasks.Task
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import org.json.JSONArray
//
//class LoginFragment : Fragment() {
//    private var email: EditText? = null
//    private var pass: EditText? = null
//    private var auth: FirebaseAuth? = null
//    private val edt: SharedPreferences.Editor? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val root: View = inflater.inflate(R.layout.fragment_login, container, false)
//        email = root.findViewById<EditText>(R.id.email)
//        pass = root.findViewById<EditText>(R.id.password)
//        val bLog: Button = root.findViewById<Button>(R.id.bLog)
//        val bToReg: Button = root.findViewById<Button>(R.id.bRegFLog)
//        val bAnom: Button = root.findViewById<Button>(R.id.bGuest)
//        auth = FirebaseAuth.getInstance()
//        val sp: SharedPreferences =
//            requireActivity().getSharedPreferences("APP_PREFERNCESS", Context.MODE_PRIVATE)
//        bLog.setOnClickListener(label@ View.OnClickListener { v: View? ->
//            if (!isEmail(email)) {
//                Toast.makeText(context, "Неправильная почта!", Toast.LENGTH_LONG).show()
//                return@label
//            }
//            auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
//                .addOnCompleteListener(
//                    requireActivity(),
//                    OnCompleteListener<AuthResult> { task: Task<AuthResult> ->
//                        if (task.isSuccessful()) {
//                            val token: String = task.getResult().getUser().getUid()
//                            requireActivity().getSharedPreferences("token", Context.MODE_PRIVATE)
//                                .edit().putString("token", token).apply()
//                            val user = User()
//                            try {
//                                val map: Map<String, Any> = DBHelper.getDB(context)
//                                user.setName(map["username"].toString())
//                                user.setEmail(map["email"].toString())
//                                user.setMed(JSONArray(map["med"].toString()))
//                                Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
//                            } catch (e: Exception) {
//                                e.printStackTrace()
//                            }
//                            emptyInputEditText()
//                            parentFragmentManager.beginTransaction()
//                                .replace(R.id.host_fragment, MainFragment()).commit()
//                        } else {
//                            Toast.makeText(context, "Не удалось войти", Toast.LENGTH_LONG).show()
//                        }
//                    })
//        })
//        bToReg.setOnClickListener(View.OnClickListener { v: View? ->
//            parentFragmentManager.beginTransaction().replace(R.id.host_fragment, RegFragment())
//                .addToBackStack("").commit()
//        })
//        bAnom.setOnClickListener(View.OnClickListener { v: View? ->
//            auth.signInAnonymously()
//                .addOnSuccessListener(OnSuccessListener<AuthResult> { task: AuthResult ->
//                    val token: String = task.getUser().getUid()
//                    requireContext().getSharedPreferences("token", Context.MODE_PRIVATE).edit()
//                        .putString("token", token).apply()
//                })
//            parentFragmentManager.beginTransaction().replace(R.id.host_fragment, MainFragment())
//                .commit()
//        })
//        return root
//    }
//
//    private fun emptyInputEditText() {
//        email.setText(null)
//        pass.setText(null)
//    }
//
//    fun isEmail(email: EditText?): Boolean {
//        val value: String = email.getText().toString()
//        return !value.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
//    }
//}