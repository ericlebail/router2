package com.github.mateuszrasinski.recruitment.router.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
