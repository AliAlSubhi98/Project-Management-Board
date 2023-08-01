package com.Codeline.Project.Management.Board.Repositories;


import com.Codeline.Project.Management.Board.Models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}