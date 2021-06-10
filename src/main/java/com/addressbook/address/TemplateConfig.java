package com.addressbook.address;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;

@Configuration
@DependsOn("mongoTemplate")
public class TemplateConfig {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void initIndexes() {

		TextIndexDefinition textIndex = new TextIndexDefinitionBuilder().onField("line1").onField("line2")
				.onField("city").onField("state").onField("zip").build();
		mongoTemplate.indexOps(Address.class).ensureIndex(textIndex);
	}
}
