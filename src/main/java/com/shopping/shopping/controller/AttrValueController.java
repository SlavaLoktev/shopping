package com.shopping.shopping.controller;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.service.AttrValueService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attrvalue")
@CrossOrigin(origins = "http://localhost:4200")
public class AttrValueController extends AbstractController<AttrValue, AttrValueService>{

    private static final Logger LOGGER = Logger.getLogger(AttrValueController.class);

    private final AttrValueService attrValueService;

    public AttrValueController(AttrValueService attrValueService) {
        super(attrValueService);
        this.attrValueService = attrValueService;
    }

    public boolean checkAdditionalParams(AttrValue attrValue){

        if (attrValue.getAttrValue() == null || attrValue.getAttrValue().trim().length() == 0) {
            LOGGER.error("Missed param: attrValue");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody AttrValue attrValue, String operationType){

        switch (operationType){
            case "add":
                if(attrValue.getAttrValueId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(attrValue)){
                    return false;
                }
                break;
            case "update":
                if(attrValue.getAttrValueId() == null || attrValue.getAttrValueId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(attrValue)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}
