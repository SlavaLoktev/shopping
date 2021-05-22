package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.service.AttributeService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attribute")
@CrossOrigin(origins = "http://localhost:4200")
public class AttributeController extends AbstractController<Attribute, AttributeService>{

    private static final Logger LOGGER = Logger.getLogger(AttributeController.class);

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        super(attributeService);
        this.attributeService = attributeService;
    }

    public boolean checkAdditionalParams(Attribute attribute){

        if(attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0){
            LOGGER.error("Missed param: attributeName");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Attribute attribute, String operationType){

        switch (operationType){
            case "add":
                if(attribute.getAttributeId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(attribute)){
                    return false;
                }
                break;
            case "update":
                if(attribute.getAttributeId() == null || attribute.getAttributeId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(attribute)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}
