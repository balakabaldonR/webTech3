package com.es.core.dao;

import com.es.core.entity.phone.Phone;
import com.es.core.entity.phone.sort.SortField;
import com.es.core.entity.phone.sort.SortOrder;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);

    List<Phone> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query);

    Long numberByQuery(String query);
}
