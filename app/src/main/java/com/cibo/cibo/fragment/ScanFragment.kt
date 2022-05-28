package com.cibo.cibo.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentScanBinding
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

    private var _bn: FragmentScanBinding? = null
    private val bn get() = _bn!!

    companion object {
        const val RC_CAMERA = 1
        const val SCANNER_FRAGMENT_TAG = "Scanner Fragment TAG"
    }

    private lateinit var beepManager: BeepManager
    private lateinit var ivPreview: ImageView
    private var isScannerActive: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bn = FragmentScanBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPreview = bn.ivPreview
        Glide.with(requireContext()).load("https://me-menu.com/public/static/qr-scan.jpg")
            .into(ivPreview)

        val formats = mutableListOf(BarcodeFormat.QR_CODE)
        beepManager = BeepManager(requireActivity())
        bn.QRScannerView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        bn.QRScannerView.setStatusText("")


        bn.btnOnOff.setOnClickListener {
            showQR()
        }

        bn.QRScannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    beepManager.isBeepEnabled = false
                    beepManager.playBeepSoundAndVibrate()

                    if (result.text == "cibo") {
                        findNavController().navigate(R.id.actionOpenRestaurantFragment)
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
        showQR()
    }

    override fun onResume() {
        super.onResume()
//        changeStatusBar(R.color.black, R.color.white)
        showQR()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

    private fun openScanner() {
        bn.QRScannerView.resume()
    }


    private fun showQR() {
        if (isScannerActive) {
            isScannerActive = false
            bn.QRScannerView.resume()
            bn.QRScannerView.visibility = View.VISIBLE
            ivPreview.visibility = View.GONE
            bn.btnOnOff.text = "Cancel"
        } else {
            isScannerActive = true
            bn.QRScannerView.pause()
            bn.QRScannerView.visibility = View.GONE
            ivPreview.visibility = View.VISIBLE
            bn.btnOnOff.text = "Let's Scanning"
        }
    }

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