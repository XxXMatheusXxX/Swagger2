package com.livre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livre.entities.Livro;

public interface  LivroRepository extends JpaRepository<Livro,Long>{

}