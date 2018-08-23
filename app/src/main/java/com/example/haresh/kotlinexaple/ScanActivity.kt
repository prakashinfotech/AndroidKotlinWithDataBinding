package com.example.haresh.kotlinexaple

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.haresh.kotlinexaple.databinding.ScanActivityBinding
import com.scandit.barcodepicker.*
import com.scandit.recognition.Barcode
import java.util.*

/**
 * Created by haresh
on 07-08-2018.
 */


/**
 * Created by Ami Govani on 03-05-2017.
 */

class ScanActivity : AppCompatActivity(), OnScanListener {
    private val CAMERA_PERMISSION_REQUEST = 0
    // The main object for recognizing and displaying barcodes.
    private var mBarcodePicker: BarcodePicker? = null
    private var mDeniedCameraAccess = false
    private var mPaused = true
    private val mToast: Toast? = null
    internal var IntentString: String? = null
    private var tvCartCnt: TextView? = null
    private var tvShoppingListCnt: TextView? = null
    internal lateinit var barcode: LinearLayout

    lateinit var binding: ScanActivityBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.scan_activity)
        ScanditLicense.setAppKey("Add Your Scandit Key")
        initializeAndStartBarcodeScanning()


    }

    private fun initializeAndStartBarcodeScanning() {

        val settings = ScanSettings.create()

        val symbologiesToEnable = intArrayOf(Barcode.SYMBOLOGY_EAN13, Barcode.SYMBOLOGY_EAN8, Barcode.SYMBOLOGY_UPCA, Barcode.SYMBOLOGY_UPCE, Barcode.SYMBOLOGY_CODE39, Barcode.SYMBOLOGY_INTERLEAVED_2_OF_5)

        for (sym in symbologiesToEnable) {
            settings.setSymbologyEnabled(sym, true)
        }
        settings.getSymbologySettings(Barcode.SYMBOLOGY_EAN13).setExtensionEnabled("relaxed_sharp_quiet_zone_check", true)

        val symSettings = settings.getSymbologySettings(Barcode.SYMBOLOGY_CODE39)
        val activeSymbolCounts = shortArrayOf(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        symSettings.setActiveSymbolCounts(activeSymbolCounts)
        // For details on defaults and how to calculate the symbol counts for each symbology, take
        // a look at http://docs.scandit.com/stable/c_api/symbologies.html.
        // Prefer the back-facing camera, is there is any.
        settings.setCameraFacingPreference(ScanSettings.CAMERA_FACING_BACK)

        // Some Android 2.3+ devices do not support rotated camera feeds. On these devices, the
        // barcode picker emulates portrait mode by rotating the scan UI.
        val emulatePortraitMode = !BarcodePicker.canRunPortraitPicker()
        if (emulatePortraitMode) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }


        val picker = BarcodePicker(this, settings)

        // Register listener, in order to be notified about relevant events
        // (e.g. a successfully scanned bar code).

        mBarcodePicker = picker
        mBarcodePicker!!.setOnScanListener(this)

        mBarcodePicker!!.getOverlayView().setTorchEnabled(false)
        binding.barcode.addView(picker)

        // setContentView(R.layout.scanning_activity);
        //mBarcodePicker.addView(picker);
        // setContentView(picker);
    }

    override fun onPause() {
        super.onPause()
        mBarcodePicker!!.stopScanning()
        mPaused = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun grantCameraPermissionsThenStartScanning() {
        if (this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (mDeniedCameraAccess == false) {
                // It's pretty clear for why the camera is required. We don't need to give a
                // detailed reason.
                this.requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST)
            }

        } else {
            // We already have the permission.
            mBarcodePicker!!.startScanning()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mDeniedCameraAccess = false
                if (!mPaused) {
                    mBarcodePicker!!.startScanning()
                }
            } else {
                mDeniedCameraAccess = true
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
        mPaused = false
        // Handle permissions for Marshmallow and onwards...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantCameraPermissionsThenStartScanning()
        } else {
            // Once the activity is in the foreground again, restart scanning.
            mBarcodePicker!!.startScanning()
        }
    }

    override fun didScan(scanSession: ScanSession) {
        mBarcodePicker!!.pauseScanning()
        var message = ""
        var SymbologyCode = ""
        for (code in scanSession.getNewlyRecognizedCodes()) {
            val data = code.getData()
            // Truncate code to certain length.
            var cleanData = data
            if (data.length > 30) {
                cleanData = data.substring(0, 25) + "[...]"
            }
            if (message.length > 0) {
                message += "\n\n\n"
            }
            message += cleanData
            Log.e("Msg", message)
            // message += "\n\n(" + code.getSymbologyName().toUpperCase(Locale.US) + ")";
            // Log.e("Msg2",message);

            SymbologyCode = code.getSymbologyName().toUpperCase(Locale.US)
            Log.e("SymbologyCode", SymbologyCode)
        }
        mToast?.cancel()

        if (SymbologyCode.equals("EAN8", ignoreCase = true) || SymbologyCode.equals("UPCA", ignoreCase = true) || SymbologyCode.equals("EAN13", ignoreCase = true)
                || SymbologyCode.equals("UPC12", ignoreCase = true) || SymbologyCode.equals("UPCE", ignoreCase = true) || SymbologyCode.equals("ITF", ignoreCase = true) || SymbologyCode.equals("Code39", ignoreCase = true)) {

            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show();
        }

        getCallback()
    }

    override fun onBackPressed() {
        mBarcodePicker!!.stopScanning()

        finish()
    }

    private fun getCallback() {
        val mIntent = Intent()
        setResult(Activity.RESULT_OK, mIntent)
        finish()
    }


}
