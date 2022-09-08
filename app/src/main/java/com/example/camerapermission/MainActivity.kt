package com.example.camerapermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    companion object {
        private const val PERMISSION_REQUEST_CAMERA = 0
    }

    private val btnOpenCamera: Button by lazy { findViewById(R.id.btnOpenCamera) }
    private val layoutMain: ConstraintLayout by lazy { findViewById(R.id.layoutMain) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnOpenCamera.setOnClickListener {
            if (checkSelfPermissionCompat(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Камера доступна для пользования", Toast.LENGTH_LONG).show()
                startCamera()
            } else {
                requestCameraPermission()
            }
        }

    }

    private fun requestCameraPermission() {

        if (shouldShowRequestPermissionRationaleCompat(android.Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Нет доступа к камере", Toast.LENGTH_LONG).show()
            requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        } else Toast.makeText(this, "Запрос разрешения доступа к камере", Toast.LENGTH_LONG).show()
        requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)

    }

    private fun startCamera() {
        Log.d("Debug1", "open camera")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Камера доступна для пользования", Toast.LENGTH_LONG).show()
                startCamera()
            } else {
                Toast.makeText(this, "Нет доступа к камере", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionsCompat(
    permissionsArray: Array<String>,
    requestCode: Int
) {
    ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
}