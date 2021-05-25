package nitrox.org.ktboard.infrastructure.bd

import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.BoardRepository
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepositoryJPA : JpaRepository<Board, Long>, BoardRepository