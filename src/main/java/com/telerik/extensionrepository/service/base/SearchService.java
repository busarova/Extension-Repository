package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface SearchService {

    public List<Extension> getAllByParam(String param, String orderParam);

}
