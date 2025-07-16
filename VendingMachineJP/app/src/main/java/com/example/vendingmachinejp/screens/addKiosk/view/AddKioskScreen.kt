package com.example.vendingmachinejp.screens.addKiosk.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendingmachinejp.base.AppConstants
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.screens.addKiosk.viewmodel.AddKioskViewmodel
import com.example.vendingmachinejp.utils.DataStorePref
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddKioskScreen(navController: NavController,viewmodel: AddKioskViewmodel = hiltViewModel()) {
    val kioskData = viewmodel.adData
    val isLoading = viewmodel.isLoading
    val error = viewmodel.errorMessage
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    var value: String by remember { mutableStateOf("") }

    fun callAddKiosk() {
        if (value.isBlank()){
            Toast.makeText(context,"Enter valid restaurant Id", Toast.LENGTH_SHORT).show()

        }else{

            val jsonObject = JSONObject().apply {
                put("OrganizationCode",value.toString())
                put("KioskId",value.toString())
                put("TenantId", AppConstants.TENANT_ID)
            }

            val body = "$jsonObject".toRequestBody("application/json; charset=utf-8".toMediaType())

            viewmodel.getAddKiosk(body)
        }
    }

    when{
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier
                    .height(40.dp)
                )
            }
        }

        error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = error, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { callAddKiosk()}) {
                    Text("Retry")
                }
            }
        }

        kioskData != null -> {
            Log.d("AdList", "SplashScreen: ${Gson().toJson(kioskData.data)}")

            val pref = DataStorePref(context)

            coroutineScope.launch {
                pref.addKiosk(true)
                pref.addAPIKey(kioskData.data?.apiKey.toString())
                pref.addBranchId(kioskData.data?.branchUUID.toString())
                pref.addOrgId(kioskData.data?.organizationId.toString())

                navController.navigate(Screen.splash.route){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ){


        TextField(
            value = value,
            onValueChange = { value = it },

            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = Color.Green,
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(color = Color.Red),
            label =  { Text(text = "Enter Restaurant ID", color = Color.Black)},

            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { callAddKiosk()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Clear"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(color = Color.White),
        )
    }





}


