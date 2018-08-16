package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface ExtensionRepository {

    List<Extension> getAllExtensions();
}
