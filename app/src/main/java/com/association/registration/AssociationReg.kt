package com.association.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.*
import com.association.R
import com.association.common.Localsorage
import com.association.common.Progresdialog
import com.association.services.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AssociationReg : AppCompatActivity(), View.OnClickListener {

    lateinit var edName: EditText
    lateinit var edAddr1: EditText
    lateinit var edAddr2: EditText
    lateinit var edAddr3: EditText
    lateinit var edCity: EditText
    lateinit var edzip: EditText
    lateinit var edRegNo: EditText
    lateinit var edRegDate: EditText
    lateinit var drawer: DrawerLayout
    lateinit var ivMenu: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_association_reg)
        initView()
        callWebserviceForm()
    }

    private fun initView() {
        drawer = findViewById(R.id.drawer_layout)
        ivMenu = findViewById(R.id.ivMenu)
        ivMenu.setOnClickListener(this)
        edName = findViewById(R.id.edName)
        edAddr1 = findViewById(R.id.edAddr1)
        edAddr2 = findViewById(R.id.edAddr2)
        edAddr3 = findViewById(R.id.edAddr3)
        edCity = findViewById(R.id.edCity)
        edzip = findViewById(R.id.edZip)
        edRegNo = findViewById(R.id.edRegNo)
        edRegDate = findViewById(R.id.edRegdate)
        edRegDate.setOnClickListener(this)
        findViewById<Button>(R.id.btnSubmit).setOnClickListener(this)


    }

    private fun callWebserviceForm() {
        Progresdialog.showDialog(this, "")

        val apiInterface = RestClient.getapiclient()
        val getNowShowingMoviesResponseCall = apiInterface.getAssnLable(AssnLabReq(Localsorage.getLangcode(this), "assnregistration"))
        getNowShowingMoviesResponseCall.enqueue(object : Callback<AssnResp> {
            override fun onResponse(call: Call<AssnResp>, response: Response<AssnResp>) {
                try {
                    val storeResp = response.body()
                    Progresdialog.dismissDialog()
                    if (storeResp!!.status.statusID != null && storeResp.status.statusID.equals("1")) {

                        findViewById<TextView>(R.id.tvass).setText(storeResp.lsScreenCaption)
                        edName.setHint(storeResp.Screen_Details_List.get(0).label_text)
                        edAddr1.setHint(storeResp.Screen_Details_List.get(1).label_text)
                        edAddr2.setHint(storeResp.Screen_Details_List.get(2).label_text)
                        edAddr3.setHint(storeResp.Screen_Details_List.get(3).label_text)
                        edCity.setHint(storeResp.Screen_Details_List.get(4).label_text)
                        edzip.setHint(storeResp.Screen_Details_List.get(5).label_text)
                        edRegNo.setHint(storeResp.Screen_Details_List.get(6).label_text)
                        edRegDate.setHint(storeResp.Screen_Details_List.get(7).label_text)
                        /* findViewById<TextView>(R.id.tvName).setText(storeResp.Screen_Details_List.get(0).label_text)
                         findViewById<TextView>(R.id.tvAddr1).setText(storeResp.Screen_Details_List.get(1).label_text)
                         findViewById<TextView>(R.id.tvAddr2).setText(storeResp.Screen_Details_List.get(2).label_text)
                         findViewById<TextView>(R.id.tvAddr3).setText(storeResp.Screen_Details_List.get(3).label_text)
                         findViewById<TextView>(R.id.tvCity).setText(storeResp.Screen_Details_List.get(4).label_text)
                         findViewById<TextView>(R.id.tvZip).setText(storeResp.Screen_Details_List.get(5).label_text)

                         findViewById<TextView>(R.id.tvRegno).setText(storeResp.Screen_Details_List.get(6).label_text)
                         findViewById<TextView>(R.id.tvRegDate).setText(storeResp.Screen_Details_List.get(7).label_text)*/


                    } else {

                        Toast.makeText(applicationContext, storeResp.status.statusDescription, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<AssnResp>, t: Throwable) {
                t.printStackTrace()
                Progresdialog.dismissDialog()
                Toast.makeText(applicationContext, "Please try again..", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSubmit -> {

                if (TextUtils.isEmpty(edName.text.toString())) {
                    edName.requestFocus()
                    Progresdialog.alerts(this, "Please enter Association Name", "2")
                } else if (TextUtils.isEmpty(edAddr1.text.toString())) {
                    edAddr1.requestFocus()
                    Progresdialog.alerts(this, "Please enter Address1", "2")
                } else if (TextUtils.isEmpty(edCity.text.toString())) {
                    edCity.requestFocus()
                    Progresdialog.alerts(this, "Please enter City", "2")
                } else if (TextUtils.isEmpty(edzip.text.toString())) {
                    edzip.requestFocus()
                    Progresdialog.alerts(this, "Please enter Zip code", "2")
                } else if (TextUtils.isEmpty(edRegNo.text.toString())) {
                    edRegNo.requestFocus()
                    Progresdialog.alerts(this, "Please enter Registration No", "2")
                } else if (TextUtils.isEmpty(edRegDate.text.toString())) {
                    edRegDate.requestFocus()
                    Progresdialog.alerts(this, "Please enter Registration Date", "2")
                } else {
                    callWebserviceAssnSubmit()
                }

            }
            R.id.ivMenu -> {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                      drawer.closeDrawer(Gravity.RIGHT)
                } else {
                    drawer.openDrawer(Gravity.END)
                }
            }
            R.id.edRegdate -> {
                selectDate()
            }
        }
    }

    private fun callWebserviceAssnSubmit() {
        Progresdialog.showDialog(this, "")
        val assnSub = AssnSubReq();
        assnSub.assnname = edName.text.toString()
        assnSub.address1 = edAddr1.text.toString()
        assnSub.address2 = edAddr2.text.toString()
        assnSub.address3 = edAddr3.text.toString()
        assnSub.city = edCity.text.toString()
        assnSub.zipcode = edzip.text.toString()
        assnSub.registrationno = edRegNo.text.toString()
        assnSub.registrationdate = edRegDate.text.toString()
        val apiInterface = RestClient.getapiclient()
        val getNowShowingMoviesResponseCall = apiInterface.getAssnSubmit(assnSub)
        getNowShowingMoviesResponseCall.enqueue(object : Callback<AssnSubResp> {
            override fun onResponse(call: Call<AssnSubResp>, response: Response<AssnSubResp>) {
                try {
                    val storeResp = response.body()
                    Progresdialog.dismissDialog()
                    if (storeResp!!.statusID != null && storeResp.statusID.equals("1")) {
                        edName.setText("")
                        edName.requestFocus()
                        edAddr1.setText("")
                        edAddr2.setText("")
                        edAddr3.setText("")
                        edCity.setText("")
                        edzip.setText("")
                        edRegNo.setText("")
                        edRegDate.setText("")

                        Toast.makeText(applicationContext, storeResp.statusDescription, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, storeResp.statusDescription, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<AssnSubResp>, t: Throwable) {
                t.printStackTrace()
                Progresdialog.dismissDialog()
                Toast.makeText(applicationContext, "Please try again..", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun selectDate() {
        val c = Calendar.getInstance()
        var mYear = c.get(Calendar.YEAR)
        var mMonth = c.get(Calendar.MONTH)
        var mDay = c.get(Calendar.DAY_OF_MONTH)
        /* val currentDate = edDate.getText().toString()
         if (!currentDate.equals("", ignoreCase = true)) {
             try {
                 val dateArray = currentDate.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                 val date = dateArray[0]
                 val month = dateArray[1]
                 val year = dateArray[2]
                 mDay = Integer.parseInt(date)
                 mMonth = Integer.parseInt(month)
                 mMonth--// reducing month for calendar
                 mYear = Integer.parseInt(year)
             } catch (e: Exception) {
                 e.printStackTrace()
             }

         }
 */
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            //mDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val strdt = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
            var strDate: Date? = null
            try {
                strDate = sdf.parse(strdt)
                edRegDate.setError(null)
                if (System.currentTimeMillis() > strDate!!.getTime()) {
                    edRegDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                } else {
                    Toast.makeText(application, "Please select previous date", Toast.LENGTH_SHORT).show()
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }, mYear, mMonth, mDay)
        dpd.show()
    }
}
