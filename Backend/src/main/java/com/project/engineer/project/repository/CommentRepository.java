package com.project.engineer.project.repository;

import com.project.engineer.project.model.Comment;
import com.project.engineer.project.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByRoomComment(Room room);
}
