package com.biblio.backend.specification;

import com.biblio.backend.dto.request.BookFilterRequest;
import com.biblio.backend.entity.Book;
import com.biblio.backend.entity.Author;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {
    private final BookFilterRequest filter;

    public BookSpecification(BookFilterRequest filter){
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(filter.getTitle())) {
            predicates.add(cb.like(cb.lower(root.get("title")),
                    "%" + filter.getTitle().toLowerCase() + "%"));
        }

        if (StringUtils.hasText(filter.getIsbn())) {
            predicates.add(cb.equal(root.get("isbn"), filter.getIsbn()));
        }

        if (filter.getPublicationYear() != null) {
            predicates.add(cb.equal(root.get("publicationYear"), filter.getPublicationYear()));
        }

        if (filter.getCategoryId() != null) {
            predicates.add(cb.equal(root.get("category").get("id"), filter.getCategoryId()));
        }

        if (filter.getAuthorId() != null) {
            Join<Book, Author> authors = root.join("authors", JoinType.LEFT);
            predicates.add(cb.equal(authors.get("id"), filter.getAuthorId()));
        }

        if (StringUtils.hasText(filter.getAuthorName())) {
            Join<Book, Author> authors = root.join("authors", JoinType.LEFT);
            predicates.add(cb.like(cb.lower(authors.get("name")),
                    "%" + filter.getAuthorName().toLowerCase() + "%"));
        }

        if (filter.getMinPages() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("pages"), filter.getMinPages()));
        }

        if (filter.getMaxPages() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("pages"), filter.getMaxPages()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
