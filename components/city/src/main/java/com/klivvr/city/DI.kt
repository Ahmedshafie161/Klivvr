package com.klivvr.city

import android.content.Context
import com.klivvr.city.data.CityRepoImp
import com.klivvr.city.data.dataSource.CityIDataSource
import com.klivvr.city.data.dataSource.CityLocalDataSource
import com.klivvr.city.domain.CityIRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CityModule {

    @Provides
    @Singleton
    fun provideCityIDataSource(
        @ApplicationContext context: Context
    ): CityIDataSource {
        return CityLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideCityIRepo(
        dataSource: CityIDataSource
    ): CityIRepo {
        return CityRepoImp(dataSource)
    }
}


