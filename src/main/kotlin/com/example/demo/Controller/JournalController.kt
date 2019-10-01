package com.example.demo.Controller

import com.example.demo.Model.Journal
import com.example.demo.Repository.JournalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/journals")
class JournalController (@Autowired private val journalRepository : JournalRepository){

    //get all journals
    @GetMapping("")
    fun getAllJournals() : List<Journal> = journalRepository.findAll()

    //create journal
    @PostMapping("")
    fun createJournal(@Valid @RequestBody journal: Journal) = journalRepository.save(journal)

    //get one journal
    @GetMapping("/{id}")
    fun getJournalById(@PathVariable id : Long) : ResponseEntity<Journal> =
            journalRepository.findById(id).map {
               /* it ->*/ ResponseEntity.ok(it) //it param is redundant in kt!!
            }.orElse(ResponseEntity.notFound().build())

    //update a journal
    @PutMapping("/{id}")
    fun updateJournal(@PathVariable id : Long, @Valid @RequestBody updatedJournal: Journal)
    : ResponseEntity<Journal> = journalRepository.findById(id).map {
        val newJournal = it.copy(title = updatedJournal.title, content = updatedJournal.content)
        ResponseEntity.ok().body(journalRepository.save(newJournal))
    }.orElse(ResponseEntity.notFound().build())
}