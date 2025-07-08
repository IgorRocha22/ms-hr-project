package com.inrocha.hrworker.controller;

import com.inrocha.hrworker.model.Worker;
import com.inrocha.hrworker.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {
    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private final Environment env;
    private final WorkerRepository repository;

    public WorkerController(Environment env, WorkerRepository repository) {
        this.env = env;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) throws InterruptedException {

        if (id == 0) {
            logger.debug("Id 0 requested, testing timeout for microservice...");
            Thread.sleep(3000L);
        }

        logger.debug("PORT = " + env.getProperty("local.server.port"));

        Optional<Worker> worker = repository.findById(id);
        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
