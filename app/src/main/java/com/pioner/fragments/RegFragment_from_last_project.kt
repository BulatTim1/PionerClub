package com.pioner.fragments
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
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
//import java.util.*
//
//class RegFragment : Fragment() {
//    private var name: EditText? = null
//    private var lastname: EditText? = null
//    private var email: EditText? = null
//    private var pass: EditText? = null
//    private var auth: FirebaseAuth? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val root: View = inflater.inflate(R.layout.fragment_reg, container, false)
//        auth = FirebaseAuth.getInstance()
//        name = root.findViewById<EditText>(R.id.reg_name)
//        lastname = root.findViewById<EditText>(R.id.reg_lastname)
//        email = root.findViewById<EditText>(R.id.reg_email)
//        pass = root.findViewById<EditText>(R.id.reg_password)
//        val bReg: Button = root.findViewById<Button>(R.id.reg_reg)
//        val bToLog: Button = root.findViewById<Button>(R.id.reg_back)
////        val sp: SharedPreferences =
////            requireActivity().getSharedPreferences("APP_PREFERNCES", Context.MODE_PRIVATE)
//        bReg.setOnClickListener(View.OnClickListener { v: View? -> postDataToSQLite() })
//        bToLog.setOnClickListener(View.OnClickListener { v: View? ->
//            val fm = parentFragmentManager
//            val ft = fm.beginTransaction()
//            val fragment = LoginFragment()
//            ft.replace(R.id.login_host, fragment)
//            ft.commit()
//        })
//        return root
//    }
//
//    private fun postDataToSQLite() {
//        if (name.text.isEmpty() || lastname.text.isEmpty()) {
//            Toast.makeText(context, "Неверное имя", Toast.LENGTH_LONG).show()
//        } else if (!isEmail(email)) {
//            Toast.makeText(context, "Неправильный email", Toast.LENGTH_LONG).show()
//        } else if (pass.text.length < 8) {
//            Toast.makeText(context, "Неверный пароль", Toast.LENGTH_LONG).show()
//        } else {
//            auth.createUserWithEmailAndPassword(
//                email.getText().toString(),
//                pass.getText().toString()
//            )
//                .addOnCompleteListener(
//                    requireActivity(),
//                    OnCompleteListener<AuthResult> { task: Task<AuthResult> ->
//                        if (task.isSuccessful) {
//                            val token: String = task.result!!.user!!.uid
//                            Log.d("Reg", "postDataToSQLite: $token")
////                        requireActivity().getSharedPreferences("token", Context.MODE_PRIVATE).edit()
////                            .putString("token", token).apply()
////                        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
////                        val doc: MutableMap<String, Any> = DBHelper.getDB()
////                        doc["username"] = username.getText().toString()
////                        doc["email"] = email.getText().toString()
////                        db.collection("users").document(token).set(doc)
////                            .addOnCompleteListener { task1 ->
////                                if (task1.isSuccessful()) {
////                                    Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
////                                }
////                            }
//                            emptyInputEditText()
//                            val fm = parentFragmentManager
//                            val ft = fm.beginTransaction()
////                        val fragment = MainFragment()
////                        ft.replace(R.id.host_fragment, fragment)
////                        ft.commit()
//                        } else {
//                            Toast.makeText(
//                                context, Objects.requireNonNull(task.exception).localizedMessage,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    })
//        }
//    }
//
//    private fun emptyInputEditText() {
//        name.text.clear()
//        lastname.text.clear()
//        email.text.clear()
//        pass.text.clear()
//    }
//
//    private fun isEmail(email: EditText?): Boolean {
//        val value: String = email.getText().toString()
//        return value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
//    }
//}