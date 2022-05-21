package com.cibo.cibo.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cibo.cibo.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class ScanFragment : BaseFragment(), EasyPermissions.PermissionCallbacks {

    val TAG: String = ScanFragment::class.java.simpleName

    companion object {
        const val RC_CAMERA = 1
        const val SCANNER_FRAGMENT_TAG = "Scanner Fragment TAG"
    }

    private lateinit var beepManager: BeepManager
    private lateinit var scannerView: DecoratedBarcodeView
    private lateinit var btn_onOff: Button
    private var isScanerActive: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_scan, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scannerView = view.findViewById(R.id.QRScannerView)
        btn_onOff = view.findViewById(R.id.btn_onOff)

        val formats = mutableListOf(BarcodeFormat.QR_CODE)
        beepManager = BeepManager(requireActivity())
        scannerView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        scannerView.setStatusText("")

        btn_onOff.setOnClickListener {

            if (isScanerActive) {
                isScanerActive = false
                scannerView.pause()
                scannerView.visibility = View.GONE
            } else {
                isScanerActive = true
                scannerView.resume()
                scannerView.visibility = View.VISIBLE
            }

        }

        scannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    beepManager.isBeepEnabled = false
                    beepManager.playBeepSoundAndVibrate()

                    if (result.text == "cibo") {

                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, RestaurantFragment())
                            .addToBackStack(null)
                            .commit()
                    } else {
                        Toast.makeText(requireContext(), "Sizning QR xato", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            }
        })

        checkPermissionsAndStartQRScan()
    }

    override fun onPause() {
        super.onPause()
        scannerView.pause()
    }

    override fun onResume() {
        super.onResume()
        scannerView.resume()
    }

    private fun openScanner() {
        scannerView.resume()
    }


    //////////////////////////////////////////////

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults, this
        )
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(R.string.permission_required_dialog_title)
            .setMessage(R.string.permission_required_dialog_content)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, _ ->
                dialog.dismiss()
                checkPermissionsAndStartQRScan()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        openScanner()
    }

    @AfterPermissionGranted(RC_CAMERA)
    fun checkPermissionsAndStartQRScan() {
        val permission = Manifest.permission.CAMERA
        if (EasyPermissions.hasPermissions(requireContext(), permission)) {
            // Already have permission, do the thing
            openScanner()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, getString(R.string.camera_permission_explanation),
                RC_CAMERA, permission
            )
        }
    }

}