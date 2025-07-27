package com.example.vendingmachinejp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.vendingmachinejp.screens.splash.model.Language
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePref @Inject constructor(private val context: Context) {
   companion object{
       private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("VM_Store")
       val KIOSK_ADDED = booleanPreferencesKey("kiosk_added")
       val API_KEY = stringPreferencesKey("api_key")
       val BRANCH_ID = stringPreferencesKey("branch_id")
       val ORG_ID = stringPreferencesKey("org_id")
       val TENANT_ID = stringPreferencesKey("tenant_id")
       val SUPPORT_NAME = stringPreferencesKey("support_name")
       val SUPPORT_CONTACT = stringPreferencesKey("support_contact")
       val CURRENCY = stringPreferencesKey("currency")
       val CLOUD_PROVIDER = stringPreferencesKey("cloud_provider")
       val TERMINAL_ID = stringPreferencesKey("terminal_id")
       val LANGUAGE = stringPreferencesKey("language")
       val LANGUAGE_LIST = stringPreferencesKey("language_list")
   }



    suspend fun addKiosk(added: Boolean){
        context.dataStore.edit { it[KIOSK_ADDED] = true }
    }

    suspend fun kioskAdded(): Boolean = context.dataStore.data.first()[KIOSK_ADDED] ?: false

    suspend fun addAPIKey(apiKey: String){
        context.dataStore.edit { it[API_KEY] = apiKey }
    }

    suspend fun getAPIKey(): String = (context.dataStore.data.first()[API_KEY] ?: "") as String

    suspend fun addOrgId(orgId: String){
        context.dataStore.edit { it[ORG_ID] = orgId }
    }

    suspend fun getOrgId(): String = (context.dataStore.data.first()[ORG_ID] ?: "") as String

    suspend fun addBranchId(branchId: String){
        context.dataStore.edit { it[BRANCH_ID] = branchId }
    }

    suspend fun getBranchId(): String = (context.dataStore.data.first()[BRANCH_ID] ?: "") as String

    suspend fun addTenantId(tenantId: String){
        context.dataStore.edit { it[TENANT_ID] = tenantId }
    }

    suspend fun getTenantId(): String = (context.dataStore.data.first()[TENANT_ID] ?: "") as String

    suspend fun addSupportName(supportName: String){
        context.dataStore.edit { it[SUPPORT_NAME] = supportName }
    }

    suspend fun getSupportName(): String = (context.dataStore.data.first()[SUPPORT_NAME] ?: "") as String

    suspend fun addSupportContact(supportNo: String){
        context.dataStore.edit { it[SUPPORT_CONTACT] = supportNo }
    }

    suspend fun getSupportContact(): String = (context.dataStore.data.first()[SUPPORT_CONTACT] ?: "") as String

    suspend fun addCloudProvider(cloudProvider: String){
        context.dataStore.edit { it[CLOUD_PROVIDER] = cloudProvider }
    }

    suspend fun getCloudProvider(): String = (context.dataStore.data.first()[CLOUD_PROVIDER] ?: "") as String

    suspend fun addTerminalId(terminalId: String){
        context.dataStore.edit { it[TERMINAL_ID] = terminalId }
    }

    suspend fun getTerminalId(): String = (context.dataStore.data.first()[TERMINAL_ID] ?: "") as String

    suspend fun addCurrency(currency: String){
        context.dataStore.edit { it[CURRENCY] = currency }
    }

    suspend fun getCurrency(): String = (context.dataStore.data.first()[CURRENCY] ?: "") as String

    suspend fun addLanguage(langugage: String){
        context.dataStore.edit { it[LANGUAGE] = langugage }
    }

    suspend fun getLanguage(): String = (context.dataStore.data.first()[LANGUAGE] ?: "") as String


    suspend fun saveLangList(list: List<Language>) {
        val json = Gson().toJson(list)
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_LIST] = json
        }
    }


    fun getList(): Flow<List<Language>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[LANGUAGE_LIST] ?: "[]"
            Gson().fromJson(json, object : TypeToken<List<Language>>() {}.type)
        }
    }


}