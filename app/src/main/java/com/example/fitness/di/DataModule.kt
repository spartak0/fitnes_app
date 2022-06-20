package com.example.fitness.di

import android.content.Context
import com.example.data.FirebaseRepositoryImpl
import com.example.data.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideFirebaseService(@ApplicationContext context: Context):FirebaseService{
        return FirebaseService(context = context)
    }

    @Singleton
    @Provides
    fun provideRepositoryImpl(firebaseService: FirebaseService): FirebaseRepositoryImpl {
        return FirebaseRepositoryImpl(firebaseService= firebaseService)
    }


}