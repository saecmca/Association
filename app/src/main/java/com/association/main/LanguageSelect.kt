package com.association.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.association.R
import com.association.common.Localsorage
import com.association.common.Progresdialog
import com.association.registration.AssociationReg
import com.association.registration.LangResp
import com.association.services.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguageSelect : AppCompatActivity(), View.OnClickListener {
    lateinit var lngSpin: Spinner
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnProced -> {
                var main = Intent(this@LanguageSelect, AssociationReg::class.java)
                startActivity(main)
                finish()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_select)
        findViewById<Button>(R.id.btnProced).setOnClickListener(this)
        findViewById<ImageView>(R.id.ivBack).visibility = View.GONE
        lngSpin = findViewById(R.id.spinLang)
        callStoreWebservice()
    }

    private fun callStoreWebservice() {
        Progresdialog.showDialog(this, "")

        val apiInterface = RestClient.getapiclient()
        val getNowShowingMoviesResponseCall = apiInterface.langResp
        getNowShowingMoviesResponseCall.enqueue(object : Callback<LangResp> {
            override fun onResponse(call: Call<LangResp>, response: Response<LangResp>) {
                try {
                    val storeResp = response.body()
                    Progresdialog.dismissDialog()
                    if (storeResp!!.status.statusID != null && storeResp.status.statusID.equals("1")) {

                        val arrspin = arrayListOf<String>()
                        for (i in 0 until storeResp.language_Details_List.size) {
                            arrspin.add(storeResp.language_Details_List.get(i).language_Name)
                        }
                        lngSpin.adapter = ArrayAdapter(this@LanguageSelect, android.R.layout.simple_spinner_dropdown_item, arrspin)
                        lngSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                                for (i in 0 until storeResp.language_Details_List.size) {
                                    if (storeResp.language_Details_List.get(i).language_Name.equals(parent.getItemAtPosition(pos).toString())) {
                                        Localsorage.setLangPref(this@LanguageSelect, storeResp.language_Details_List.get(i).language_Name, storeResp.language_Details_List.get(i).language_Id)

                                    }
                                }

                            }

                            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

                            }

                        }
                    } else {

                        Toast.makeText(applicationContext, storeResp.status.statusDescription, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<LangResp>, t: Throwable) {
                t.printStackTrace()
                Progresdialog.dismissDialog()
                Toast.makeText(applicationContext, "Please try again..", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
