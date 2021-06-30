package com.example.tokenmanagement.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.tokenmanagement.R
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import org.json.JSONException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QrFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QrFragment : Fragment(), EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var cardView1: CardView? = null
    var cardView2: CardView? = null
    var btnScan: Button? = null
    var btnEnterCode: Button? = null
    var btnEnter: Button? = null
    var edtCode: EditText? = null
    var tvText: TextView? = null
    var hide: Animation? = null
    var reveal: Animation? = null

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
        var view = inflater.inflate(R.layout.fragment_qr, container, false)
        retrieveValues(view)

        tvText!!.startAnimation(reveal)
        cardView2!!.startAnimation(reveal)
        tvText!!.setText("Scan QR Code Here")
        cardView2!!.visibility = View.VISIBLE

        btnScan!!.setOnClickListener {
            tvText!!.startAnimation(reveal)
            cardView1!!.startAnimation(hide)
            cardView2!!.startAnimation(reveal)

            cardView2!!.visibility = View.VISIBLE
            cardView1!!.visibility = View.GONE
            tvText!!.setText("Scan QR Code Here")
        }
        cardView2!!.setOnClickListener {
            cameraTask()
        }

        btnEnter!!.setOnClickListener {

            if (edtCode!!.text.toString().isNullOrEmpty()) {
                Toast.makeText(context, "Please enter code", Toast.LENGTH_SHORT).show()
            } else {
                var value = edtCode!!.text.toString()

                Toast.makeText(context, value, Toast.LENGTH_SHORT).show()

            }
        }
        btnEnterCode!!.setOnClickListener {
            tvText!!.startAnimation(reveal)
            cardView1!!.startAnimation(reveal)
            cardView2!!.startAnimation(hide)

            cardView2!!.visibility = View.GONE
            cardView1!!.visibility = View.VISIBLE
            tvText!!.setText("Enter QR Code Here")
        }
    return view
    }

        private fun hasCameraAccess(): Boolean? {
            return context?.let { EasyPermissions.hasPermissions(it, android.Manifest.permission.CAMERA) }
        }


        private fun cameraTask() {

            if (hasCameraAccess() == true) {

                var qrScanner = IntentIntegrator(context as Activity?)
                qrScanner.setPrompt("scan a QR code")
                qrScanner.setCameraId(0)
                qrScanner.setOrientationLocked(true)
                qrScanner.setBeepEnabled(true)
                qrScanner.captureActivity = CaptureActivity::class.java
                qrScanner.initiateScan()
            } else {
                EasyPermissions.requestPermissions(
                    this,
                    "This app needs access to your camera so you can take pictures.",
                    123,
                    android.Manifest.permission.CAMERA
                )
            }
    }



    private fun retrieveValues(view: View){
        cardView1 = view.findViewById(R.id.cardView1)
        cardView2 = view.findViewById(R.id.cardView2)
        btnScan = view.findViewById(R.id.btnScan)
        btnEnterCode = view.findViewById(R.id.btnEnterCode)
        btnEnter = view.findViewById(R.id.btnEnter)
        edtCode = view.findViewById(R.id.edtCode)
        tvText = view.findViewById(R.id.tvText)

        hide = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        reveal = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QrFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QrFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult( requestCode, resultCode, data)


        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Result Not Found", Toast.LENGTH_SHORT).show()
                edtCode!!.setText("")
                super.onActivityResult(requestCode, resultCode, data)
            } else {
                try {

                    cardView1!!.startAnimation(reveal)
                    cardView2!!.startAnimation(hide)
                    cardView1!!.visibility = View.VISIBLE
                    cardView2!!.visibility = View.GONE
                    Toast.makeText(context, result.contents.toString(), Toast.LENGTH_SHORT).show()
                    edtCode!!.setText(result.contents.toString())
                    super.onActivityResult(requestCode, resultCode, data)
                } catch (exception: JSONException) {
                    Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    edtCode!!.setText("")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        }


    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }



    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }
}