package com.example.vendingmachinejp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStorePref @Inject constructor(private val context: Context) {
   companion object{
       private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("VM_Store")
       val KIOSK_ADDED = booleanPreferencesKey("kiosk_added")
       val API_KEY = booleanPreferencesKey("api_key")
       val BRANCH_ID = booleanPreferencesKey("branch_id")
       val ORG_ID = booleanPreferencesKey("org_id")
   }



    suspend fun addKiosk(added: Boolean){
        context.dataStore.edit { it[KIOSK_ADDED] = true }
    }

    suspend fun kioskAdded(): Boolean = context.dataStore.data.first()[KIOSK_ADDED] ?: false

    suspend fun addAPIKey(apiKey: String){
        context.dataStore.edit { it[API_KEY] = true }
    }

    suspend fun getAPIKey(): String = (context.dataStore.data.first()[API_KEY] ?: "") as String

    suspend fun addOrgId(orgId: String){
        context.dataStore.edit { it[ORG_ID] = true }
    }

    suspend fun getOrgId(): String = (context.dataStore.data.first()[ORG_ID] ?: "") as String

    suspend fun addBranchId(branchId: String){
        context.dataStore.edit { it[BRANCH_ID] = true }
    }

    suspend fun getBranchId(): String = (context.dataStore.data.first()[BRANCH_ID] ?: "") as String
}