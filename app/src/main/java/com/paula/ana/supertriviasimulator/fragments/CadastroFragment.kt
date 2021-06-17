package com.paula.ana.supertriviasimulator.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.UserActivity
import com.paula.ana.supertriviasimulator.dao.UsuarioDAO
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioRegistrar
import kotlinx.android.synthetic.main.fragment_cadastro.view.*

class CadastroFragment : Fragment() {
    private val dao = UsuarioDAO()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =   inflater.inflate(R.layout.fragment_cadastro, container, false)
        view.tv_login4.setOnClickListener{openLogin()}
        view.btn_register4.setOnClickListener{
            if(TextUtils.isEmpty(view.et_register_email4.text.toString().trim() { it <= ' '})){
                alert(R.string.et_email);

            }else if(TextUtils.isEmpty(view.et_register_password4.text.toString().trim() { it <= ' '})){
                alert(R.string.et_password);

            }else if(TextUtils.isEmpty(view.et_register_name4.text.toString().trim() { it <= ' '})){
                alert(R.string.et_name);

            }else{
                val email: String = view.et_register_email4.text.toString().trim { it <= ' '}
                val password: String = view.et_register_password4.text.toString().trim { it <= ' '}
                val name: String = view.et_register_name4.text.toString().trim { it <= ' '}
                dao.insert(UsuarioRegistrar(email, name, password)){ usuario ->

                    if(usuario.message==null){
                        val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)
                        if (sharedPref != null) {
                            with(sharedPref.edit()) {
                                putString("token", usuario.data!!.user.token)
                                putString("nome", usuario.data!!.user.name)
                                commit()
                            }
                        }
                        alert(R.string.alert_login_sucess);
                        val intent = Intent(activity, UserActivity::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(activity, usuario.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return view;
    }
    private fun alert(text: Int){
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

    }
    private fun openLogin(){
        findNavController().navigate(R.id.navigateToLogin)
    }


}