package io.falcon.assignment.repository;

import io.falcon.assignment.model.Palindrome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalindromeRepository extends JpaRepository<Palindrome, Integer> {

}
