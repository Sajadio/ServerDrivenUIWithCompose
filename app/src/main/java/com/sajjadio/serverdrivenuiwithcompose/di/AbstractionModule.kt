package com.sajjadio.serverdrivenuiwithcompose.di

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.RepositoryImpl
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.remote.DocumentaryFireStore
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.remote.DocumentaryFireStoreImpl
import com.sajjadio.serverdrivenuiwithcompose.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractionModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindsDocumentaryFireStore(documentaryFireStoreImpl: DocumentaryFireStoreImpl): DocumentaryFireStore
}