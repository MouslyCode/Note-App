package com.mouscode.noteapp.di

import android.app.Application
import androidx.room.Room
import com.mouscode.noteapp.feature.note.data.data_source.NoteDatabase
import com.mouscode.noteapp.feature.note.data.repository.NoteRepositoryImpl
import com.mouscode.noteapp.feature.note.domain.repository.NoteRepository
import com.mouscode.noteapp.feature.note.domain.use_case.AddNote
import com.mouscode.noteapp.feature.note.domain.use_case.DeleteNote
import com.mouscode.noteapp.feature.note.domain.use_case.GetNote
import com.mouscode.noteapp.feature.note.domain.use_case.GetNotes
import com.mouscode.noteapp.feature.note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUsecase(repository: NoteRepository) : NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

}