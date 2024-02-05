package com.larffxx.remoteaccessbot.repo;

import com.larffxx.remoteaccessbot.models.TextData;
import jakarta.persistence.Tuple;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public interface TextDataRepository extends CrudRepository<TextData, Long> {

    List<Tuple> findByUserName(String name);

}
