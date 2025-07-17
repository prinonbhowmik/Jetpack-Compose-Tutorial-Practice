package com.example.vendingmachinejp.screens.videoItems.utils

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

@UnstableApi
object ExoPlayerCacheManager {
    private var cache: SimpleCache? = null

    fun getCacheFactory(context: Context): DataSource.Factory {
        if (cache == null) {
            val cacheDir = File(context.cacheDir, "media_cache")
            val evictor = LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024) // 90 MB
            val dbProvider = StandaloneDatabaseProvider(context)

            cache = SimpleCache(cacheDir, evictor, dbProvider)
        }

        val upstreamFactory = DefaultHttpDataSource.Factory()

        return CacheDataSource.Factory()
            .setCache(cache!!)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }

    fun release() {
        cache?.release()
        cache = null
    }
}
