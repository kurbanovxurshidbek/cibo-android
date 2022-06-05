package com.cibo.cibo.fragment

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentScanBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class ScanFragment : BaseFragment(), EasyPermissions.PermissionCallbacks {

    private val TAG: String = ScanFragment::class.java.simpleName

    private var _bn: FragmentScanBinding? = null
    private val bn get() = _bn!!

    companion object {
        const val RC_CAMERA = 1
    }

    private lateinit var beepManager: BeepManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setTransparentStatusBarColor(
            requireContext(),
            R.color.black,
            R.color.white,
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        )
        _bn = FragmentScanBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews()
        setupScanner()
    }

    private fun initViews() {
        Glide.with(requireContext())
            .load("https://images.unsplash.com/photo-1486428263684-28ec9e4f2584?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80")
            .centerCrop().into(bn.ivBackground)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
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
        /*AlertDialog.Builder(requireContext())
            .setTitle(R.string.permission_required_dialog_title)
            .setMessage(R.string.permission_required_dialog_content)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, _ ->
                dialog.dismiss()
                checkPermissionsAndStartQRScan()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()*/
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    private fun setupScanner() {
        val formats = mutableListOf(BarcodeFormat.QR_CODE)
        beepManager = BeepManager(requireActivity())
        bn.QRScannerView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        bn.QRScannerView.setStatusText("")
        bn.motionLayout.setTransitionListener(getTransitionListener())
        bn.QRScannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    beepManager.isBeepEnabled = false
                    beepManager.playBeepSoundAndVibrate()

                    if (result.text == "cibo") {
                        bn.QRScannerView.pause()
                        findNavController().navigate(R.id.action_scanFragment_to_restaurantFragment)
                    } else {
                        Toast.makeText(requireContext(), "Sizning QR xato", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            }
        })
    }

    @AfterPermissionGranted(RC_CAMERA)
    fun checkPermissionsAndStartQRScan() {
        val permission = Manifest.permission.CAMERA
        if (EasyPermissions.hasPermissions(requireContext(), permission)) {
            // Already have permission, do the thing
            bn.QRScannerView.resume()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, getString(R.string.camera_permission_explanation),
                RC_CAMERA, permission
            )
        }
    }

    private fun getTransitionListener(): MotionLayout.TransitionListener {
        return object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, startId: Int, endId: Int) {
                Log.d(TAG, "onTransitionStarted")
            }

            override fun onTransitionChange(
                p0: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                Log.d(TAG, "onTransitionChange")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                Log.d(TAG, "onTransitionCompleted")
                if (currentId == R.id.start) {
                    bn.QRScannerView.pause()
                } else {
                    checkPermissionsAndStartQRScan()
                }
            }

            override fun onTransitionTrigger(
                p0: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                Log.d(TAG, "onTransitionTrigger")
            }
        }
    }
}