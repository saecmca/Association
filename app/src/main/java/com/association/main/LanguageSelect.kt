package com.association.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.association.R
import com.association.common.Localsorage
import com.association.common.Progresdialog
import com.association.common.RecyclerItemClickListener
import com.association.registration.AssociationReg
import com.association.registration.LangResp
import com.association.services.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguageSelect : AppCompatActivity(), View.OnClickListener {
    lateinit var rclview: RecyclerView
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

        rclview = findViewById(R.id.rclv)
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
                        rclview.layoutManager = LinearLayoutManager(this@LanguageSelect, LinearLayoutManager.VERTICAL, false)
                        var langadap = LanguageAdapter(this@LanguageSelect, storeResp.language_Details_List)
                        rclview.adapter = langadap
                        Localsorage.setLangPref(this@LanguageSelect, storeResp.language_Details_List.get(0).language_Name, storeResp.language_Details_List.get(0).language_Id)
                        rclview.addOnItemTouchListener(RecyclerItemClickListener(this@LanguageSelect, object : RecyclerItemClickListener.OnItemClickListener {
                            override fun onItemClick(view: View, position: Int) {
                                langadap.setSelectedIndex(position)

                                Localsorage.setLangPref(this@LanguageSelect, storeResp.language_Details_List.get(position).language_Name, storeResp.language_Details_List.get(position).language_Id)

                            }
                        }))

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
