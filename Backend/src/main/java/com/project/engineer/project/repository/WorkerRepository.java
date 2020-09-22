package com.project.engineer.project.repository;

import com.project.engineer.project.model.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository <Worker, Long> {

    List <Worker> findByPosition(Worker worker);

    Worker findByLogin(String login);

}
