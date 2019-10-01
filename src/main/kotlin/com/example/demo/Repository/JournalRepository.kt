package com.example.demo.Repository

import com.example.demo.Model.Journal
import org.springframework.data.jpa.repository.JpaRepository

interface JournalRepository : JpaRepository<Journal,Long>{
}