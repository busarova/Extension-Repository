package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {


    @Override
    public List<Extension> getAllByParam(String param) {
        return null;
    }
}
