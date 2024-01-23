package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.RealEstate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class RealEstateRepositoryImpl implements RealEstateRepository{

    private final EntityManager entityManager;

    @Autowired
    public RealEstateRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RealEstate findRealEstateById(Long id) {
        return null;
    }

    @Override
    public List<RealEstate> findAllByAgentIdAndOptionalFields(Long id, String location, Double surfaceFrom, Double surfaceTo, Double priceFrom, Double priceTo, String type, String transactionType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RealEstate> cq = cb.createQuery(RealEstate.class);
        Root<RealEstate> realEstate = cq.from(RealEstate.class);

        List<Predicate> predicates = new ArrayList<>();

        if (location != null) predicates.add(cb.like(realEstate.get("location"), "%" + location + "%"));
        if (surfaceFrom != null) predicates.add(cb.greaterThanOrEqualTo(realEstate.get("surface_area"), surfaceFrom));
        if (surfaceTo != null) predicates.add(cb.lessThanOrEqualTo(realEstate.get("surface_area"), surfaceTo));
        if (priceFrom != null) predicates.add(cb.greaterThanOrEqualTo(realEstate.get("price"), priceFrom));
        if (priceTo != null) predicates.add(cb.lessThanOrEqualTo(realEstate.get("price"), priceTo));
        if (!type.equals("")) predicates.add(cb.equal(realEstate.get("real_estate_type"), type));
        if (!transactionType.equals("")) predicates.add(cb.equal(realEstate.get("transaction_type"), transactionType));

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends RealEstate> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends RealEstate> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<RealEstate> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public RealEstate getOne(Long aLong) {
        return null;
    }

    @Override
    public RealEstate getById(Long aLong) {
        return null;
    }

    @Override
    public RealEstate getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends RealEstate> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RealEstate> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RealEstate> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RealEstate> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RealEstate> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RealEstate> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends RealEstate, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends RealEstate> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RealEstate> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RealEstate> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<RealEstate> findAll() {
        return null;
    }

    @Override
    public List<RealEstate> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(RealEstate entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RealEstate> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<RealEstate> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RealEstate> findAll(Pageable pageable) {
        return null;
    }
}
