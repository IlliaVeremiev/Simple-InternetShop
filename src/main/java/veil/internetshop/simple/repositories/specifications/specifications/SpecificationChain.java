package veil.internetshop.simple.repositories.specifications.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SpecificationChain<E> implements Specification<E>{

    private List<Specification<E>> specifications;

    public SpecificationChain(){
        specifications = new ArrayList<>();
    }

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        return specifications.stream()
                .reduce(Specification::and)
                .orElse((r, q, c) -> c.conjunction())
                .toPredicate(root, query, criteriaBuilder);
    }

    public void add(Specification<E> specification){
        specifications.add(specification);
    }
}
