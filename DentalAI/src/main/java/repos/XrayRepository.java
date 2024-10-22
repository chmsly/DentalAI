package com.dentalai.repository;

import com.dentalai.model.XrayImage;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface XrayRepository extends CrudRepository<XrayImage, String> {
}
