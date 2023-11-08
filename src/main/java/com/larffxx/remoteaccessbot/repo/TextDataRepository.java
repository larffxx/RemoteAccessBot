package com.larffxx.remoteaccessbot.repo;

import com.larffxx.remoteaccessbot.models.TextData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TextDataRepository extends CrudRepository<TextData, Long> {

}
