package com.examples.thsaraiva.roomwordsamplekt.wordList.repository

import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.Word
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.WordDao

class WordListRepository(private val dataSource: WordDao) {

    fun getAllWords(): List<Word> = dataSource.getAllWords()

    fun insert(word: Word) = dataSource.insert(word)

    fun delete(word: Word) = dataSource.delete(word)

    fun deleteAll() = dataSource.deleteAll()

    fun update(word: Word) = dataSource.update(word)

}